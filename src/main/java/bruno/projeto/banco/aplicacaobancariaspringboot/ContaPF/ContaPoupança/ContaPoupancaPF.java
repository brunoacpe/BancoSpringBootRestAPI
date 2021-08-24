package bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaPoupança;

import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaPF;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "contas_poupanca_pf")
public class ContaPoupancaPF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "cpf_poupanca", referencedColumnName = "cpf")
    @OneToOne(cascade = CascadeType.ALL)
    private ContaPF conta;

    private BigDecimal saldo;

    private boolean isActive;
}
