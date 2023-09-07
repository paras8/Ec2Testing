FROM openjdk:17
COPY target/*.jar tokentesting.jar
EXPOSE 8081
ENTRYPOINT [ "java","-jar","tokentesting.jar" ]