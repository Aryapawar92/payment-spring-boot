package payment_backend.payment.exception;

import org.springframework.http.HttpStatus;


public class BasePaymentException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String errorCode;

    public BasePaymentException(String message,HttpStatus httpStatus,String errorCode){
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }

    public HttpStatus httpStatus(){return httpStatus;}
    public String errorCode(){return errorCode;}

}
