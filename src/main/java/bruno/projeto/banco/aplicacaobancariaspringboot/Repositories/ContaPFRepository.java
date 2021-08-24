package bruno.projeto.banco.aplicacaobancariaspringboot.Repositories;

import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaPF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaPFRepository extends JpaRepository<ContaPF, Long> {
    Optional<ContaPF> findByCpf(String cpf);
}
