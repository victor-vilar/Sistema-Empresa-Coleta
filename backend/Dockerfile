FROM amazoncorretto:17-alpine3.17
WORKDIR /app
COPY  pom.xml .
COPY ./src /src
COPY ./mvnw ./mvnw.cmd .
COPY ./.mvn ./.mvn
RUN ./mvnw clean install spring-boot:repackage -DskipTests
CMD ["java","-cp","./target/api-springboot-1.9.jar"]
