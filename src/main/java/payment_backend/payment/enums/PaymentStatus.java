package payment_backend.payment.enums;

public enum PaymentStatus {
    PENDING,        // Attempt initiated, awaiting Razorpay response
    AUTHORIZED,     // Authorized but not yet captured (manual capture flow)
    CAPTURED,       // Payment successfully captured — money is moving
    FAILED,         // Payment failed (declined, timeout, etc.)
    REFUNDED,       // Full refund issued
    PARTIALLY_REFUNDED
}
