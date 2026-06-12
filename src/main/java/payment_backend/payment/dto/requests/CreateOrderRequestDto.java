package payment_backend.payment.dto.requests;

import jakarta.validation.constraints.*;


import java.math.BigDecimal;

public class CreateOrderRequestDto {
    private CreateOrderRequestDto() {}
    public record CreateOrderRequest(

            @NotNull(message = "amount is required")
            @DecimalMin(value ="1.00",message = "Minimum Order Amount is ₹1.00")
            @DecimalMax(value = "500000.00",message = "Maximum Order Amount ₹5,00,000.00")
            @Digits(integer = 7,fraction = 2,message = "Invalid Amount Format")
            BigDecimal amount,

            @NotNull(message = "User Id is Required")
            @Positive(message = "User Id must me a positive number")
            Long userId,

            @NotBlank(message = "Receipt is required")
            @Size(max = 40, message = "Receipt must not exceed 40 characters")
            String receipt,

            @Size(max = 255, message = "Description must not exceed 255 characters")
            String description
    )
    {}

}
