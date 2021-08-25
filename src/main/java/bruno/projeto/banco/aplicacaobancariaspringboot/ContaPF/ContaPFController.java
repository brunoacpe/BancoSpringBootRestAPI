package bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/contasPF")
public class ContaPFController {

    private final ContaPFServices services;

    @Autowired
    public ContaPFController(ContaPFServices contaPFServices) {
        services = contaPFServices;
    }

    @GetMapping
    public ResponseEntity<List<ContaPF>> getContas() {
        return ResponseEntity.ok().body(services.findAll());
    }

    @PostMapping("/criarConta")
    public ResponseEntity<ContaPFDTO> createContaPF(@RequestBody ContaPF contaPF) {
        if (services.findConta(contaPF.getCpf()).isPresent()) {
            log.error("Conta com CPF já cadastrado.");
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok().body(services.createContaPF(contaPF));
    }
}
