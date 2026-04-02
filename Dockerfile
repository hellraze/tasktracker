# Стадия сборки (используем официальный Maven образ с Java 21)
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

# Копируем pom.xml и скачиваем зависимости (кэшируем слой)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Копируем исходники и собираем JAR
COPY src src
RUN mvn clean package -DskipTests -B

# Финальная стадия (лёгкий JRE образ)
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Копируем собранный JAR из стадии builder
COPY --from=builder /app/target/*.jar app.jar

# Точка входа
ENTRYPOINT ["java", "-jar", "app.jar"]