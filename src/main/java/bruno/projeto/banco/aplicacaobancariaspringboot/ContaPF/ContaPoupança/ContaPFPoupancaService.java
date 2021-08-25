package bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaPoupança;

import bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.ContasPF.SaldoInsuficienteParaSaque;
import bruno.projeto.banco.aplicacaobancariaspringboot.Repositories.ContaPFPoupancaRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class ContaPFPoupancaService {
    private static final BigDecimal TAXA_SAQUE = BigDecimal.valueOf(6.50);
    private static final BigDecimal TAXA_RENDIMENTO = BigDecimal.valueOf(0.04);
    private final ContaPFPoupancaRepository repository;

    @Autowired
    public ContaPFPoupancaService(
            ContaPFPoupancaRepository contaPFPoupancaRepository
    ) {
        repository = contaPFPoupancaRepository;
    }

    public List<ContaPoupancaPF> findAll() {
        return repository.findAll();
    }

    public ContaPoupancaPFDTO criarConta(ContaPoupancaPF conta) {
        return ContaPoupancaPFDTO.convert(repository.save(conta));
    }

    public ContaPoupancaPFDTO sacarContaPoupancaPF(ContaPoupancaPF conta, Double valor) {
        BigDecimal saldoAtual = conta.getSaldo();
        int checarTransacao = saldoAtual.add(TAXA_SAQUE).compareTo(BigDecimal.valueOf(valor));
        if (checarTransacao == 1) {
            BigDecimal decrescimoSaldo = BigDecimal.valueOf(valor);
            conta.setSaldo(saldoAtual.subtract(decrescimoSaldo).subtract(TAXA_SAQUE));
            repository.save(conta);
            log.info("O valor de {} R$ foi sacado da conta poupança PF com CPF: {}", valor, conta.getConta().getCpf());
            return ContaPoupancaPFDTO.convert(conta);
        }
        log.info("Saldo insuficiente para sacar. CPF da conta {}", conta.getConta().getCpf());
        throw new SaldoInsuficienteParaSaque();
    }


    public ContaPoupancaPFDTO renderAno(ContaPoupancaPF conta) {
        BigDecimal saldoAtual = conta.getSaldo();
        BigDecimal saldoAtualAplicadoTaxa = saldoAtual.multiply(TAXA_RENDIMENTO);
        conta.setSaldo(saldoAtual.add(saldoAtualAplicadoTaxa));
        repository.save(conta);
        log.info("Rendimento anual aplicado. CPF da conta: {}", conta.getConta().getCpf());
        return ContaPoupancaPFDTO.convert(conta);
    }

    public ContaPoupancaPFDTO depositar(ContaPoupancaPF contaPoupancaPF, Double valor) {
        contaPoupancaPF.setSaldo(contaPoupancaPF.getSaldo().add(BigDecimal.valueOf(valor)));
        repository.save(contaPoupancaPF);
        log.info("O valor de {} R$ foi depositado na conta poupança PF com CPF: {}", valor, contaPoupancaPF.getConta().getCpf());
        return ContaPoupancaPFDTO.convert(contaPoupancaPF);
    }

    @PutMapping
    public ContaPoupancaPFDTO desativarConta(String cpf) {
        log.info("Requisição put para desativar a conta poupança PF com CPF: {}",cpf);
        ContaPoupancaPF contaPoupancaPF = repository.findByConta_Cpf(cpf).get();
        contaPoupancaPF.setActive(false);
        repository.save(contaPoupancaPF);
        return ContaPoupancaPFDTO.convert(contaPoupancaPF);
    }

    @PutMapping
    public ContaPoupancaPFDTO ativarConta(String cpf) {
        log.info("Requisição put para re-ativar a conta poupança PF com CPF: {}",cpf);
        ContaPoupancaPF contaPoupancaPF = repository.findByConta_Cpf(cpf).get();
        contaPoupancaPF.setActive(true);
        repository.save(contaPoupancaPF);
        return ContaPoupancaPFDTO.convert(contaPoupancaPF);
    }
}
