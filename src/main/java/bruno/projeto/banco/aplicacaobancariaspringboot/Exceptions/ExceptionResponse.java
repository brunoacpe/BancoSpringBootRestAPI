package bruno.projeto.banco.aplicacaobancariaspringboot.Exceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ExceptionResponse extends RuntimeException{

    private LocalDateTime timeOccurrence = LocalDateTime.now();
    private String exceptionMessage;
    private String details;

    public ExceptionResponse(String exceptionMessage, String details) {
        this.exceptionMessage = exceptionMessage;
        this.details = details;
    }
}
