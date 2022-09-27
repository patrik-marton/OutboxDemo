package com.cloudera.example.outboxdemo.outbox;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * This class uses the {@link ApplicationEventPublisher} interface to encapsulates the event publication functionality.
 */
@Component
public class OutboxEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    /**
     * This method publishes an {@link OutboxEvent}
     * @param outboxEvent - The event to be published
     */
    public void fire(OutboxEvent outboxEvent) {
        this.publisher.publishEvent(outboxEvent);
    }
}
