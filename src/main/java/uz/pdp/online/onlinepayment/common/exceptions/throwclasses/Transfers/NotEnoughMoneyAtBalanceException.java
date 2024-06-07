package uz.pdp.online.onlinepayment.common.exceptions.throwclasses.Transfers;

public class NotEnoughMoneyAtBalanceException extends RuntimeException {
    public NotEnoughMoneyAtBalanceException() {
        super("Not enough money at balance");
    }
}
