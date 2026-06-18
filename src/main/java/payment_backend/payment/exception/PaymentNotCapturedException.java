package payment_backend.payment.exception;

import org.springframework.http.HttpStatus;

class PaymentNotCapturedException extends BasePaymentException {
    public PaymentNotCapturedException(String razorpayPaymentId) {
        super(
                "Cannot refund payment that is not captured: " + razorpayPaymentId,
                HttpStatus.BAD_REQUEST,
                "PAYMENT_NOT_CAPTURED"
        );
    }
}
