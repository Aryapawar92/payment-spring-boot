package payment_backend.payment.config;


import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// RequiredArgsConstructor - creates constructor on its on
// Slf4j - logging framework
// Bean - when your spring application starts it automatically runs the function

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(RazorpayProperties.class)
public class RazorpayConfig {
    private final RazorpayProperties razorpayProperties;

    @Bean
    public RazorpayClient razorpayClient(){
        try{
            log.info("Initializing Razorpay Client ...");
            RazorpayClient client = new RazorpayClient(razorpayProperties.getKeyId(),razorpayProperties.getKeySecret());
            log.info("Razorpay Client initialized Successfully");
            return client;
        }
        catch (RazorpayException e){
            log.error("Failed to initialize Client. Check RAZORPAY_KEY_ID and RAZORPAY_KEY_SECRET.", e);
            throw new IllegalStateException("Razorpay client initialization failed.");
        }
    }

}
