#!/bin/sh
java -Dserver.port=$SERVER_PORT -Deureka.client.serviceUrl.defaultZone=$DISCOVERY_URI \
    -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=$DEBUG_PORT \
    -Dspring.kafka.bootstrap-servers=$KAFKASERVER_URI \
    -jar /usr/local/mailserver/mail-server-2.2.5.RELEASE.jar \
