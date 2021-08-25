package bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.ContaPJ;

import bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerControllerPJ {

    @ExceptionHandler(ContaPJCadastradaComEsteCNPJ.class)
    public ResponseEntity<ExceptionResponse> handleContaPJCadastradaComEsteCNPJ(
            ContaPJCadastradaComEsteCNPJ e
    ){
        return new ResponseEntity<>(
                new ExceptionResponse(e.getMessage(),"CNPJ já utilizado para cadastrar outra conta."),
                HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(ContaPJInvestimentoJaCadastradaComCNPJ.class)
    public ResponseEntity<ExceptionResponse> handleContaPJInvestimentoJaCadastradaComCNPJ(ContaPJInvestimentoJaCadastradaComCNPJ ex){
        return new ResponseEntity<>(
                new ExceptionResponse(ex.getMessage(), "Conta Investimento já cadastrada neste CNPJ."),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(CNPJNãoExistente.class)
    public ResponseEntity<ExceptionResponse> handleCNPJNãoExistente(CNPJNãoExistente ex){
        return new ResponseEntity<>(
                new ExceptionResponse(ex.getMessage(),"Nenhuma conta PJ com este CNPJ."),
                HttpStatus.BAD_REQUEST
        );
    }
}
