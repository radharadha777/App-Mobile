#!/usr/bin/env sh

##############################################################################
# Gradle start up script for UN*X
##############################################################################

APP_HOME=$(cd "`dirname "$0"`"; pwd)
APP_NAME="Gradle"
APP_BASE_NAME=`basename "$0"`

# Locate java
if [ -n "$JAVA_HOME" ] ; then
    JAVACMD="$JAVA_HOME/bin/java"
else
    JAVACMD="java"
fi

# Check if Java is available
if [ ! -x "$JAVACMD" ] ; then
  echo "Error: JAVA_HOME is not defined correctly or java is not in PATH."
  exit 1
fi

CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar

exec "$JAVACMD" -cp "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
