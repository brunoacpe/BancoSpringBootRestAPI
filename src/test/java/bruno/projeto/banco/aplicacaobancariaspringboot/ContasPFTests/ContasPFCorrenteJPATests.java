package bruno.projeto.banco.aplicacaobancariaspringboot.ContasPFTests;

import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaCorrente.ContaCorrentePF;
import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaCorrente.ContasPFCorrenteService;
import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaPF;
import bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.ContaDesativadaException;
import bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.SaldoInsuficienteContaCorrentePF;
import bruno.projeto.banco.aplicacaobancariaspringboot.Repositories.ContaPFCorrenteRepository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.sql.Date;

@DataJpaTest
public class ContasPFCorrenteJPATests {

    private final ContaPFCorrenteRepository repository;

    @Autowired
    public ContasPFCorrenteJPATests(ContaPFCorrenteRepository contaPFCorrenteRepository){
        repository= contaPFCorrenteRepository;
    }

    @Test
    void testFindAll(){
        ContaPF conta = new ContaPF(1L,"925.689.840-31","Av pedro lucas 52.", "12056-94", "Bruno","Augusto"
                , new Date(20010117), "Brunoacpe@gmail.com");
        ContaCorrentePF contaCorrentePF = new ContaCorrentePF(1L,conta,BigDecimal.valueOf(200.00),true);
        repository.save(contaCorrentePF);
        assertFalse(repository.findAll().isEmpty());
    }

    @Test
    void testSave(){
        ContaPF conta = new ContaPF(1L,"925.689.840-31","Av pedro lucas 52.", "12056-94", "Bruno","Augusto"
                , new Date(20010117), "Brunoacpe@gmail.com");
        ContaCorrentePF contaCorrentePF = new ContaCorrentePF(1L,conta,BigDecimal.valueOf(200.00),true);
        repository.save(contaCorrentePF);
        assertFalse(repository.findAll().isEmpty());
    }

    @Test
    void testFindByCPF(){
        ContaPF conta = new ContaPF(1L,"925.689.840-31","Av pedro lucas 52.", "12056-94", "Bruno","Augusto"
                , new Date(20010117), "Brunoacpe@gmail.com");
        ContaCorrentePF contaCorrentePF = new ContaCorrentePF(1L,conta,BigDecimal.valueOf(200.00),true);
        repository.save(contaCorrentePF);
        assertFalse(repository.findByConta_Cpf(contaCorrentePF.getConta().getCpf()).isEmpty());
    }
}
