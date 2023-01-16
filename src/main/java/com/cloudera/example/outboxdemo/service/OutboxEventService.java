package com.cloudera.example.outboxdemo.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.cloudera.example.outboxdemo.outbox.OutboxMessage;
import com.cloudera.example.outboxdemo.outbox.OutboxEvent;
import com.cloudera.example.outboxdemo.repository.OutboxRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OutboxEventService {

    private final OutboxRepository outBoxRepository;

    @Autowired
    public OutboxEventService(OutboxRepository outBoxRepository) {
        this.outBoxRepository = outBoxRepository;
    }

    /**
     * This method handles every {@link OutboxEvent} fired by the {@link com.cloudera.example.outboxdemo.outbox.OutboxEventPublisher}.
     * Events are persisted into the database for CDC.
     *
     * @param event
     */
    @EventListener
    public void handleOutboxEvent(OutboxEvent event) {
        var uuid = UUID.randomUUID();
        OutboxMessage entity = new OutboxMessage(
                uuid,
                event.getAggregateType(),
                event.getEventType(),
                event.getPayload().toString(),
                new Date()
        );

        log.info("Processing the following event : {}.", entity);

        outBoxRepository.save(entity);
        
        outBoxRepository.delete(entity);
    }
}
