package uz.pdp.online.onlinepayment.common.exceptions.handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.AccountException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AccountExceptionHandling {

    @ExceptionHandler({AccountException.class})
    public ResponseEntity<Map<String , String>> handleValidationException(AccountException ex) {
        String message = ex.getMessage();
        Map<String, String> errors = new HashMap<>();
        errors.put("error", message);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errors);
    }
}
