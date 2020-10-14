#!/bin/sh
java -Dserver.port=$SERVER_PORT -Deureka.client.serviceUrl.defaultZone=$DISCOVERY_URI \
    -Dspring.datasource.url=$DB_URI \
    -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=$DEBUG_PORT \
    -jar /usr/local/finder/finder-service-1.0-SNAPSHOT.jar