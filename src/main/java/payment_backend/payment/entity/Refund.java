package payment_backend.payment.entity;

import jakarta.persistence.*;

public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @Column(name = "razorpay_refund_id", unique = true, length = 64)
    private String razorpayRefundId;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency = "INR";

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private RefundStatus status = RefundStatus.INITIATED;

    @Column(name = "reason", length = 255)
    private String reason;

    @Column(name = "speed_requested", length = 20)
    private String speedRequested = "normal";

    @Column(name = "speed_processed", length = 20)
    private String speedProcessed;

    public boolean isProcessed() {
        return this.status == RefundStatus.PROCESSED;
    }
}
