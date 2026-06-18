package payment_backend.payment.exception;


import org.springframework.http.HttpStatus;

public class OrderNotFoundException extends BasePaymentException{
    public OrderNotFoundException(String identifier){
        super("Order not found: " + identifier, HttpStatus.NOT_FOUND, "ORDER_NOT_FOUND");
    }
}
