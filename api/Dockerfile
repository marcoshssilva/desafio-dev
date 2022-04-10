FROM openjdk:17
VOLUME /tmp

COPY ./ ./
ENTRYPOINT ["./mvnw", "spring-boot::run"]
EXPOSE 8080