package bruno.projeto.banco.aplicacaobancariaspringboot.ContasPFTests;

import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaCorrente.ContaCorrentePF;
import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaCorrente.ContasPFCorrenteService;
import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaCorrente.Transferencia;
import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaCorrente.TransferenciaService;
import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaPF;
import bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.ContaDesativadaException;
import bruno.projeto.banco.aplicacaobancariaspringboot.Repositories.TransferenciaPFRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;


@DataJpaTest
public class TransferenciaJPATests {

    private final TransferenciaPFRepository repository;

    private static final ContaPF contaUm = new ContaPF(1L,"925.689.840-31","Av pedro lucas 52.", "12056-94", "Bruno","Augusto"
            , new Date(20010117), "Brunoacpe@gmail.com");

    private static final ContaPF contaDois = new ContaPF(2L,"051.871.190-01","Rua Astrogildo Rodrigues Lima", "88517-375", "Felipe","Augusto"
            , new Date(19980117), "felipeaugustolol@gmail.com");

    @Autowired
    public TransferenciaJPATests(TransferenciaPFRepository transferenciaPFRepository){
        repository = transferenciaPFRepository;
    }
    @Test
    void testSaveTransferencia(){

        ContaCorrentePF contaCorrentePF1 = new ContaCorrentePF(1L,contaUm,new BigDecimal("1000.0"),true);
        ContaCorrentePF contaCorrentePF2 = new ContaCorrentePF(2L, contaDois, new BigDecimal("1000.0"), true);
        Transferencia transferencia = new Transferencia(contaCorrentePF1.getConta().getCpf(),
                contaCorrentePF2.getConta().getCpf(), BigDecimal.valueOf(200.00));
        repository.save(transferencia);
        assertFalse(repository.findAll().isEmpty());
    }

    @Test
    void testTransferenciaFindById(){
        ContaCorrentePF contaCorrentePF1 = new ContaCorrentePF(1L,contaUm,new BigDecimal("1000.0"),false);
        ContaCorrentePF contaCorrentePF2 = new ContaCorrentePF(2L, contaDois, new BigDecimal("1000.0"), false);
        Transferencia transferencia = new Transferencia(contaCorrentePF1.getConta().getCpf()
                ,contaCorrentePF2.getConta().getCpf(), BigDecimal.valueOf(200.00));
        repository.save(transferencia);
        assertFalse(repository.findById(1L).isEmpty());
    }
}
