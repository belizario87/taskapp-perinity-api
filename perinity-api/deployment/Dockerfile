# Dockerfile

# Use a base image with Java 17 installed
FROM adoptopenjdk:17-jdk-hotspot

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file to the container
COPY target/perinity-api-0.0.1.jar .

# Expose the port on which the application will run
EXPOSE 8080

# Set environment variables for PostgreSQL
ENV POSTGRES_HOST=localhost
ENV POSTGRES_PORT=5432
ENV POSTGRES_DATABASE=task_manager
ENV POSTGRES_USERNAME=postgres
ENV POSTGRES_PASSWORD=postgres

# Install PostgreSQL client
RUN apt-get update && apt-get install -y postgresql-client

# Set the command to run the application when the container starts
CMD ["java", "-jar", "perinity-api.jar-0.0.1.jar"]





