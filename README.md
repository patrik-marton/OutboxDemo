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
