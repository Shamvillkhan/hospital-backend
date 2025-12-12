# Multi-stage build for smallest possible image

# Build stage

FROM eclipse-temurin:17-jdk-alpine AS builder



WORKDIR /app



# Install Maven (better than mvnw in container)

RUN apk add --no-cache maven



# Copy pom.xml first (layer caching optimization)

COPY pom.xml .

RUN mvn dependency:go-offline -B



# Copy source and build

COPY src ./src

RUN mvn clean package -DskipTests \

    && java -Djarmode=layertools -jar target/*.jar extract



# Runtime stage - ultra small

FROM eclipse-temurin:17-jre-alpine



RUN addgroup -S spring && adduser -S spring -G spring

USER spring:spring



WORKDIR /app



# Copy extracted layers from builder

COPY --from=builder /app/dependencies/ ./

COPY --from=builder /app/spring-boot-loader/ ./

COPY --from=builder /app/snapshot-dependencies/ ./

COPY --from=builder /app/application/ ./



EXPOSE 8080



# Health check (adjust endpoint based on your app)

HEALTHCHECK --interval=30s --timeout=3s --start-period=30s --retries=3 \

  CMD wget --quiet --tries=1 --spider http://localhost:8080/actuator/health || exit 1



# Optimized JVM for containers

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:+UseG1GC -XX:+ExitOnOutOfMemoryError"



ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} org.springframework.boot.loader.JarLauncher"]