#FROM payara/micro
#MAINTAINER Rodrigo Bento <rodrigobentor2014@gmail.com>
#ADD JSON/disciplinas.json $PAYARA_PATH/
#ADD target/app-json-jar-with-dependencies.jar $PAYARA_PATH/
#ENTRYPOINT ["java", "-jar", "app-json-jar-with-dependencies.jar"]
FROM openjdk:8-jdk-alpine
WORKDIR /home
COPY JSON/* JSON/
COPY target/app-json-jar-with-dependencies.jar app-json-jar-with-dependencies.jar
CMD ["java", "-jar", "app-json-jar-with-dependencies.jar"]
