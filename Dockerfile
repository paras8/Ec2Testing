FROM openjdk:17
COPY target/*.jar apigateway3.jar
EXPOSE 8083
ENTRYPOINT [ "java","-jar","apigateway3.jar" ]