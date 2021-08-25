package bruno.projeto.banco.aplicacaobancariaspringboot.ContaPJ.ContaInvestimento;

import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPJ.ContaPJ;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="contas_investimento_pj")
public class ContaInvestimentoPJ {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(referencedColumnName = "cnpj")
    @OneToOne(cascade = CascadeType.PERSIST)
    private ContaPJ conta;

    private BigDecimal saldo;

    private boolean isActive;

}
