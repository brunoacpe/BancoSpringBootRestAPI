package bruno.projeto.banco.aplicacaobancariaspringboot.ContaPJ;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="contas_pj")
public class ContaPJ implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String cnpj;

    @Column(name="razao_social")
    private String razaoSocial;

    private String endereco;

    private String cep;

    @Column(name = "nome_empresa")
    private String nomeEmpresa;

    @Email
    private String email;

}
