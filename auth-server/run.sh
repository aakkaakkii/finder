#!/bin/sh
java -Dserver.port=$SERVER_PORT -Deureka.client.serviceUrl.defaultZone=$DISCOVERY_URI \
    -Dspring.datasource.url=$DB_URI \
    -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=$DEBUG_PORT \
    -Dspring.kafka.bootstrap-servers=$KAFKASERVER_URI \
    -jar /usr/local/authenticationserver/auth-server-1.0-SNAPSHOT.jar
