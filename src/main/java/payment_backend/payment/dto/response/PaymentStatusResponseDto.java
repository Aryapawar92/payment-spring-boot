package payment_backend.payment.dto.response;

import payment_backend.payment.entity.Payment;
import payment_backend.payment.entity.PaymentOrder;
import payment_backend.payment.enums.OrderStatus;
import payment_backend.payment.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.Instant;

public class PaymentStatusResponseDto {
    public PaymentStatusResponseDto(){}

    public record PaymentStatusResponse(
            Long orderId,
            String razorpayOrderId,
            String razorpayPaymentId,
            OrderStatus orderStatus,
            PaymentStatus paymentStatus,
            Long amount,
            BigDecimal amountDisplay,
            String currency,
            String paymentMethod,
            Instant paidAt
    )
    {
        public static PaymentStatusResponse from (PaymentOrder paymentOrder, Payment payment){
            return new PaymentStatusResponse(
                    paymentOrder.getId(),
                    paymentOrder.getRazorpayOrderId(),
                    payment.getRazorpayPaymentId(),
                    paymentOrder.getStatus(),
                    payment.getStatus(),
                    payment.getAmount(),
                    paymentOrder.getAmountDisplay(),
                    payment.getCurrency(),
                    payment.getPaymentMethod(),
                    payment.getUpdatedAt()
            );
        }
    }
}
