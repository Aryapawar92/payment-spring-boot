package payment_backend.payment.enums;

public enum RefundStatus {
    INITIATED,      // Refund request sent to Razorpay
    PENDING,        // Razorpay acknowledged, processing
    PROCESSED,      // Refund successfully completed
    FAILED          // Refund failed — needs manual intervention
}
