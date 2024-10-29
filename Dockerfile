FROM gradle:jdk17 AS build

WORKDIR /home/gradle/project

COPY build.gradle settings.gradle /home/gradle/project/
COPY src /home/gradle/project/src

RUN gradle clean build -x test --no-daemon

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /home/gradle/project/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
