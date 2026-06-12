package payment_backend.payment.dto.requests;

import jakarta.validation.constraints.NotBlank;

public class VerifyPaymentRequestDto {
    private VerifyPaymentRequestDto(){}
    public record VerifyPaymentRequest(

            @NotBlank(message = "Razorpay Order Id can not be empty")
            String razorpayOrderId,

            @NotBlank(message = "Razorpay Payment Id can not be empty")
            String razorpayPaymentId,

            @NotBlank(message = "Razorpay Signature can not be empty")
            String razorpaySignature
    ){

    }
}
