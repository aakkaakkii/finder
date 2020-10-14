FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/finder
COPY target/finder-service-1.0-SNAPSHOT.jar /usr/local/finder
ADD run.sh run.sh
RUN chmod +x run.sh
CMD java -Dserver.port=$SERVER_PORT -Deureka.client.serviceUrl.defaultZone=$DISCOVERY_URI \
        -Dspring.datasource.url=$DB_URI \
        -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=$DEBUG_PORT \
        -jar /usr/local/finder/finder-service-1.0-SNAPSHOT.jar
