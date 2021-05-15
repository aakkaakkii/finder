FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/authenticationserver
COPY target/auth-server-1.0-SNAPSHOT.jar /usr/local/authenticationserver
ADD run.sh run.sh
RUN chmod +x run.sh
CMD java -Dserver.port=$SERVER_PORT -Deureka.client.serviceUrl.defaultZone=$DISCOVERY_URI \
        -Dspring.datasource.url=$DB_URI \
        -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=$DEBUG_PORT \
        -Dspring.kafka.bootstrap-servers=$KAFKASERVER_URI \
        -jar /usr/local/authenticationserver/auth-server-1.0-SNAPSHOT.jar
