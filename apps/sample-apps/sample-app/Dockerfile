FROM openjdk:8-jdk-alpine
RUN apk add --update curl
ADD source/build/libs/*.jar app.jar
EXPOSE 8080
CMD ["java","-jar","/app.jar"]
