package payment_backend.payment.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

// Getter and Setter from lombok to get and set values - pretty self-explanatory
// Validated - to add validation on each value
// ConfigurationProperties - tells spring to check into application.yml/application.properties with prefix "razorpay"


@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "razorpay")
public class RazorpayProperties {
    @NotBlank(message="Razorpay key-id must not be blank")
    private String keyId;
    @NotBlank(message = "Razorpay key-secret must not be blank")
    private String keySecret;
    @NotBlank(message = "Razorpay webhook-secret must not be blank")
    private String webhookSecret;
    @NotBlank(message = "Razorpay currency must not be blank")
    private String currency = "INR";
    @Positive(message = "Timeout must be a positive value")
    private int timeoutMs = 10000;
}
