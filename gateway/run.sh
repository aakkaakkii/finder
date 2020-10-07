#!/bin/sh
java -Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT   \
     -Deureka.client.serviceUrl.defaultZone=$DISCOVERY_URI   \
     -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=$DEBUG_PORT \
     -jar /usr/local/gayeway/gateway-2.2.5.RELEASE.jar
