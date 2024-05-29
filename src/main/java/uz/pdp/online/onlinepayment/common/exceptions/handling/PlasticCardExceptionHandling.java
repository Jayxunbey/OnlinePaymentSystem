package uz.pdp.online.onlinepayment.common.exceptions.handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.online.onlinepayment.common.exceptions.throwclasses.PlasticCardNotFoundException;
import uz.pdp.online.onlinepayment.common.exceptions.throwclasses.PlasticCardPhoneNumberNotEqualException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class PlasticCardExceptionHandling {

    @ExceptionHandler({PlasticCardNotFoundException.class})
    public ResponseEntity<Map<String, String>> handlePlasticCardNotFoundException(PlasticCardNotFoundException ex) {
        String message = ex.getMessage();
        Map<String, String> errors = new HashMap<>();
        errors.put("error", message);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler({PlasticCardPhoneNumberNotEqualException.class})
    public ResponseEntity<Map<String, String>> handlePlasticCardNotFoundException(PlasticCardPhoneNumberNotEqualException ex) {
        String message = ex.getMessage();
        Map<String, String> errors = new HashMap<>();
        errors.put("error", message);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errors);
    }

}
