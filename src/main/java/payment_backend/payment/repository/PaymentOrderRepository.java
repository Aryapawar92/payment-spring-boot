package payment_backend.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import payment_backend.payment.entity.PaymentOrder;
import payment_backend.payment.enums.OrderStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long> {
    Optional<PaymentOrder> findByRazorpayOrderId(String razorpayOrderId);

    List<PaymentOrder> findByUserIdAndDeletedFalseOrderByCreatedAtDesc(Long userId);

    List<PaymentOrder> findByStatusAndDeletedFalse(OrderStatus status);

    boolean existsByReceiptAndDeletedFalse(String receipt);

    @Query("SELECT o FROM PaymentOrder o LEFT JOIN FETCH o.payments WHERE o.id = :id AND o.deleted = false")
    Optional<PaymentOrder> findByIdWithPayments(@Param("id") Long id);


}
