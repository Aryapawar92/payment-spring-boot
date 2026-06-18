package payment_backend.payment.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import payment_backend.payment.dto.response.ApiResponse;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ApiResponse<Void>> handleValidationException(
                MethodArgumentNotValidException ex
        ){
            String message = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(";"));
            log.warn("Validation failed: {}", message);

            return ResponseEntity.badRequest().body(ApiResponse.failure(message,"VALIDATION_ERROR"));
        }

        @ExceptionHandler(BasePaymentException.class)
        public ResponseEntity<ApiResponse<Void>> handlePaymentException(
                BasePaymentException ex
        ){
            if (ex.httpStatus().is5xxServerError()) {
                log.error("Payment error [{}]: {}", ex.errorCode(), ex.getMessage(), ex);
            } else {
                log.warn("Payment error [{}]: {}", ex.errorCode(), ex.getMessage());
            }

            return ResponseEntity.status(ex.httpStatus()).body(ApiResponse.failure(ex.getMessage(), ex.errorCode()));
        }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnexpectedException(Exception ex) {
        log.error("Unexpected error: {}", ex.getMessage(), ex);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.failure(
                        "An unexpected error occurred. Please try again later.",
                        "INTERNAL_ERROR"
                ));
    }
}
