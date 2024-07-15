FROM amazoncorretto:17-alpine3.17
WORKDIR /app
COPY  pom.xml .
COPY ./src ./src
COPY ./mvnw ./mvnw.cmd .
COPY ./.mvn ./.mvn
RUN chmod ugo+rwx ./mvnw 
RUN ./mvnw clean install spring-boot:repackage -DskipTests
EXPOSE 8080
CMD ["java","-jar","./target/api-springboot-1.0.jar"]
