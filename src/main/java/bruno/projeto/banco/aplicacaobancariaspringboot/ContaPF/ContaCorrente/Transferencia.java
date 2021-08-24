package bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaCorrente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transferencias_corrente_pf")
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "conta_transferente")
    private String contaQueTransferiu;


    @Column(name = "conta_transferida")
    private String contaQueRecebeu;

    @Column(name = "valor_transferido")
    private BigDecimal valorTransferido;

    public Transferencia(String conta1, String conta2, BigDecimal valueOf) {
        this.contaQueTransferiu = conta1;
        contaQueRecebeu = conta2;
        valorTransferido = valueOf;
    }
}
