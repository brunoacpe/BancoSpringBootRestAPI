package bruno.projeto.banco.aplicacaobancariaspringboot.Repositories;

import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaCorrente.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenciaPFRepository extends JpaRepository<Transferencia,Long> {
}
