package com.cloudera.example.outboxdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudera.example.outboxdemo.model.PurchaseOrder;
import com.cloudera.example.outboxdemo.repository.OrderRepository;
import com.cloudera.example.outboxdemo.service.OrderService;

@RestController
@RequestMapping("/api")
public class OrderResource {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<PurchaseOrder>> getAllOrders() {
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @PostMapping("/orders")
    public PurchaseOrder addOrder(@RequestBody PurchaseOrder purchaseOrder) {
        return orderService.createOrder(purchaseOrder);
    }
}
