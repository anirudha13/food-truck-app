#!/bin/bash

JAVA=$(which java)
FOODTRUCKMAPPER_JAR="target/foodtruckmap-0.1.0-SNAPSHOT.jar"

RUN_JAVA_OPTS="-Xmx1024m -XX:MaxPermSize=256m"

COMMAND="$JAVA $RUN_JAVA_OPTS -jar $FOODTRUCKMAPPER_JAR"

$COMMAND "$@"


