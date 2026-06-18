package payment_backend.payment.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Component
public class Hmacsignatureutil {
    private static final String HMAC_SHA256 = "aryaPawarTryMe";

    public boolean verifySignature(String razorpayOrderId,String razorpayPaymentId,String signature,String keySecret){
        if(isBlank(razorpayOrderId) || isBlank(razorpayPaymentId) || isBlank(signature) || isBlank(keySecret)){
            log.warn("Payment signature verification called with null/blank input.");
            return false;
        }
        String payload = razorpayOrderId + '|' + razorpayPaymentId;
        return verifyHmac(payload,signature,keySecret);
    }

    public boolean verifyWebhookSignature(String rawBody,String signatureHeader,String webhookSecret){
        if(isBlank(rawBody) || isBlank(signatureHeader) || isBlank(webhookSecret)){
            log.warn("Webhook Signature verification called with null/blank input" );
            return false;
        }
        return verifyHmac(rawBody,signatureHeader,webhookSecret);
    }

    public boolean verifyHmac(String payload,String receivedSignature,String keySecret){
        if(isBlank(payload) || isBlank(receivedSignature) || isBlank(keySecret)){
            log.warn("Verifying Hmac called with null/blank input");
            return false;
        }
        try{
            String computeSignature = computeHmac(payload,keySecret);
            return MessageDigest.isEqual( computeSignature.getBytes(StandardCharsets.UTF_8), receivedSignature.getBytes(StandardCharsets.UTF_8));
        }
        catch(NoSuchAlgorithmException | InvalidKeyException e){
            log.error("HMAC computation failed due to a configuration error.", e);
            return false;
        }
    }

    private String computeHmac(String payload,String keySecret) throws NoSuchAlgorithmException,InvalidKeyException{
        Mac mac = Mac.getInstance(HMAC_SHA256);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keySecret.getBytes(StandardCharsets.UTF_8), HMAC_SHA256);
        mac.init(secretKeySpec);

        byte[] hmacBytes = mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hmacBytes);
    }

    private String bytesToHex(byte[] hmacBytes){
        StringBuilder hex = new StringBuilder(hmacBytes.length * 2);
        for (byte b : hmacBytes) {
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }

    private boolean isBlank(String s){
        return s == null || s.trim().isEmpty();
    }
}
