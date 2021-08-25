package bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.ContaPJ;

public class CNPJNãoExistente extends RuntimeException{

    private final static String DEFAULT_MESSAGE = "Nenhuma conta com este CNPJ foi cadastrada.";

    public CNPJNãoExistente(){
        this(DEFAULT_MESSAGE);
    }

    public CNPJNãoExistente(String message){
        super(message);
    }
}
