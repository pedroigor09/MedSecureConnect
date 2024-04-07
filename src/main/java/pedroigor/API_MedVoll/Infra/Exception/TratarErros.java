package pedroigor.API_MedVoll.Infra.Exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pedroigor.API_MedVoll.controller.Medical.MedicalRepository;
import pedroigor.API_MedVoll.controller.ValidacaoException;

@RestControllerAdvice
public class TratarErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity TratarErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity TratarErro400(MethodArgumentNotValidException exception){

        var errors = exception.getFieldErrors().stream().map(DetalharErro400::new);

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity TratarErroRegraDeNegocio(ValidacaoException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    public record DetalharErro400(String erro, String mensagem){
        public DetalharErro400(FieldError fieldError){
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
