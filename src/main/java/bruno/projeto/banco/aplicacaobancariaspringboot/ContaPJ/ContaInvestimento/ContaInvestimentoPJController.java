package bruno.projeto.banco.aplicacaobancariaspringboot.ContaPJ.ContaInvestimento;

import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPJ.ContaPJ;
import bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.ContaPJ.CNPJNãoExistente;
import bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.ContaPJ.ContaPJInvestimentoJaCadastradaComCNPJ;

import bruno.projeto.banco.aplicacaobancariaspringboot.Repositories.ContaPJRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@Slf4j
@RestController
@RequestMapping("/contasInvestimentoPJ")
public class ContaInvestimentoPJController {

    private final ContaInvestimentoPJService service;
    private final ContaPJRepository repository;
    @Autowired
    public ContaInvestimentoPJController(ContaInvestimentoPJService contaInvestimentoPJService, ContaPJRepository  contaPJRepository){
        service = contaInvestimentoPJService;
        repository = contaPJRepository;
    }

    @PostMapping("/criarConta")
    public ResponseEntity<ContaInvestimentoPJ> criarContaInvestimentoPJ(@RequestBody ContaInvestimentoPJ contaInvestimentoPJ){
        log.info("Request post para criar uma nova conta investimento PJ com CNPJ: {}"
                , contaInvestimentoPJ.getConta().getCnpj());
        ContaPJ contaPJ = repository.findByCnpj(contaInvestimentoPJ.getConta().getCnpj()).orElseThrow(CNPJNãoExistente::new);
        contaInvestimentoPJ.setActive(true);
        contaInvestimentoPJ.setConta(contaPJ);
        service.checarSeEstaDisponivel(contaInvestimentoPJ.getConta().getCnpj());
        return ResponseEntity.ok().body(service.criarConta(contaInvestimentoPJ));
    }


    @PutMapping("/depositar/{cnpj}")
    public ResponseEntity<ContaInvestimentoPJ> depositarContaInvestimentoPJ(@PathVariable String cnpj, @RequestParam Double valor){
        log.info("Request put para depositar na conta com CNPJ {}", cnpj);
        ContaInvestimentoPJ conta = service.findByCnpj(cnpj);
        service.checarSeEstaAtiva(conta);
        return ResponseEntity.ok().body(service.depositar(conta,valor));
    }

    @PutMapping("/sacar/{cnpj}")
    public ResponseEntity<ContaInvestimentoPJ> sacarContaInvestimentoPJ(@PathVariable String cnpj, @RequestParam Double valor){
        log.info("Request put para sacar da conta com CNPJ{}", cnpj);
        ContaInvestimentoPJ conta = service.findByCnpj(cnpj);
        service.checarSeEstaAtiva(conta);
        return ResponseEntity.ok().body(service.sacar(conta,valor));
    }

    @PutMapping("/investir/{cnpj}")
    public ResponseEntity<ContaInvestimentoPJ> renderInvestimentoContaInvestimentoPJ(@PathVariable String cnpj){
        log.info("Request put para render os investimentos na conta com CNPJ {}", cnpj);
        ContaInvestimentoPJ conta = service.findByCnpj(cnpj);
        service.checarSeEstaAtiva(conta);
        return ResponseEntity.ok().body(service.renderInvestimentosAnual(conta));
    }

    @PutMapping("/ativar/{cnpj}")
    public ResponseEntity<ContaInvestimentoPJ> reativarContaInvestimentoPJ(@PathVariable String cnpj){
        log.info("Request put para re-ativar a conta investimento PJ com CNPJ: {}", cnpj);
        ContaInvestimentoPJ conta = service.findByCnpj(cnpj);
        return ResponseEntity.ok().body(service.ativarConta(conta));
    }

    @PutMapping("/desativar/{cnpj}")
    public ResponseEntity<ContaInvestimentoPJ> desativarContaInvestimentoPJ(@PathVariable String cnpj){
        log.info("Request put para desativar a conta investimento PJ com CNPJ: {}", cnpj);
        ContaInvestimentoPJ conta = service.findByCnpj(cnpj);
        return ResponseEntity.ok().body(service.desativarConta(conta));
    }
}
