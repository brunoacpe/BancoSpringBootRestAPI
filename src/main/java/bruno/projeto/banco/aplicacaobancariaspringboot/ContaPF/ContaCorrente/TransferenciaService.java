package bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaCorrente;

import bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.ContaDesativadaException;

import bruno.projeto.banco.aplicacaobancariaspringboot.Repositories.TransferenciaPFRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class TransferenciaService {

    private final TransferenciaPFRepository repositoryTransferencia;
    private final ContasPFCorrenteService service;

    @Autowired
    public TransferenciaService(
            TransferenciaPFRepository transferenciaPFRepository,
            ContasPFCorrenteService contasPFCorrenteService
    ) {
        repositoryTransferencia = transferenciaPFRepository;
        service = contasPFCorrenteService;
    }


    public Transferencia fazerTransferencia(ContaCorrentePF conta1, ContaCorrentePF conta2, Double valor) {
        if (!conta1.isActive() || !conta2.isActive()) {
            throw new ContaDesativadaException();
        }
        service.sacarContaCorrentePF(conta1, valor);
        service.depositarContaCorrentePF(conta2, valor);
        log.info("Transferencia com valor de {} R$ da conta com CPF :{} para a conta com CPF: {}", valor, conta1.getConta().getCpf(), conta2.getConta().getCpf());
        Transferencia transferencia = new Transferencia(conta1.getConta().getCpf(), conta2.getConta().getCpf(), BigDecimal.valueOf(valor));
        return repositoryTransferencia.save(transferencia);
    }

    public List<Transferencia> findAll() {
        return repositoryTransferencia.findAll();
    }
}
