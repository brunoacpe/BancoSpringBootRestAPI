package bruno.projeto.banco.aplicacaobancariaspringboot.ContaPJ;

import bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.ContaPJ.ContaPJCadastradaComEsteCNPJ;
import bruno.projeto.banco.aplicacaobancariaspringboot.Repositories.ContaPJRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;


@Slf4j
@RequestMapping("/contasPJ")
@RestController
public class ContaPJController {

    private final ContaPJRepository repository;

    @Autowired
    public ContaPJController(ContaPJRepository contaPJRepository){
        repository = contaPJRepository;
    }

    @PostMapping("/criarConta")
    public ResponseEntity<ContaPJ> createContaPJ(@RequestBody ContaPJ contaPJ){
        log.info("Request post para criar uma nova conta PJ com CNPJ: {}", contaPJ.getCnpj());
        if(!repository.findByCnpj(contaPJ.getCnpj()).isEmpty()){
            log.info("Tentativa de cadastro com CNPJ: {} que já foi cadastrado.", contaPJ.getCnpj());
            throw new ContaPJCadastradaComEsteCNPJ();
        }
        return ResponseEntity.ok().body(repository.save(contaPJ));
    }

    @GetMapping("/buscarTodas")
    public ResponseEntity<List<ContaPJ>> findAll(){
        log.info("Request get para listar todas as contas PJ.");
        return ResponseEntity.ok().body(repository.findAll());
    }
}
