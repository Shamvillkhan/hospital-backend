# Use Temurin Java 17 image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml first (for caching dependencies)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Grant execute permission for mvnw
RUN chmod +x mvnw

# Download dependencies only
RUN ./mvnw dependency:go-offline -B

# Copy the source code
COPY src ./src

# Build the project
RUN ./mvnw clean package -DskipTests

# Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Run the Spring Boot jar
CMD ["java", "-jar", "target/hospital-backend-0.0.1-SNAPSHOT.jar"]
