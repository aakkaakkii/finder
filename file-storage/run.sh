#!/bin/sh
java -Dserver.port=$SERVER_PORT -Deureka.client.serviceUrl.defaultZone=$DISCOVERY_URI \
    -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=$DEBUG_PORT \
    -jar /usr/local/filestorage/file-storage-2.2.5.RELEASE.jar