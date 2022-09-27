package com.cloudera.example.outboxdemo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudera.example.outboxdemo.model.PurchaseOrder;
import com.cloudera.example.outboxdemo.outbox.OutboxEvent;
import com.cloudera.example.outboxdemo.outbox.OutboxEventPublisher;
import com.cloudera.example.outboxdemo.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final OutboxEventPublisher eventPublisher;

    @Autowired
    public OrderService(OrderRepository orderRepository, OutboxEventPublisher eventPublisher) {
        this.orderRepository = orderRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public PurchaseOrder createOrder(PurchaseOrder purchaseOrder) {
        // Step 1: Save the order into the database
        var order = orderRepository.save(purchaseOrder);

        // Step 2: Publish an Order Created event
        eventPublisher.fire(OutboxEvent.from(order));

        return order;
    }
}
