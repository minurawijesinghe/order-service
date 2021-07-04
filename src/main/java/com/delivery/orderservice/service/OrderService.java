package com.delivery.orderservice.service;

import com.delivery.orderservice.common.Payment;
import com.delivery.orderservice.common.TransactionRequest;
import com.delivery.orderservice.common.TransactionResponse;
import com.delivery.orderservice.entity.Order;
import com.delivery.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    @Autowired
    private RestTemplate template;
    public TransactionResponse saveOrder(TransactionRequest request){
        String response ="";
        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        Payment paymentResponse = template.postForObject("http://localhost:9191/payment/doPayment", payment,Payment.class);
        response=paymentResponse.getPaymentStatus().equals("success")?"Payment processing successful and order placed":"There is a failure in payment api, order added to cart";
        repository.save(order);
         return new TransactionResponse(order,paymentResponse.getTransactionId(), paymentResponse.getAmount(), response);
    }
}
