package payment_backend.payment.entity;

import jakarta.persistence.*;
import lombok.*;
import payment_backend.payment.enums.PaymentStatus;

// Fetch Lazy - Spring won't load the massive order details into memory unless you explicitly call payment.getPaymentOrder()
// @Builder - is an annotation that automatically implements the Builder Design Pattern for your Java class.

@Entity
@Table(
        name = "payments",
        indexes = {
                @Index(name = "idx_payments_razorpay_payment_id", columnList = "razorpay_payment_id", unique = true),
                @Index(name = "idx_payments_razorpay_order_id", columnList = "razorpay_order_id"),
                @Index(name = "idx_payments_status", columnList = "status"),
                @Index(name = "idx_payments_order_id", columnList = "order_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private PaymentOrder paymentOrder;

    @Column(name = "razorpay_payment_id",nullable = false)
    private String razorpayPaymentId;

    @Column(name = "razorpay_order_id",nullable = false)
    private String razorpayOrderId;

    @Column(name = "razorpay_signature",nullable = false)
    private String razorpaySignature;

    @Column(name = "amount",nullable = false)
    private Long amount;

    @Column(name = "currency", length = 3, nullable = false)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status",nullable = false,length = 20)
    private PaymentStatus status;

    @Column(name = "payment_method",length = 30)
    private String paymentMethod;

    @Column(name = "error_code",length = 64)
    private String errorCode;

    @Column(name = "error_description",length = 512)
    private String errorDescription;

    public boolean isCaptured() {
        return this.status == PaymentStatus.CAPTURED;
    }

    public boolean isFailed() {
        return this.status == PaymentStatus.FAILED;
    }

}
