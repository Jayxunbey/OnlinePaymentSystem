package uz.pdp.online.onlinepayment.common.exceptions.throwclasses;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PlasticCardNotFoundException extends RuntimeException {

    public PlasticCardNotFoundException() {
        super("Plastic card not found");
    }

}
