package uz.pdp.online.onlinepayment.common.exceptions.throwclasses.plasticcard;


public class PlasticCardPhoneNumberNotEqualException extends PlasticCardException {
    public PlasticCardPhoneNumberNotEqualException() {
        super("Registered phone number is different");
    }
}
