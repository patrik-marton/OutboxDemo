# OutboxDemo

Simple SpringBoot application that implements the Transactional Outbox pattern.


### Build and Run
Build with the gradle wrapper:

    ./gradlew clean build
    
Run with docker:

    docker-compose up
    
    
### Database

By default this application comes with dockerized PostreSQL Database server. To use a different database, change the following properties in _application.properties_:

    spring.datasource.url=jdbc:postgresql://postgresqldb:5432/orderdb
    spring.datasource.username=postgres
    spring.datasource.password=postgres
    
    
### Change Data Capture
    
This project does not contain a working Streaming environment, as it is used for demo purposes with Cloudera CDP Public Cloud.

To set up the CDC part of the demo:
1. Create or access a streaming environment with Kafka set up and working.
2. Create a Debezium connector for PostgresSQL (For more information, read the [documentation](https://debezium.io/documentation/reference/stable/connectors/postgresql.html) )

A sample PostgresSQL connector in a Cloudera environment configurations looks like this:

    {
    "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
    "database.history.kafka.bootstrap.servers": "${cm-agent:ENV:KAFKA_BOOTSTRAP_SERVERS}",
    "database.server.name": "debezium",
    "database.hostname": "[***DATABASE HOSTNAME***]",
    "database.password": "[***DATABASE PASSWORD***]",
    "database.dbname": "[***DATABASE NAME***]",
    "database.user": "[***DATABASE USERNAME***]",
    "database.port": "5432",
    "database.server.id": "184054",
    "tasks.max": "1",
    "secret.properties": "database.history.consumer.sasl.jaas.config,database.history.producer.sasl.jaas.config,database.password,producer.override.sasl.jaas.config",
    "database.history.consumer.bootstrap.servers": "${cm-agent:ENV:KAFKA_BOOTSTRAP_SERVERS}",
    "database.history.consumer.sasl.mechanism": "PLAIN",
    "database.history.consumer.sasl.jaas.config": "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"[***USERNAME***]\" password=\"[***PASSWORD***]\";",
    "database.history.consumer.security.protocol": "SASL_SSL",
    "database.history.consumer.ssl.truststore.password": "${cm-agent:ENV:CONNECT_SSL_SERVER_TRUSTSTORE_PASSWORD}",
    "database.history.consumer.ssl.truststore.location": "${cm-agent:ENV:CONNECT_SSL_SERVER_TRUSTSTORE_LOCATION}",
    "database.history.consumer.ssl.truststore.type": "${cm-agent:ENV:KEYSTORE_TYPE}",
    "database.history.consumer.group.id": "debezium",
    "database.history.producer.bootstrap.servers": "${cm-agent:ENV:KAFKA_BOOTSTRAP_SERVERS}",
    "database.history.producer.sasl.mechanism": "PLAIN",
    "database.history.producer.sasl.jaas.config": "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"[***USERNAME***]\" password=\"[***PASSWORD***]\";",
    "database.history.producer.security.protocol": "SASL_SSL",
    "database.history.producer.ssl.truststore.password": "${cm-agent:ENV:CONNECT_SSL_SERVER_TRUSTSTORE_PASSWORD}",
    "database.history.producer.ssl.truststore.location": "${cm-agent:ENV:CONNECT_SSL_SERVER_TRUSTSTORE_LOCATION}",
    "database.history.producer.ssl.truststore.type": "${cm-agent:ENV:KEYSTORE_TYPE}",
    "producer.override.sasl.mechanism": "PLAIN",
    "producer.override.sasl.jaas.config": "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"[***USERNAME***]\" password=\"[***PASSWORD***]\";",
    "producer.override.security.protocol": "SASL_SSL",
    "plugin.name": "pgoutput",
    "table.whitelist": "public.outbox",
    "transforms": "outbox",
    "transforms.outbox.type": "com.cloudera.kafka.connect.debezium.transformer.CustomDebeziumTopicTransformer",
    "slot.name": "slot1"
    }

