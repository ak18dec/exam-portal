FROM maven:3.8.3-openjdk-17 as maven_build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package


FROM openjdk:17-jdk-alpine
COPY --from=maven_build /app/target/simple-spring-application-0.0.1-SNAPSHOT.jar /usr/local/lib/exam-portal.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/exam-portal.jar"]
