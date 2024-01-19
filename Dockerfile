FROM openjdk:17

WORKDIR /app

COPY target/ScrapingJobs-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]