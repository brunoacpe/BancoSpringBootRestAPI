package bruno.projeto.banco.aplicacaobancariaspringboot.ContaPJ;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
@Entity
@Table(name="contas_pj")
public class ContaPJ {

    @Id
    private Long CNPJ;

    @Column(name="razao_social")
    private String razaoSocial;

    private String endereco;

    private Long CEP;

    @Column(name = "nome_empresa")
    private String nomeEmpresa;

    private Date data_nascimento;

    private String email;

}
