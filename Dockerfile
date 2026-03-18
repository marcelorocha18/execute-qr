FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/execute-qr-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9497

ENTRYPOINT ["java","-jar","app.jar"]
