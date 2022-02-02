FROM bellsoft/liberica-openjdk-alpine-musl:17
#RUN apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/cas
ADD target/customer-authentication-service-0.0.1-SNAPSHOT.jar /usr/local/cas/
ADD run.sh run.sh
EXPOSE 8081
RUN chmod +x run.sh
CMD ./run.sh



# cust-auth-service Dockerfile
# FROM openjdk:17-jdk-alpine

# WORKDIR /customer-authentication-service

# COPY target/*.jar customer-authentication-service.jar
# ADD run.sh run.sh
# EXPOSE 8081

#RUN chmod +x run.sh
#CMD ./run.sh