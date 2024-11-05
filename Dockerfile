# Start with an OpenJDK image
FROM openjdk:21-jdk-slim

# Install dos2unix to handle line ending conversions
RUN apt-get update && apt-get install -y dos2unix

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper and related files
COPY gradlew ./
COPY gradle gradle
COPY build.gradle ./
COPY settings.gradle ./

# Convert gradlew to Unix format
RUN dos2unix gradlew

# Copy the source code into the container
COPY src src

# Run the Gradle build to create the application JAR
RUN ./gradlew build --no-daemon

# Expose the port your app runs on
EXPOSE 8080

# Set the command to run your Spring Boot application
ENTRYPOINT ["java", "-jar", "build/libs/techeer-partners-api-practice-0.0.1-SNAPSHOT.jar"]
