package payment_backend.payment.dto.requests;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class RefundPaymentRequestDto {
    public RefundPaymentRequestDto(){}

    public record RefundPaymentRequest(
            @NotBlank(message = "Razorpay payment ID is required")
            String razorpayPaymentId,

            @DecimalMin(value = "1.00", message = "Minimum refund amount is ₹1.00")
            @Digits(integer = 7, fraction = 2, message = "Invalid refund amount format")
            BigDecimal amount,

            @Size(max = 255, message = "Reason must not exceed 255 characters")
            String reason,

            @Pattern(regexp = "normal|optimum", message = "Speed must be 'normal' or 'optimum'")
            String speed
    )
    {

    }
}
