FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/mailserver
COPY target/mail-server-2.2.5.RELEASE.jar /usr/local/mailserver
ADD run.sh run.sh
RUN chmod +x run.sh
CMD java -Dserver.port=$SERVER_PORT -Deureka.client.serviceUrl.defaultZone=$DISCOVERY_URI \
        -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=$DEBUG_PORT \
        -jar /usr/local/mailserver/mail-server-2.2.5.RELEASE.jar
