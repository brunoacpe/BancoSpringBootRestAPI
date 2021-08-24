package bruno.projeto.banco.aplicacaobancariaspringboot.ContasPFTests;

import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaCorrente.ContaCorrentePF;
import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaCorrente.ContasPFCorrenteService;
import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaCorrente.Transferencia;
import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaCorrente.TransferenciaService;
import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaPF;
import bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.ContaDesativadaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@DataJpaTest
public class TransferenciaTests {

    private final TransferenciaService transferenciaService;
    @Autowired
    public TransferenciaTests(TransferenciaService service){
        transferenciaService = service;
    }
    @Test
    void testTransferenciaSucess(){
        ContaPF conta1 = new ContaPF(1L,"925.689.840-31","Av pedro lucas 52.", "12056-94", "Bruno","Augusto"
                , new Date(20010117), "Brunoacpe@gmail.com");
        ContaCorrentePF contaCorrentePF1 = new ContaCorrentePF(1L,conta1,new BigDecimal("1000.0"),true);

        ContaPF conta2 = new ContaPF(2L,"051.871.190-01","Rua Astrogildo Rodrigues Lima", "88517-375", "Felipe","Augusto"
                , new Date(19980117), "felipeaugustolol@gmail.com");
        ContaCorrentePF contaCorrentePF2 = new ContaCorrentePF(2L, conta2, new BigDecimal("1000.0"), true);
        Transferencia transferencia = new Transferencia("925.689.840-31", "051.871.190-01", BigDecimal.valueOf(200.00));
        Transferencia transferencia2 = new Transferencia("925.232", "051.8723", BigDecimal.valueOf(20022.00));

    }

    @Test
    void testTransferenciaAccountNotActive(){

        ContaPF conta1 = new ContaPF(1L,"925.689.840-31","Av pedro lucas 52.", "12056-94", "Bruno","Augusto"
                , new Date(20010117), "Brunoacpe@gmail.com");
        ContaCorrentePF contaCorrentePF1 = new ContaCorrentePF(1L,conta1,new BigDecimal("1000.0"),false);

        ContaPF conta2 = new ContaPF(2L,"051.871.190-01","Rua Astrogildo Rodrigues Lima", "88517-375", "Felipe","Augusto"
                , new Date(19980117), "felipeaugustolol@gmail.com");
        ContaCorrentePF contaCorrentePF2 = new ContaCorrentePF(2L, conta2, new BigDecimal("1000.0"), false);
        assertThrows(ContaDesativadaException.class,
                () -> transferenciaService.fazerTransferencia(contaCorrentePF1,contaCorrentePF2,200.00));
    }
}
