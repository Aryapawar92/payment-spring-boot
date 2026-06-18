package payment_backend.payment.service;

import com.razorpay.RazorpayClient;
import lombok.extern.slf4j.Slf4j;
import payment_backend.payment.config.RazorpayProperties;
import payment_backend.payment.dto.requests.CreateOrderRequestDto;
import payment_backend.payment.dto.response.CreateOrderResponseDto;
import payment_backend.payment.exception.DuplicateOrderException;
import payment_backend.payment.interfaces.PaymentServiceInterface;
import payment_backend.payment.repository.PaymentOrderRepository;
import payment_backend.payment.repository.PaymentRepository;
import payment_backend.payment.util.Hmacsignatureutil;

@Slf4j
public class PaymentService implements PaymentServiceInterface {
    private final RazorpayClient razorpayClient;
    private final RazorpayProperties razorpayProperties;
    private final PaymentOrderRepository paymentOrderRepository;
    private final PaymentRepository paymentRepository;
    private final Hmacsignatureutil hmacsignatureutil;

    // ── Create Order ─────────────────────────────────────────────────────────

    /*
     * Full flow:
     *  1. Guard: reject duplicate receipts
     *  2. Convert rupees → paise
     *  3. Call Razorpay to create an order
     *  4. Persist PaymentOrder to DB
     *  5. Return response with razorpayOrderId for the frontend checkout widget
     *
     * @Transactional ensures the DB save either fully commits or rolls back.
     * If Razorpay succeeds but DB save fails, you'll have an orphaned Razorpay
     * order — acceptable, since no money has moved yet. The user can retry.
     */

    @Override
    public CreateOrderResponseDto.CreateOrderResponse createOrder(CreateOrderRequestDto.CreateOrderRequest request){
        log.info("Creating order for user={}, receipt={}", request.userId(), request.receipt());
        if (paymentOrderRepository.existsByReceiptAndDeletedFalse(request.receipt())) {
            log.warn("Duplicate order attempt for receipt={}", request.receipt());
            throw new DuplicateOrderException(request.receipt());
        }
    }
}
