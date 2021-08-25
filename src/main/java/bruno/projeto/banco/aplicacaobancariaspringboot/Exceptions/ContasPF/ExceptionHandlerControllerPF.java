package bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.ContasPF;


import bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.ContaDesativadaException;
import bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@Slf4j
@ControllerAdvice
public class ExceptionHandlerControllerPF {

    @ExceptionHandler(ContaPFJaCadastradaComCPF.class)
    public ResponseEntity<ExceptionResponse> handleContaPFJaCadastradaComCPF(ContaPFJaCadastradaComCPF e){
        return new ResponseEntity<>(
                new ExceptionResponse(e.getMessage(),"Este CPF já foi utilizado para criar uma conta."),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(CPFNaoExistente.class)
    public ResponseEntity<ExceptionResponse> handleCPFNaoExistente(CPFNaoExistente e){
        log.info("CPF não encontrado no banco de dados.");
        return new ResponseEntity<>(
                new ExceptionResponse(e.getMessage(),"Não existe nenhuma conta com este CPF"),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(SaldoInsuficienteContaCorrentePF.class)
    public ResponseEntity<ExceptionResponse> handleSaldoInsuficienteContaCorrentePF(SaldoInsuficienteContaCorrentePF e){
        log.info("Saldo insuficiente para sacar.");
        return new ResponseEntity<>(
                new ExceptionResponse(e.getMessage(),"Saldo insuficiente."),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ContaDesativadaException.class)
    public ResponseEntity<ExceptionResponse> handleContaDesativadaException(ContaDesativadaException e){
        log.info("Conta desativada. Não foi possivel operar.");
        return new ResponseEntity<>(
                new ExceptionResponse(e.getMessage(),"Conta desativada."),
                HttpStatus.BAD_REQUEST
        );
    }

}
