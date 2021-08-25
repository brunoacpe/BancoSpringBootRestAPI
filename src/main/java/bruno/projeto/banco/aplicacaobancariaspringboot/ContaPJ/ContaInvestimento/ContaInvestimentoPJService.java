package bruno.projeto.banco.aplicacaobancariaspringboot.ContaPJ.ContaInvestimento;

import bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.ContaDesativadaException;
import bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.ContaPJ.CNPJNãoExistente;
import bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.ContaPJ.ContaPJInvestimentoJaCadastradaComCNPJ;
import bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.ContasPF.SaldoInsuficienteParaSaque;
import bruno.projeto.banco.aplicacaobancariaspringboot.Repositories.ContaInvestimentoPJRepository;
import bruno.projeto.banco.aplicacaobancariaspringboot.Repositories.ContaPJRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Slf4j
@Service
public class ContaInvestimentoPJService {
    private static final BigDecimal TAXA_SAQUE = BigDecimal.valueOf(10.50);
    private static final BigDecimal RENDIMENTO_ANUAL = BigDecimal.valueOf(0.04);
    private final ContaPJRepository repository2;
    private final ContaInvestimentoPJRepository repository;

    @Autowired
    public ContaInvestimentoPJService(ContaInvestimentoPJRepository contaInvestimentoPJRepository, ContaPJRepository contaPJRepository){
        repository = contaInvestimentoPJRepository;
        repository2 = contaPJRepository;
    }



    public ContaInvestimentoPJ criarConta(ContaInvestimentoPJ contaInvestimentoPJ) {
        return repository.save(contaInvestimentoPJ);
    }

    public void checarSeEstaDisponivel(String cnpj) {
        if(!repository.findByConta_Cnpj(cnpj).isEmpty()){
            throw new ContaPJInvestimentoJaCadastradaComCNPJ();
        }
    }

    public void checarSeEstaAtiva(ContaInvestimentoPJ conta){
        if(!conta.isActive()){
            throw new ContaDesativadaException();
        }
    }

    public ContaInvestimentoPJ findByCnpj(String cnpj) {
        return repository.findByConta_Cnpj(cnpj).orElseThrow(CNPJNãoExistente::new);
    }

    public ContaInvestimentoPJ depositar(ContaInvestimentoPJ conta, Double valor) {
        BigDecimal saldoAtual = conta.getSaldo();
        conta.setSaldo(saldoAtual.add(BigDecimal.valueOf(valor)));
        repository.save(conta);
        return conta;
    }

    public ContaInvestimentoPJ desativarConta(ContaInvestimentoPJ conta){
        conta.setActive(false);
        repository.save(conta);
        return conta;
    }

    public ContaInvestimentoPJ ativarConta(ContaInvestimentoPJ conta){
        conta.setActive(true);
        repository.save(conta);
        return conta;
    }

    public ContaInvestimentoPJ renderInvestimentosAnual(ContaInvestimentoPJ conta) {
        BigDecimal saldoAtual = conta.getSaldo();
        BigDecimal saldoComAcrescimoTaxa = saldoAtual.multiply(RENDIMENTO_ANUAL);
        conta.setSaldo(saldoComAcrescimoTaxa.add(saldoAtual));
        repository.save(conta);
        return conta;
    }

    public ContaInvestimentoPJ sacar(ContaInvestimentoPJ conta, Double valor) {
        BigDecimal saldoAtual = conta.getSaldo();
        int checarTransacao = saldoAtual.add(TAXA_SAQUE).compareTo(BigDecimal.valueOf(valor));
        if (checarTransacao == 1) {
            BigDecimal decrescimoSaldo = BigDecimal.valueOf(valor);
            conta.setSaldo(saldoAtual.subtract(decrescimoSaldo).subtract(TAXA_SAQUE));
            return repository.save(conta);
        }
        log.info("Saldo insuficiente para sacar este valor. CNPJ da conta : {}", conta.getConta().getCnpj());
        throw new SaldoInsuficienteParaSaque();
    }
}
