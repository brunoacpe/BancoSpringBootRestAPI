package bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaCorrente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class ContaCorrentePFDTOSaldo {

    private BigDecimal saldo;


    public static ContaCorrentePFDTOSaldo of(ContaCorrentePF contaCorrentePF) {
        return new ContaCorrentePFDTOSaldo(contaCorrentePF.getSaldo());
    }
}
