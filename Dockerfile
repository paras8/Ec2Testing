FROM openjdk:17
COPY target/*.jar apigateway4.jar
EXPOSE 8083
ENTRYPOINT [ "java","-jar","apigateway4.jar" ]