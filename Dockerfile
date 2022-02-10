FROM bellsoft/liberica-openjdk-alpine-musl:17

EXPOSE 8081

CMD echo "********************************************************"
CMD echo "Wait for mongodb to be available"
CMD echo "********************************************************"

CMD echo $MONGODB_STATUS_HOST $MONGODB_STATUS_PORT
CMD while ! nc -z $MONGODB_STATUS_HOST $MONGODB_STATUS_PORT; do \
  printf 'mongodb is still not available. Retrying...\n'; \
  sleep 3; \
done;

CMD echo "********************************************************"
CMD echo "Starting customer-authentication-service"
CMD echo "********************************************************"

CMD java -Dserver.port=$SERVER_PORT \
     -Dspring.data.mongodb.uri=$MONGODB_URI \
     -jar target/customer-authentication-service-0.0.1-SNAPSHOT.jar
