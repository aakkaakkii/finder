@ECHO OFF

set COMPOSE_FILE_PATH=%CD%\..\docker\common\docker-compose.yml

IF %1==build_start (
    CALL :down
    CALL :build
    CALL :start
    CALL :tail
    GOTO END
)
IF %1==start (
    CALL :start
    CALL :tail
    GOTO END
)
IF %1==stop (
    CALL :down
    GOTO END
)
IF %1==purge (
    CALL:down
    CALL:purge
    GOTO END
)
IF %1==tail (
    CALL:down
    CALL:purge
    GOTO END
)
echo "Usage: %0  {build_start|start|stop|purge|tail}"
:END
EXIT /B %ERRORLEVEL%

:start
    docker volume create finder-postgres-volume
    docker-compose -f "%COMPOSE_FILE_PATH%" up --build -d
EXIT /B 0
:build
  call mvn -f ../ clean package -Dmaven.test.skip=true
EXIT /B 0
:down
  docker-compose -f "%COMPOSE_FILE_PATH%" down
EXIT /B 0
:down
  docker volume rm -f finder-postgres-volume
EXIT /B 0
:tail
    docker-compose -f "%COMPOSE_FILE_PATH%" logs -f
EXIT /B 0
