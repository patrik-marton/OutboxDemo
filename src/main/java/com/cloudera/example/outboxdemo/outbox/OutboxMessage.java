package com.cloudera.example.outboxdemo.outbox;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OUTBOX")
public class OutboxMessage {

    @Id
    @Column(name = "uuid")
    private UUID id;

    @Column(name = "aggregateType")
    private String aggregateType;

    @Column(name = "eventType")
    private String eventType;

    @Column(name = "payload")
    private String payload;

    @Column(name = "createdOn")
    private Date createdOn;
}
