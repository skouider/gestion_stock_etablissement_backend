# Étape 1 : Compilation et packaging de l'application avec Maven
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copie du fichier pom.xml et téléchagement préalable des dépendances (cache Docker)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copie du reste du code source et génération du JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Image d'exécution minimale
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copie du fichier JAR compilé depuis la première étape
COPY --from=build /app/target/*.jar app.jar

# Exposition du port
EXPOSE 8080

# Commande de lancement
ENTRYPOINT ["java", "-jar", "app.jar"]