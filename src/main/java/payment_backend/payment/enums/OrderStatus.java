package payment_backend.payment.enums;

public enum OrderStatus {
    CREATED,        // Order created in your DB + Razorpay, awaiting payment
    ATTEMPTED,      // At least one payment attempt made, not yet confirmed
    PAID,           // Payment captured successfully
    FAILED,         // All attempts failed
    REFUNDED,       // Payment fully refunded
    PARTIALLY_REFUNDED
}
