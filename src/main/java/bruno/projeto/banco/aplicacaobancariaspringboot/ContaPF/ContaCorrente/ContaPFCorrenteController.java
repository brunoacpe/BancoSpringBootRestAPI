package bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaCorrente;

import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaPF;
import bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.CPFNaoExistente;
import bruno.projeto.banco.aplicacaobancariaspringboot.Repositories.ContaPFCorrenteRepository;
import bruno.projeto.banco.aplicacaobancariaspringboot.Repositories.ContaPFRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/contasPFCorrente")
public class ContaPFCorrenteController {
    private final ContaPFRepository repository2;
    private final ContaPFCorrenteRepository repository;
    private final ContasPFCorrenteService service;

    @Autowired
    public ContaPFCorrenteController(ContasPFCorrenteService contasPFCorrenteService
            , ContaPFCorrenteRepository contaCorrentePFRepository, ContaPFRepository contaPFRepository) {
        repository = contaCorrentePFRepository;
        service = contasPFCorrenteService;
        repository2 = contaPFRepository;
    }

    @PostMapping("/criarConta")
    public ResponseEntity<ContaCorrentePFDTO> createContaCorrentePF(@RequestBody ContaCorrentePF contaCorrentePF) {
        log.info("Requisição post para criar uma conta corrente PF nova com o CFP {}", contaCorrentePF.getConta().getCpf());
        contaCorrentePF.setActive(true);
        ContaPF contaPF = repository2.findByCpf(contaCorrentePF.getConta().getCpf()).orElseThrow(CPFNaoExistente::new);
        if (repository.findByConta(contaPF).isPresent()) {
            log.info("Tentativa de criação de conta corrente PF em um CPF que já existe. CPF: {}", contaCorrentePF.getConta().getCpf());
            return ResponseEntity.badRequest().body(null);
        }
        contaCorrentePF.setConta(contaPF);
        return ResponseEntity.ok().body(service.createContaCorrentePF(contaCorrentePF));
    }

    @GetMapping
    public ResponseEntity<List<ContaCorrentePF>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @PutMapping("/sacar/{cpf}")
    public ResponseEntity<ContaCorrentePFDTO> sacarContaCorrentePF(@PathVariable String cpf, @RequestParam Double valor) {
        log.info("Requisição post para sacar da conta com CPF: {}", cpf);
        ContaCorrentePF contaCorrentePF = repository.findByConta_Cpf(cpf).orElseThrow(CPFNaoExistente::new);
        return ResponseEntity.ok().body(service.sacarContaCorrentePF(contaCorrentePF, valor));
    }

    @PutMapping("/depositar/{cpf}")
    public ResponseEntity<ContaCorrentePFDTO> depositarContaCorrentePF(@PathVariable String cpf, @RequestParam Double valor) {
        log.info("Requisição post para depositar o valor de {} R$ na conta com CPF: {}", valor, cpf);

        ContaCorrentePF conta = repository.findByConta_Cpf(cpf).orElseThrow(CPFNaoExistente::new);
        return ResponseEntity.ok().body(service.depositarContaCorrentePF(conta, valor));
    }

    @GetMapping("/saldo/{cpf}")
    public ResponseEntity<ContaCorrentePFDTOSaldo> getSaldo(@PathVariable String cpf) {
        log.info("Requisição get para checar o saldo da conta com CPF: {}", cpf);
        ContaCorrentePF conta = repository.findByConta(repository2.findByCpf(cpf).orElseThrow(CPFNaoExistente::new)).orElseThrow(CPFNaoExistente::new);
        return ResponseEntity.ok().body(ContaCorrentePFDTOSaldo.of(conta));
    }

    @PutMapping("/desativar/{cpf}")
    public ResponseEntity<ContaCorrentePFDTO> desativarConta(@PathVariable String cpf) {
        log.info("Requisição put para desativar conta corrente PF com o CPF: {}", cpf);
        ContaCorrentePF conta = repository.findByConta_Cpf(cpf).orElseThrow(CPFNaoExistente::new);
        conta.setActive(false);
        repository.save(conta);
        log.info("Conta corrente PF com CPF: {} desativada.", cpf);
        return ResponseEntity.ok().body(ContaCorrentePFDTO.of(conta));
    }

    @PutMapping("/ativar/{cpf}")
    public ResponseEntity<ContaCorrentePFDTO> ativarConta(@PathVariable String cpf) {
        log.info("Requisição put para ativar conta corrente PF com o CPF: {}", cpf);
        ContaCorrentePF conta = repository.findByConta_Cpf(cpf).orElseThrow(CPFNaoExistente::new);
        conta.setActive(true);
        repository.save(conta);
        log.info("Conta corrente PF com CPF: {} ativada.", cpf);
        return ResponseEntity.ok().body(ContaCorrentePFDTO.of(conta));
    }
}
