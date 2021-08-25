package bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaPoupança;


import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaPF;
import bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.ContasPF.CPFNaoExistente;
import bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.ContaDesativadaException;

import bruno.projeto.banco.aplicacaobancariaspringboot.Repositories.ContaPFPoupancaRepository;
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
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/contasPoupançaPF")
public class ContaPFPoupancaController {

    private final ContaPFPoupancaService service;
    private final ContaPFPoupancaRepository repository;
    private final ContaPFRepository repository2;

    @Autowired
    public ContaPFPoupancaController(
            ContaPFPoupancaService contaPFPoupancaService,
            ContaPFPoupancaRepository contaPFPoupancaRepository,
            ContaPFRepository contaPFRepository
    ) {
        service = contaPFPoupancaService;
        repository = contaPFPoupancaRepository;
        repository2 = contaPFRepository;
    }


    @GetMapping
    public ResponseEntity<List<ContaPoupancaPF>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }


    @PostMapping("/criarConta")
    public ResponseEntity<ContaPoupancaPFDTO> criarContaPoupancaPF(@RequestBody ContaPoupancaPF conta) {
        log.info("Request post para criar uma nova conta poupança PF com CPF: {}", conta.getConta().getCpf());
        conta.setActive(true);
        ContaPF contaPF = repository2.findByCpf(conta.getConta().getCpf()).orElseThrow(CPFNaoExistente::new);
        if (repository.findByConta(contaPF).isPresent()) {
            log.info("Tentativa de criação de conta poupança PF com CPF já existente. CPF:{} ", conta.getConta().getCpf());
            return ResponseEntity.badRequest().body(null);
        }
        conta.setConta(contaPF);
        return ResponseEntity.ok().body(service.criarConta(conta));
    }

    @PutMapping("/sacar/{cpf}")
    public ResponseEntity<ContaPoupancaPFDTO> sacarContaPoupancaPF(@PathVariable String cpf, @RequestParam Double valor) {
        log.info("Requisição post para sacar da conta poupança com CPF: {}", cpf);
        ContaPF contaPF = repository2.findByCpf(cpf).orElseThrow(CPFNaoExistente::new);
        Optional<ContaPoupancaPF> contaPoupancaPF = repository.findByConta(contaPF);
        if (!contaPoupancaPF.get().isActive()) {
            throw new ContaDesativadaException();
        }
        return ResponseEntity.ok().body(service.sacarContaPoupancaPF(contaPoupancaPF.get(), valor));
    }

    @PutMapping("/render/{cpf}")
    public ResponseEntity<ContaPoupancaPFDTO> renderAnualContaPoupancaPF(@PathVariable String cpf) {
        log.info("Requisição put para render na conta poupança PF com CPF: {}", cpf);
        ContaPoupancaPF contaPoupancaPF = repository.findByConta_Cpf(cpf).orElseThrow(CPFNaoExistente::new);
        if (!contaPoupancaPF.isActive()) {
            throw new ContaDesativadaException();
        }
        return ResponseEntity.ok().body(service.renderAno(contaPoupancaPF));
    }

    @PutMapping("/depositar/{cpf}")
    public ResponseEntity<ContaPoupancaPFDTO> depositarContaPFPoupanca(@PathVariable String cpf, @RequestParam Double valor) {
        log.info("Requisição put para depositar o valor de {} R$ na conta poupança PF com CPF: {}", valor, cpf);
        ContaPoupancaPF contaPoupancaPF = repository.findByConta_Cpf(cpf).orElseThrow(CPFNaoExistente::new);
        if (!contaPoupancaPF.isActive()) {
            throw new ContaDesativadaException();
        }
        return ResponseEntity.ok().body(service.depositar(contaPoupancaPF, valor));
    }

    @PutMapping("/desativar/{cpf}")
    public ResponseEntity<ContaPoupancaPFDTO> desativarConta(@PathVariable String cpf) {
        log.info("Requisição post para desativar a conta com CPF: {}", cpf);
        return ResponseEntity.ok().body(service.desativarConta(cpf));
    }

    @PutMapping("/ativar/{cpf}")
    public ResponseEntity<ContaPoupancaPFDTO> ativarConta(@PathVariable String cpf) {
        log.info("Requisição post para desativar a conta com CPF: {}", cpf);
        return ResponseEntity.ok().body(service.ativarConta(cpf));
    }
}
