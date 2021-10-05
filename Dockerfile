FROM adoptopenjdk/openjdk11:jdk-11.0.9_11-alpine
VOLUME /tmp
EXPOSE 8080
WORKDIR /app
ADD target/*.jar /app/pokemon.jar
ENTRYPOINT ["java", "-jar", "/app/pokemon.jar"]