package payment_backend.payment.exception;

import org.springframework.http.HttpStatus;

class RazorpayApiException extends BasePaymentException {
    public RazorpayApiException(String message, Throwable cause) {
        super("Razorpay API error: " + message, HttpStatus.BAD_GATEWAY, "RAZORPAY_API_ERROR");
        initCause(cause);
    }
}
