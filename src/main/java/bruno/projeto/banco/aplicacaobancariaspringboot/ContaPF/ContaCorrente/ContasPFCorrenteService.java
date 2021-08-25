package bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaCorrente;

import bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.ContaDesativadaException;
import bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.ContasPF.SaldoInsuficienteParaSaque;
import bruno.projeto.banco.aplicacaobancariaspringboot.Repositories.ContaPFCorrenteRepository;
import bruno.projeto.banco.aplicacaobancariaspringboot.Repositories.ContaPFRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class ContasPFCorrenteService {
    private static final BigDecimal TAXA_SAQUE = BigDecimal.valueOf(6.50);
    private final ContaPFCorrenteRepository repository;
    private final ContaPFRepository repositoryContas;

    @Autowired
    public ContasPFCorrenteService(ContaPFCorrenteRepository contaPFCorrenteRepository, ContaPFRepository contaPFRepository) {
        repository = contaPFCorrenteRepository;
        repositoryContas = contaPFRepository;
    }

    public ContaCorrentePFDTO createContaCorrentePF(ContaCorrentePF contaCorrentePF) {
        return ContaCorrentePFDTO.of(repository.save(contaCorrentePF));
    }


    public ContaCorrentePFDTO sacarContaCorrentePF(ContaCorrentePF conta, Double valor) {
        BigDecimal saldoAtual = conta.getSaldo();
        int checarTransacao = saldoAtual.add(TAXA_SAQUE).compareTo(BigDecimal.valueOf(valor));
        if (!conta.isActive()) {
            throw new ContaDesativadaException();
        }
        if (checarTransacao == 1) {
            BigDecimal decrescimoSaldo = BigDecimal.valueOf(valor);
            conta.setSaldo(saldoAtual.subtract(decrescimoSaldo).subtract(TAXA_SAQUE));
            repository.save(conta);
            log.info("O valor de {} R$ foi sacado da conta com CPF: {}", valor, conta.getConta().getCpf());
            return ContaCorrentePFDTO.of(conta);
        }
        log.info("Saldo insuficiente para sacar. CPF da conta {}", conta.getConta().getCpf());
        throw new SaldoInsuficienteParaSaque();

    }

    public ContaCorrentePFDTO depositarContaCorrentePF(ContaCorrentePF conta, Double valor) {
        if (!conta.isActive()) {
            throw new ContaDesativadaException();
        }
        BigDecimal saldoAtual = conta.getSaldo();
        BigDecimal acrescimoSaldo = BigDecimal.valueOf(valor);
        conta.setSaldo(saldoAtual.add(acrescimoSaldo));
        repository.save(conta);
        return ContaCorrentePFDTO.of(conta);
    }

    public List<ContaCorrentePF> findAll() {
        return repository.findAll();
    }
}
