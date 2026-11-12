FROM maven:3.9.9-eclipse-temurin-17

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean test

RUN mvn package -DskipTests

CMD ["java", "-cp", "target/shopping-cart-1.0-SNAPSHOT.jar", "com.example.maven_project.ShoppingCart"]
