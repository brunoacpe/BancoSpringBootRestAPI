package bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaCorrente;

import bruno.projeto.banco.aplicacaobancariaspringboot.Repositories.ContaPFCorrenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("transferir")
public class TransferenciaContasPFController {

    private final ContaPFCorrenteRepository repository;
    private final TransferenciaService service;

    @Autowired
    public TransferenciaContasPFController(ContaPFCorrenteRepository contaPFCorrenteRepository, TransferenciaService transferenciaService) {
        repository = contaPFCorrenteRepository;
        service = transferenciaService;
    }

    @PutMapping("/{cpf1}/{cpf2}/")
    public ResponseEntity<Transferencia> novaTransferencia(
            @PathVariable String cpf1,
            @PathVariable String cpf2,
            @RequestParam Double valorTransferido
    ) {
        Optional<ContaCorrentePF> conta1 = repository.findByConta_Cpf(cpf1);
        Optional<ContaCorrentePF> conta2 = repository.findByConta_Cpf(cpf2);
        if (conta1.isPresent() && conta2.isPresent()) {
            return ResponseEntity.ok().body(service.fazerTransferencia(conta1.get(), conta2.get(), valorTransferido));
        }

        return null;
    }

    public ResponseEntity<List<Transferencia>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }
}
