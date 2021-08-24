package bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions;

public class ContaDesativadaException extends RuntimeException{

    private final static String DEFAULT_MESSAGE = "Esta conta esta desativada no nosso sistema.";

    public ContaDesativadaException(){
        this(DEFAULT_MESSAGE);
    }

    public ContaDesativadaException(String message){
        super(message);
    }
}
