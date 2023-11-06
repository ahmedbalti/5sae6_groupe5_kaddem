FROM openjdk:8-jre-alpine
EXPOSE 8089
ADD target/kaddem-0.0.1-SNAPSHOT.jar kaddem.jar
ENTRYPOINT ["java", "-jar", "/kaddem.jar"]
