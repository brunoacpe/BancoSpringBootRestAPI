package bruno.projeto.banco.aplicacaobancariaspringboot.Repositories;

import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaCorrente.ContaCorrentePF;
import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaPF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaPFCorrenteRepository extends JpaRepository<ContaCorrentePF,Long> {
    Optional<ContaCorrentePF> findById(Long id);
    Optional<ContaCorrentePF> findByConta(ContaPF contaPF);
    Optional<ContaCorrentePF> findByConta_Cpf(String cpf);
}
