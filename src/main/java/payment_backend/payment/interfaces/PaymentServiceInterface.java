package payment_backend.payment.interfaces;

import payment_backend.payment.dto.requests.CreateOrderRequestDto;
import payment_backend.payment.dto.requests.VerifyPaymentRequestDto;
import payment_backend.payment.dto.response.CreateOrderResponseDto;
import payment_backend.payment.dto.response.OrderDetailResponseDto;
import payment_backend.payment.dto.response.PaymentStatusResponseDto;

import java.util.List;

public interface PaymentServiceInterface {

    CreateOrderResponseDto.CreateOrderResponse createOrder(CreateOrderRequestDto.CreateOrderRequest request);

    PaymentStatusResponseDto.PaymentStatusResponse verifyPayment(VerifyPaymentRequestDto.VerifyPaymentRequest request);

    OrderDetailResponseDto.OrderDetailResponse getOrderById(Long orderId);

    List<OrderDetailResponseDto.OrderDetailResponse> getOrdersByUser(Long userId);
}
