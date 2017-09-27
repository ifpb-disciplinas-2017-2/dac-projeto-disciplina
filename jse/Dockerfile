FROM payara/micro
ADD target/jse-jar-with-dependencies.jar $PAYARA_PATH/
ENTRYPOINT ["java", "-jar", "jse-jar-with-dependencies.jar"]