package payment_backend.payment.exception;

import org.springframework.http.HttpStatus;

class PaymentNotFoundException extends BasePaymentException {
    public PaymentNotFoundException(String identifier) {
        super("Payment not found: " + identifier, HttpStatus.NOT_FOUND, "PAYMENT_NOT_FOUND");
    }
}
