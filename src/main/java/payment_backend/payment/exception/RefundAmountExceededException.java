package payment_backend.payment.exception;

import org.springframework.http.HttpStatus;

class RefundAmountExceededException extends BasePaymentException {
    public RefundAmountExceededException(long requested, long available) {
        super(
                String.format("Refund amount (%d paise) exceeds available amount (%d paise).", requested, available),
                HttpStatus.BAD_REQUEST,
                "REFUND_AMOUNT_EXCEEDED"
        );
    }
}
