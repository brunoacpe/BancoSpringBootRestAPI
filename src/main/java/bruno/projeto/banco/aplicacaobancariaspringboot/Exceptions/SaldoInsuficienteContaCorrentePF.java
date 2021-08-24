package bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions;

public class SaldoInsuficienteContaCorrentePF extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Saldo insuficiente para saque.";


    public SaldoInsuficienteContaCorrentePF(){
        this(DEFAULT_MESSAGE);
    }

    public SaldoInsuficienteContaCorrentePF(String message){
        super(message);
    }
}
