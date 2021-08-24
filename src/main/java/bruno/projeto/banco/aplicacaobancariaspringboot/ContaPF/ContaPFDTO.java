package bruno.projeto.banco.aplicacaobancariaspringboot.ContaPF;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContaPFDTO {

    private String nome;

    private String sobrenome;

    private String email;

    public static ContaPFDTO of(ContaPF contaPF) {
        return new ContaPFDTO(contaPF.getNome()
                , contaPF.getSobrenome(), contaPF.getEmail());
    }

}
