FROM java:8-jdk-alpine
WORKDIR /usr/app
COPY target/rest-service-*.jar /usr/app/rest-service.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "rest-service.jar"]
