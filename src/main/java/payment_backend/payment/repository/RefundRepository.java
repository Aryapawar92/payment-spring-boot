package payment_backend.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import payment_backend.payment.entity.Refund;
import payment_backend.payment.enums.RefundStatus;

import java.util.List;
import java.util.Optional;

// JpaRepository<Refund, Long> -> Entity Class and Primary Key Type;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Long> {

    Optional<Refund> findByRazorpayRefundId(String razorpayRefundId);

    List<Refund> findByPaymentId(Long paymentId);

    @Query("SELECT COALESCE(SUM(r.amount), 0) FROM Refund r WHERE r.payment.id = :paymentId AND r.status = 'PROCESSED' AND r.deleted = false")
    Long sumProcessedRefundsByPaymentId(@Param("paymentId") Long paymentId);

    boolean existsByRazorpayRefundIdAndStatus(String razorpayRefundId, RefundStatus status);
}
