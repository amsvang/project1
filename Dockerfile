FROM openjdk:11
COPY build/libs/project1.jar project1.jar
CMD ["java", "-jar", "project1.jar"]