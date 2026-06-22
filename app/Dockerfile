# Stage 1: Build the application
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /app
COPY . . 
# Build the jar (assuming Maven)
RUN /mvn clean package -DskipTests

# Stage 2: Run the application (Minimal image)
FROM eclipse-temurin:17-jre
WORKDIR /app
# Copy ONLY the built jar from the builder stage
COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
