package bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.ContasPF;

public class ContaPFJaCadastradaComCPF extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Uma conta PF já foi cadastrada com este CPF.";

    public ContaPFJaCadastradaComCPF() {
        this(DEFAULT_MESSAGE);
    }

    public ContaPFJaCadastradaComCPF(String message) {
        super(message);
    }
}
