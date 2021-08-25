package bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.ContasPF;

public class CPFNaoExistente extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Nenhuma conta encontrada com este CPF.";

    public CPFNaoExistente(){
        this(DEFAULT_MESSAGE);
    }

    public CPFNaoExistente(String message){
        super(message);
    }
}
