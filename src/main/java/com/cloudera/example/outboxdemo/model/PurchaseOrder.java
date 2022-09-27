package com.cloudera.example.outboxdemo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Entity class representation of an incoming Order, that can contain multiple {@link OrderItem}
 *
 */
@Data
@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime orderTime;

    private int price;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "purchaseOrder")
    private List<OrderItem> items;

    protected PurchaseOrder() {
        this.orderTime = LocalDateTime.now();
        this.status = OrderStatus.ORDER_CREATED;
    }

    @JsonCreator
    public PurchaseOrder(@JsonProperty("price") int price, @JsonProperty("items") List<OrderItem> items) {
        this.orderTime = LocalDateTime.now();
        this.price = price;
        this.status = OrderStatus.ORDER_CREATED;
        this.items = new ArrayList<>(items);
        items.forEach(item -> item.setPurchaseOrder( this ));
    }
}
