package payment_backend.payment.exception;

import org.springframework.http.HttpStatus;

public class SignatureVerificationException extends BasePaymentException {
    public SignatureVerificationException() {
        super("Payment signature verification failed.", HttpStatus.BAD_REQUEST, "INVALID_SIGNATURE");
    }
}


