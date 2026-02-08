# Java 17 + Maven already installed
FROM maven:3.9.6-eclipse-temurin-17

# Working directory
WORKDIR /app

# Copy project files
COPY . .

# Build the Spring Boot application
RUN mvn clean package -DskipTests

# Expose port
EXPOSE 8080

# Run the jar
CMD ["java", "-jar", "target/*.jar"]
