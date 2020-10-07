FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/filestorage
COPY target/file-storage-2.2.5.RELEASE.jar /usr/local/filestorage
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh