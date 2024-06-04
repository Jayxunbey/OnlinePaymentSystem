package uz.pdp.online.onlinepayment.common.exceptions.throwclasses.services;

public class FieldsDataEmptyException extends RuntimeException {

    public FieldsDataEmptyException() {
        super("Fields data is Empty");
    }
}
