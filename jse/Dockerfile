FROM payara/micro
ADD target/jse-jar-with-dependencies.jar $PAYARA_PATH/
ENTRYPOINT ["java", "-jar", "jse-jar-with-dependencies.jar"]
#FROM openjdk:8-jdk-alpine
#WORKDIR /home
#COPY target/jse-jar-with-dependencies.jar jse-jar-with-dependencies.jar
#CMD ["java", "-jar", "jse-jar-with-dependencies.jar"]
