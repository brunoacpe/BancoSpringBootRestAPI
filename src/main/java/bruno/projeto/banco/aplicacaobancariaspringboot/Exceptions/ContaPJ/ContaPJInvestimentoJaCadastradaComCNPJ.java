package bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.ContaPJ;

public class ContaPJInvestimentoJaCadastradaComCNPJ extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Conta investimento já cadastrada com este CNPJ";

    public ContaPJInvestimentoJaCadastradaComCNPJ(){
        this(DEFAULT_MESSAGE);
    }

    public ContaPJInvestimentoJaCadastradaComCNPJ(String message){
        super(message);
    }
}
