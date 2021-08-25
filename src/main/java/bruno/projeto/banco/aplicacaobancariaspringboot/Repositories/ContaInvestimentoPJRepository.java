package bruno.projeto.banco.aplicacaobancariaspringboot.Repositories;

import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPJ.ContaInvestimento.ContaInvestimentoPJ;
import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPJ.ContaPJ;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContaInvestimentoPJRepository extends JpaRepository<ContaInvestimentoPJ,Long> {

    Optional<ContaInvestimentoPJ> findByConta_Cnpj(String cnpj);

    Optional<ContaInvestimentoPJ> findByConta(ContaPJ contaPJ);
}
