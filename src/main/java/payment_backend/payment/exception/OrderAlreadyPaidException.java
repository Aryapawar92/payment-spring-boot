package payment_backend.payment.exception;

import org.springframework.http.HttpStatus;

class OrderAlreadyPaidException extends BasePaymentException {
    public OrderAlreadyPaidException(String razorpayOrderId) {
        super("Order is already paid: " + razorpayOrderId, HttpStatus.CONFLICT, "ORDER_ALREADY_PAID");
    }
}
