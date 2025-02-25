FROM openjdk:17-jdk-slim

LABEL maintainer="jordyjaja@hotmail.com"
LABEL version="1.0"

WORKDIR /app
COPY target/microservicio1.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]