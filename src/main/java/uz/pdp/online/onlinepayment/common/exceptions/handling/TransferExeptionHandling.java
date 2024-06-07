package uz.pdp.online.onlinepayment.common.exceptions.handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.online.onlinepayment.common.exceptions.throwclasses.Transfers.FailedTransactionException;
import uz.pdp.online.onlinepayment.common.exceptions.throwclasses.Transfers.NotEnoughMoneyAtBalanceException;
import uz.pdp.online.onlinepayment.common.exceptions.throwclasses.plasticcard.PlasticCardNotFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TransferExeptionHandling {

    @ExceptionHandler({FailedTransactionException.class, NotEnoughMoneyAtBalanceException.class})
    public ResponseEntity<Map<String, String>> handlePlasticCardNotFoundException(RuntimeException ex) {
        String message = ex.getMessage();
        Map<String, String> errors = new HashMap<>();
        errors.put("error", message);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errors);
    }

}
