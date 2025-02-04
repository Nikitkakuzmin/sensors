FROM openjdk:17-oracle
LABEL maintainer="nik"
COPY build/libs/sensors.jar sensors.jar
ENTRYPOINT ["java", "-jar", "sensors.jar"]