# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the application's jar file into the container at /app
COPY target/Getway-0.0.1-SNAPSHOT.jar /app/app.jar

# Make port 9091 available to the world outside this container
EXPOSE 9091

# Run the jar file
ENTRYPOINT ["java","-jar","/app/app.jar"]
