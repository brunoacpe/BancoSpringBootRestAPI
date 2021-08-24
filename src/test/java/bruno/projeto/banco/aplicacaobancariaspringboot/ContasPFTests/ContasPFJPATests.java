package bruno.projeto.banco.aplicacaobancariaspringboot.ContasPFTests;

import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaPF;
import bruno.projeto.banco.aplicacaobancariaspringboot.Repositories.ContaPFRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;


@DataJpaTest
public class ContasPFJPATests {

    private final ContaPFRepository repository;
    @Autowired
    public ContasPFJPATests(ContaPFRepository contaPFRepository){
        repository = contaPFRepository;
    }
    @Test
    void testFindAll(){
        ContaPF conta = new ContaPF(1L,"925.689.840-31","Av pedro lucas 52.", "12056-94", "Bruno","Augusto"
        , new Date(20010117), "Brunoacpe@gmail.com");
        repository.save(conta);
        List<ContaPF> list = repository.findAll();
        assertFalse(list.isEmpty());
    }


    @Test
    void testFindByCPF(){
        ContaPF conta = new ContaPF(1L,"925.689.840-31","Av pedro lucas 52.", "12056-94", "Bruno","Augusto"
                , new Date(20010117), "Brunoacpe@gmail.com");
        repository.save(conta);
        assertFalse(repository.findByCpf(conta.getCpf()).isEmpty());
    }

    @Test
    void testSave(){
        ContaPF conta = new ContaPF(1L,"925.689.840-31","Av pedro lucas 52.", "12056-94", "Bruno","Augusto"
                , new Date(20010117), "Brunoacpe@gmail.com");
        repository.save(conta);
        assertFalse(repository.findAll().isEmpty());
    }

}
