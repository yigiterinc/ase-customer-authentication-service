#!/bin/sh

echo "********************************************************"
echo "Wait for mongodb to be available"
echo "********************************************************"

echo $MONGODB_STATUS_HOST $MONGODB_STATUS_PORT
while ! nc -z $MONGODB_STATUS_HOST $MONGODB_STATUS_PORT; do
  printf 'mongodb is still not available. Retrying...\n'
  sleep 3
done

echo "********************************************************"
echo "Starting myapp"
echo "********************************************************"

java -Dserver.port=$SERVER_PORT \
     -Dspring.data.mongodb.uri=$MONGODB_URI \
     -jar /usr/local/cas/customer-authentication-service-0.0.1-SNAPSHOT.jar
