FROM maven:3.9-eclipse-temurin-17 AS builder

# Copy local code to the container image.
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build a release artifact.
RUN mvn -DskipTests package

# Run the web service on container startup.
EXPOSE 8080
CMD ["java","-jar","/app/target/yubi-backend-0.0.1-SNAPSHOT.jar"]
