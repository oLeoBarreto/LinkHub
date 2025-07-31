# Multi-Stage build strategy, used to create small and secuty images
# because the final image don't have the source code and compilation tools

#eclipse-temurin image with 21 jdk version to build project
FROM eclipse-temurin:21-jdk-jammy AS builder

#Container work dir
WORKDIR /app

#Copy gradlew archives
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

#Copy the code
COPY src src

#Execute the build of project (-x test, will skip the tests) to generate JAR
RUN ./gradlew -x test -x detekt --no-daemon
# -------------------------------------------- #

# Using eclipse-temurin runtime image
FROM eclipse-temurin:21-jre-jammy

#Container work dir
WORKDIR /app

#Copy the JAR created by JDK builder
COPY --from=builder /app/build/libs/*.jar app.jar

#Expose port 8080 for the application in the container
EXPOSE 8080

#Define author and version label
LABEL authors="Leonardo Barreto"
LABEL version="0.2.0"

#Define commands executed when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]