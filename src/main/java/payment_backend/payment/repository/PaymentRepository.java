package payment_backend.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import payment_backend.payment.entity.Payment;
import payment_backend.payment.entity.PaymentOrder;
import payment_backend.payment.enums.PaymentStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByRazorpayPaymentId(String razorpayPaymentId);

    List<Payment> findByRazorpayOrderId(String razorpayOrderId);

    Optional<Payment> findByRazorpayOrderIdAndStatus(String razorpayOrderId, PaymentStatus status);

    boolean existsByRazorpayPaymentIdAndStatus(String razorpayPaymentId, PaymentStatus status);

    @Query("SELECT p FROM Payment p WHERE p.paymentOrder.id = :orderId AND p.deleted = false ORDER BY p.createdAt DESC")
    List<Payment> findAllByOrderId(@Param("orderId") Long orderId);


}
