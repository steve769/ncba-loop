# Use a base image with Java 21
FROM openjdk:21-jdk-slim

# Add the application JAR file to the container
ADD target/loop-0.0.1-SNAPSHOT.jar loop-ncba.jar

# Specify the command to run your application
CMD ["java", "-jar", "loop-ncba.jar"]