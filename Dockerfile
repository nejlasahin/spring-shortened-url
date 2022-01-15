FROM adoptopenjdk/openjdk11
WORKDIR app
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=docker"]