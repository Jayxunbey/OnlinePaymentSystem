package uz.pdp.online.onlinepayment.common.exceptions.throwclasses.services;

public class ServiceNotFoundException extends RuntimeException {
    public ServiceNotFoundException() {
        super("Service not found");
    }
}
