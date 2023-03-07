FROM maven:3.8.3-openjdk-17 as maven_build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -Dmaven.test.skip -DskipTests


FROM openjdk:17-jdk-alpine
COPY --from=maven_build /home/app/target/exam-portal.jar /usr/local/lib/exam-portal.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/exam-portal.jar"]
