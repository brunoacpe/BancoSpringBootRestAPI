package bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.ContaPJ;

public class ContaPJCadastradaComEsteCNPJ extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Este CNPJ já foi cadastrado em outra conta.";

    public ContaPJCadastradaComEsteCNPJ(){
        this(DEFAULT_MESSAGE);
    }

    public ContaPJCadastradaComEsteCNPJ(String message){
        super(message);
    }
}
