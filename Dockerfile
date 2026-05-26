FROM eclipse-temurin:21-jdk AS build

WORKDIR /workspace

COPY resume-analyzer/.mvn .mvn
COPY resume-analyzer/mvnw resume-analyzer/pom.xml ./

RUN chmod +x mvnw && ./mvnw -B dependency:go-offline

COPY resume-analyzer/src src

RUN ./mvnw -B clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /workspace/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
