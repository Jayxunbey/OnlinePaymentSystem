package uz.pdp.online.onlinepayment.common.exceptions.throwclasses.plasticcard;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PlasticCardNotFoundException extends PlasticCardException {

    public PlasticCardNotFoundException() {
        super("Plastic card not found");
    }

}
