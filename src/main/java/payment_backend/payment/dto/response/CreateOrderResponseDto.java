package payment_backend.payment.dto.response;

import payment_backend.payment.entity.PaymentOrder;
import payment_backend.payment.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;

public class CreateOrderResponseDto {
    private CreateOrderResponseDto() {}
    public record CreateOrderResponse(
        Long orderId,
        String razorpayOrderId,
        Long amount,
        BigDecimal amountDisplay,
        String currency,
        String receipt,
        OrderStatus status,
        Instant createdAt
    ){
        public static CreateOrderResponse from(PaymentOrder paymentOrder){
            return new CreateOrderResponse(
                paymentOrder.getId(),
                paymentOrder.getRazorpayOrderId(),
                paymentOrder.getAmount(),
                paymentOrder.getAmountDisplay(),
                paymentOrder.getCurrency(),
                paymentOrder.getReceipt(),
                paymentOrder.getStatus(),
                paymentOrder.getCreatedAt()
            );
        }
    }
}
