FROM maven:3.3-jdk-8
EXPOSE 9090

WORKDIR /tmp
ADD . /tmp
RUN mvn install

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/tmp/target/pelicula-loader-1.0.jar"]