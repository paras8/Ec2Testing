FROM openjdk:17
COPY target/*.jar logintesting.jar
EXPOSE 8443
ENTRYPOINT [ "java","-jar","logintesting.jar" ]