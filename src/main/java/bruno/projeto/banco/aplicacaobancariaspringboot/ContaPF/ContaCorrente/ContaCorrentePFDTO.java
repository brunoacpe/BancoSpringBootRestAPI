package bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaCorrente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContaCorrentePFDTO {

    private BigDecimal saldo;

    private String cpf;


    public static ContaCorrentePFDTO of(ContaCorrentePF contaCorrentePF) {
        return new ContaCorrentePFDTO(contaCorrentePF.getSaldo(), contaCorrentePF.getConta().getCpf());
    }
}
