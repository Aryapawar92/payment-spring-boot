package payment_backend.payment.exception;

import org.springframework.http.HttpStatus;

public class WebhookSignatureException extends BasePaymentException{
    public WebhookSignatureException(){
        super("Webhook signature verification failed.", HttpStatus.UNAUTHORIZED, "INVALID_WEBHOOK_SIGNATURE");
    }
}
