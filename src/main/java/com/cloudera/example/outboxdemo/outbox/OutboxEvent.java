package com.cloudera.example.outboxdemo.outbox;

import com.cloudera.example.outboxdemo.model.OrderStatus;
import com.cloudera.example.outboxdemo.model.PurchaseOrder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Class that represents the Outbox Events fired inside the application.
 * @aggregateId - Id
 * @eventType - The actual type of the fired event
 * @payload - The message about to be sent to the Kafka topic
 */
@Data
@AllArgsConstructor
public class OutboxEvent {

    private String eventType;

    private String aggregateType;

    private JsonNode payload;

    public static OutboxEvent from(PurchaseOrder order) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        JsonNode jsonNode = mapper.convertValue(order, JsonNode.class);

        return new OutboxEvent(
                OrderStatus.ORDER_CREATED.toString(),
                OutboxAggregateType.ORDER.toString(),
                jsonNode
        );
    }
}