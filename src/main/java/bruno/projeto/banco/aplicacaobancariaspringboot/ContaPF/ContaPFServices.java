package bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF;


import bruno.projeto.banco.aplicacaobancariaspringboot.Repositories.ContaPFRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class ContaPFServices {

    private final ContaPFRepository repository;

    @Autowired
    public ContaPFServices(ContaPFRepository contaPFRepository) {
        repository = contaPFRepository;
    }

    public ContaPFDTO createContaPF(ContaPF contaPF) {
        return ContaPFDTO.of(repository.save(contaPF));
    }

    public Optional<ContaPF> findConta(String cpf) {

        return repository.findByCpf(cpf);
    }

    public List<ContaPF> findAll() {
        return repository.findAll();
    }
}
