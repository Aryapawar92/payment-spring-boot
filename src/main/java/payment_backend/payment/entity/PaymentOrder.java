package payment_backend.payment.entity;

import com.razorpay.Payment;
import jakarta.persistence.*;
import lombok.*;
import payment_backend.payment.enums.OrderStatus;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "payment_orders",
        indexes = {
                @Index(name = "idx_payment_orders_razorpay_order_id", columnList = "razorpay_order_id", unique = true),
                @Index(name = "idx_payment_orders_user_id", columnList = "user_id"),
                @Index(name = "idx_payment_orders_status", columnList = "status")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentOrder extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "razorpay_order_id",nullable = false,unique = true,length = 64)
    private String razorpayOrderId;

    @Column(name = "amount",nullable = false)
    private Long amount;

    @Column(name = "amount_display",nullable = false,precision = 12,scale = 2)
    private BigDecimal amountDisplay;

    @Column(name = "currency",nullable = false,length = 3)
    private String currency = "INR";

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status",nullable = false,length = 20)
    private OrderStatus status = OrderStatus.CREATED;

    @Column(name = "user_id",nullable = false)
    private Long userId;

    @Column(name = "receipt", length = 40)
    private String receipt;

    @Column(name = "description", length = 255)
    private String description;

    @OneToMany(mappedBy = "paymentOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Payment> payments = new ArrayList<>();

    public void addPayment(Payment payment){
        payments.add(payment);
//        payments.setPaymentOrder(this);

    }

    public boolean isPaid() {
        return this.status == OrderStatus.PAID;
    }

    public boolean isFailed() {
        return this.status == OrderStatus.FAILED;
    }


}
