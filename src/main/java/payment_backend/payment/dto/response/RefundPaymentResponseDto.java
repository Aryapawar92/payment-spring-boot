package payment_backend.payment.dto.response;

//Long refundId,
//            String razorpayRefundId,
//            String razorpayPaymentId,
//            Long amount,
//            BigDecimal amountDisplay,
//            String currency,
//            Refund.RefundStatus status,
//            String reason,
//            String speedRequested,
//            Instant createdAt

import payment_backend.payment.entity.Refund;
import payment_backend.payment.enums.RefundStatus;

import java.math.BigDecimal;
import java.time.Instant;

public class RefundPaymentResponseDto {
    public RefundPaymentResponseDto(){}

    public record refundPaymentResponse(
            Long refundId,
            String razorpayRefundId,
            String razorpayPaymentId,
            Long amount,
            BigDecimal amountDisplay,
            String currency,
            RefundStatus status,
            String reason,
            String speedRequested,
            Instant createdAt
    ){
        public static refundPaymentResponse from(Refund refund){
            return new refundPaymentResponse(
                    refund.getId(),
                    refund.getRazorpayRefundId(),
                    refund.getPayment().getRazorpayPaymentId(),
                    refund.getAmount(),
                    BigDecimal.valueOf(refund.getAmount()).divide(BigDecimal.valueOf(100)),
                    refund.getCurrency(),
                    refund.getStatus(),
                    refund.getReason(),
                    refund.getSpeedRequested(),
                    refund.getCreatedAt()
            );
        };
    }


}
