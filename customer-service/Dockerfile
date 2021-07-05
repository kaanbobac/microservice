FROM openjdk:12.0.2

EXPOSE 9000
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} customer-service.jar

ENTRYPOINT ["java","-jar","/customer-service.jar"]