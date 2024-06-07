package uz.pdp.online.onlinepayment.common.exceptions.throwclasses.Transfers;

public class FailedTransactionException extends RuntimeException {
    public FailedTransactionException(String cardNumber) {
        super("Could not transfer money to " + cardNumber);
    }
}
