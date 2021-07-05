FROM openjdk:12.0.2

EXPOSE 8000

VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} order-service.jar

ENTRYPOINT ["java","-jar","/order-service.jar"]