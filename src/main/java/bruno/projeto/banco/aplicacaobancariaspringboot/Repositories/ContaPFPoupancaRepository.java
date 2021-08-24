package bruno.projeto.banco.aplicacaobancariaspringboot.Repositories;

import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaCorrente.ContaCorrentePF;
import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaPF;
import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaPoupança.ContaPoupancaPF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaPFPoupancaRepository extends JpaRepository<ContaPoupancaPF,Long> {
    Optional<ContaPoupancaPF> findByConta(ContaPF conta);
    Optional<ContaPoupancaPF> findByConta_Cpf(String cpf);
}
