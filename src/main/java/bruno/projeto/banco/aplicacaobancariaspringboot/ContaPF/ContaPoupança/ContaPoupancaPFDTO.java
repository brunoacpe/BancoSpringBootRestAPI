package bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF.ContaPoupança;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContaPoupancaPFDTO {

    private BigDecimal saldo;

    private String cpf;

    public static ContaPoupancaPFDTO convert(ContaPoupancaPF conta) {
        return new ContaPoupancaPFDTO(conta.getSaldo(), conta.getConta().getCpf());
    }

    public static List<ContaPoupancaPFDTO> of(List<ContaPoupancaPF> contaPoupancaPFS) {
        return contaPoupancaPFS
                .stream()
                .map(ContaPoupancaPFDTO::convert)
                .collect(Collectors.toList());
    }
}
