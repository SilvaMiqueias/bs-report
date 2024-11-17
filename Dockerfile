# report/Dockerfile
FROM openjdk:17-slim
VOLUME /tmp
COPY build/libs/report-0.0.1-SNAPSHOT.jar report.jar
ENTRYPOINT ["java", "-jar", "/report.jar"]
