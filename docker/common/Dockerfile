# Use a Maven image with JDK 21
FROM maven:3.9.9-eclipse-temurin-21 as builder

# Set the working directory
WORKDIR /app

ENV env=docker

COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the entire project source
COPY . .

# Build the project and generate test reports
RUN mvn test-compile

# Optional: Use JDK slim image for just running tests separately
#FROM eclipse-temurin:21-jre

#COPY --from=builder /app/target /app/
# -o offline
CMD mvn test -o -Dsurefire.suiteXmlFiles=$SUITE_XML_FILE