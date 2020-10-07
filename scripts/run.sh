#!/bin/bash
export COMPOSE_FILE_PATH=${PWD}/../docker/common/docker-compose.yml

start() {
  docker volume create finder-postgres-volume
  docker-compose -f "$COMPOSE_FILE_PATH" up --build -d
}

build() {
  mvn -f ../ clean package -Dmaven.test.skip=true
}

down() {
  docker-compose -f "$COMPOSE_FILE_PATH" down
}

purge() {
  docker volume rm -f finder-postgres-volume
}

tail() {
    docker-compose -f "$COMPOSE_FILE_PATH" logs -f
}

case "$1" in
  build_start)
    down
    build
    start
    tail
    ;;
  start)
    start
    tail
    ;;
  stop)
    down
    ;;
  purge)
    down
    purge
    ;;
  tail)
    tail
    ;;
  *)
    echo "Usage: $0 {build_start|start|stop|purge|tail}"
esac