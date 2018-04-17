FROM openjdk:8-jdk-alpine
ADD source/build/libs/*.jar app.jar
EXPOSE 8080
CMD ["java","-jar","/app.jar"]
