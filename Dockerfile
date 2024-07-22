# Usar uma imagem base do OpenJDK
FROM openjdk:8-jre-slim

# Adicionar um volume apontando para /tmp
VOLUME /tmp

# Copiar o JAR para o container
COPY target/*.jar app.jar

# Configurar a porta que o container vai expor
EXPOSE 8080

# Executar o JAR
ENTRYPOINT ["java","-jar","/app.jar"]
