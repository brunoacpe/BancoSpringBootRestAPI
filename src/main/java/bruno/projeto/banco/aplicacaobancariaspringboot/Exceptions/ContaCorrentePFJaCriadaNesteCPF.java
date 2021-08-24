package bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions;

public class ContaCorrentePFJaCriadaNesteCPF extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Conta corrente já criada com este CPF.";

    public ContaCorrentePFJaCriadaNesteCPF(){
        this(DEFAULT_MESSAGE);
    }

    public ContaCorrentePFJaCriadaNesteCPF(String message){
        super(message);
    }
}
