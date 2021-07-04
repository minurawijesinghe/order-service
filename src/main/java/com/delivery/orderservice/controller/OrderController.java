package com.delivery.orderservice.controller;

import com.delivery.orderservice.common.Payment;
import com.delivery.orderservice.common.TransactionRequest;
import com.delivery.orderservice.common.TransactionResponse;
import com.delivery.orderservice.entity.Order;
import com.delivery.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request){

        return service.saveOrder(request);
    }

}
