FROM openjdk:17
COPY targets/*.jar apigateway2.jar
EXPOSE 8082
ENTRYPOINT [ "java","-jar","apigateway2.jar" ]