FROM openjdk:17
COPY target/*.jar logintesting.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar","logintesting.jar" ]