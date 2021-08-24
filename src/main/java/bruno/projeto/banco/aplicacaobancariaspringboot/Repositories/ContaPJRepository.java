package bruno.projeto.banco.aplicacaobancariaspringboot.Repositories;

import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPJ.ContaPJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaPJRepository extends JpaRepository<ContaPJ, Long> {
}
