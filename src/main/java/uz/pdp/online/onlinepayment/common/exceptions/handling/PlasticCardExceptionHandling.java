package uz.pdp.online.onlinepayment.common.exceptions.handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.online.onlinepayment.common.exceptions.throwclasses.plasticcard.PlasticCardAlreadyExistException;
import uz.pdp.online.onlinepayment.common.exceptions.throwclasses.plasticcard.PlasticCardException;
import uz.pdp.online.onlinepayment.common.exceptions.throwclasses.plasticcard.PlasticCardNotFoundException;
import uz.pdp.online.onlinepayment.common.exceptions.throwclasses.plasticcard.PlasticCardPhoneNumberNotEqualException;

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

    @ExceptionHandler({PlasticCardPhoneNumberNotEqualException.class, PlasticCardAlreadyExistException.class})
    public ResponseEntity<Map<String, String>> handlePlasticCardNotFoundException(PlasticCardException ex) {
        String message = ex.getMessage();
        Map<String, String> errors = new HashMap<>();
        errors.put("error", message);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errors);
    }

}
