package bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.ContasPF;

public class SaldoInsuficienteParaSaque extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Saldo insuficiente para saque.";


    public SaldoInsuficienteParaSaque(){
        this(DEFAULT_MESSAGE);
    }

    public SaldoInsuficienteParaSaque(String message){
        super(message);
    }
}
