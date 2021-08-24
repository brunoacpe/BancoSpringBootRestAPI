package bruno.projeto.banco.aplicacaobancariaspringboot.ContaPJ;

import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaPF;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;
@Table(name= "contas_corrente_pj")
public class ContaCorrentePJ {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(referencedColumnName = "CNPJ")
    private ContaPJ conta;

    private BigDecimal saldo;

}
