package bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaCorrente;

import bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaPF;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "contas_corrente_pf")
public class ContaCorrentePF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "cpf_corrente", referencedColumnName = "cpf")
    @OneToOne(cascade = CascadeType.ALL)
    private ContaPF conta;

    private BigDecimal saldo;

    private boolean isActive;
}
