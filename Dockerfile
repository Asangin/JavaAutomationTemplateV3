# Use a Maven image with JDK 21
FROM eclipse-temurin:21-jdk as builder

# Set the working directory
WORKDIR /app

# Copy the pom.xml file and resolve dependencies (faster subsequent builds)
ADD .mvn/ .mvn/
COPY mvnw .
COPY pom.xml .

RUN echo "Listing contents of /app:" && ls -l /app

RUN ./mvnw dependency:go-offline

# Copy the entire project source
COPY . .

# Build the project and generate test reports
RUN ./mvnw test-compile

# Optional: Use JDK slim image for just running tests separately
#FROM eclipse-temurin:21-jre

#COPY --from=builder /app/target /app/

CMD ["./mvnw", "test"]
