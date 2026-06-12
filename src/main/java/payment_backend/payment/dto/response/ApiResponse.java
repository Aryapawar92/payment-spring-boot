package payment_backend.payment.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.Instant;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
        boolean success,
        String message,
        T data,
        ErrorDetails error,
        Instant timestamp
) {
    public static <T> ApiResponse<T> success(T data){
        return ApiResponse.<T>builder()
                .success(true)
                .data(data)
                .timestamp(Instant.now())
                .build();
    }

    public static <T> ApiResponse<T> success(T data,String message){
        return ApiResponse.<T>builder()
                .success(true)
                .data(data)
                .message(message)
                .timestamp(Instant.now())
                .build();
    }

    public static <T> ApiResponse<T> failure(String message,String errorCode){
        return ApiResponse.<T>builder()
                .success(false)
                .error(new ErrorDetails(errorCode,message))
                .timestamp(Instant.now())
                .build();
    }

    public record ErrorDetails(
            String code,
            String message
    ){

    }

}
