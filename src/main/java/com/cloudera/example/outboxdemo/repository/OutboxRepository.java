package com.cloudera.example.outboxdemo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cloudera.example.outboxdemo.outbox.OutboxMessage;

@Repository
public interface OutboxRepository extends CrudRepository<OutboxMessage, Integer> {

}
