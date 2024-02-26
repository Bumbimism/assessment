
FROM amazoncorretto:17.0.9-alpine3.18 as builder
WORKDIR /app
ADD .  .
CMD ["./gradlew","bootJar"]

FROM gcr.io/distroless/java17-debian12:latest
WORKDIR /app
COPY --from=builder /app/build/libs/assessment-0.0.1-SNAPSHOT.jar demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/build/libs/assessment-0.0.1-SNAPSHOT.jar"]