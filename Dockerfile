FROM openjdk:17-alpine
WORKDIR /5Winyounesbouallegui
RUN ls -la
COPY target/tp-foyer-5.0.0.jar /usr/local/lib/5Winyounesbouallegui.jar
EXPOSE 8080
USER root
ENTRYPOINT ["java", "-jar", "/usr/local/lib/5Winyounesbouallegui.jar"]
