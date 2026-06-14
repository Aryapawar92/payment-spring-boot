package payment_backend.payment.dto.response;

import payment_backend.payment.entity.Payment;
import payment_backend.payment.entity.PaymentOrder;
import payment_backend.payment.enums.OrderStatus;
import payment_backend.payment.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class OrderDetailResponseDto {
    public OrderDetailResponseDto(){}

    public record OrderDetailResponse(
            Long orderId,
            String razorpayOrderId,
            Long userId,
            Long amount,
            BigDecimal amountDisplay,
            String currency,
            String receipt,
            String description,
            OrderStatus status,
            List<PaymentSummary> payments,
            Instant createdAt,
            Instant updatedAt
    ){
            public static OrderDetailResponse from(PaymentOrder paymentOrder){
                    return new OrderDetailResponse(
                            paymentOrder.getId(),
                            paymentOrder.getRazorpayOrderId(),
                            paymentOrder.getUserId(),
                            paymentOrder.getAmount(),
                            paymentOrder.getAmountDisplay(),
                            paymentOrder.getCurrency(),
                            paymentOrder.getReceipt(),
                            paymentOrder.getDescription(),
                            paymentOrder.getStatus(),
                            paymentOrder.getPayments().stream()
                                    .map(p -> PaymentSummary.from(p))
                                    .toList(),
                            paymentOrder.getCreatedAt(),
                            paymentOrder.getUpdatedAt()
                    );
            }
    }

    public record PaymentSummary(
            Long paymentId,
            String razorpayPaymentId,
            PaymentStatus status,
            String paymentMethod,
            Long amount,
            String errorCode,
            Instant createdAt
    ){
            public static PaymentSummary from(Payment payment){
                return new PaymentSummary(
                        payment.getId(),
                        payment.getRazorpayPaymentId(),
                        payment.getStatus(),
                        payment.getPaymentMethod(),
                        payment.getAmount(),
                        payment.getErrorCode(),
                        payment.getCreatedAt()
                );
            }
    }
}
