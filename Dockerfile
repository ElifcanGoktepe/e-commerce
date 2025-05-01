FROM amazoncorretto:21.0.7-alpine3.21
COPY build/libs/e-commerce-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
