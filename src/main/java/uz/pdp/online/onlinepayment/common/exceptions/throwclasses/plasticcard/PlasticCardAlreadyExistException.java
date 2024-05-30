package uz.pdp.online.onlinepayment.common.exceptions.throwclasses.plasticcard;

public class PlasticCardAlreadyExistException extends PlasticCardException {
    public PlasticCardAlreadyExistException() {
        super("Plastic card already exist");
    }
}
