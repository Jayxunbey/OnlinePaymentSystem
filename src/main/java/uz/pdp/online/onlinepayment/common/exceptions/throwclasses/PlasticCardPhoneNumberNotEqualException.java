package uz.pdp.online.onlinepayment.common.exceptions.throwclasses;


public class PlasticCardPhoneNumberNotEqualException extends RuntimeException {
    public PlasticCardPhoneNumberNotEqualException() {
        super("Phone number is different");
    }
}
