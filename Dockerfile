# 1º Estágio: Baixamos a imagem do gradle com Jdk 25 e apelida como BUILD
FROM gradle:jdk25 AS BUILD
# Seleciona o diretório em que será trabalhado
WORKDIR /usuario
# Copio tudo que está na pasta atual "/usuario"
COPY . .
# Utiliza o comando change mode para adicionar uma permissão de executar no terminal o gradlew
RUN chmod +x ./gradlew
# Executa o comando no terminal para compilar o projeto pulando os testes
RUN ./gradlew bootJar -x test

# 2º Estágio: Baixamos a imagem do ambiente linux
FROM eclipse-temurin:25-jre-alpine-3.24
# Entro novamente dentro de /usuario
WORKDIR /usuario
# Copio da pasta do 1º Estagio o snapshot gerado la para o diretorio atual como app
COPY --from=BUILD /usuario/build/libs/*SNAPSHOT.jar app.jar
# Exponho a porta 8080
EXPOSE 8080
# Defino o autor
LABEL authors="gabriel_bia"

# Utilizo o java para executar o jar que acabamos de compilar
ENTRYPOINT ["java", "-jar","app.jar"]