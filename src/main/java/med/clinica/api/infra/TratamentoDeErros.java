package med.clinica.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratamentoDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratamentoErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratamentoErro400(MethodArgumentNotValidException e){
        var err = e.getFieldErrors();
        return ResponseEntity.badRequest().body(err.stream().map(DadosErro::new).toList());
    }

    private record DadosErro(String campo, String mensagem){

        public DadosErro(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }

    }
}
