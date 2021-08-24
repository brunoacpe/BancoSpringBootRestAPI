package bruno.projeto.banco.aplicacaobancariaspringboot.ContaPJ;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;
@Table(name="contas_investimento_pj")
public class ContaInvestimentoPJ {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(referencedColumnName = "CNPJ")
    private ContaPJ conta;

    private BigDecimal saldo;

}
