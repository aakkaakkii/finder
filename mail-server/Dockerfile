FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/mailserver
COPY target/mail-server-2.2.5.RELEASE.jar /usr/local/mailserver
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh