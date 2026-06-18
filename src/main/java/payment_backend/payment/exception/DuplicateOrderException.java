package payment_backend.payment.exception;

import org.springframework.http.HttpStatus;

public class DuplicateOrderException extends BasePaymentException {
    public DuplicateOrderException(String receipt) {
        super("An order already exists for receipt: " + receipt, HttpStatus.CONFLICT, "DUPLICATE_ORDER");
    }
}
