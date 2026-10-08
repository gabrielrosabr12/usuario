#1º Container temporário: baixa a imaagem do gradle com jdk25 e apelida como build
FROM gradle:jdk25 AS build

# Cria uma pasta se não existir de app e entra dentro dela
WORKDIR /app

# Copia os arquivos de configuração e o script do gradlew para \
# dentro da pasta que acabamos de criar no container
COPY ./settings.gradle ./build.gradle ./gradlew ./
# Copia a pasta do código no computador local para dentro de /app/src no container
COPY src src
# Copia a pasta do gradle para dentro da pasta app no container
COPY gradle gradle
# Roda o change mode adicionando a permissão de execução do script gradlew
RUN chmod +x ./gradlew
# Roda o script gradlew com parametro de baixar as depências do build.gradle mas sem compilar
# Parametro --no-daemon é para mostrar todo o processo no terminal
RUN ./gradlew dependencies --no-daemon
# Roda o script gradlew para compilar o projeto spring e gerar o .jar pulando os testes
RUN ./gradlew bootJar -x test


# 2º Container da aplicacao: Baixa o eclipse temurin pois nela contém o \
# kit de desenvolvimento java utilizando SO alpine que é super leve
FROM eclipse-temurin:25-jre-alpine

# Criamos a pasta /app dentro desse container
WORKDIR /app

# Copiamos la do primeiro container o jar que geramos
COPY --from=build /app/build/libs/*SNAPSHOT.jar app.jar

# Colocamos etiqueta de autor nesse container
LABEL authors="gabriel_rosa"

# Colocamos a etiqueta da porta que essa aplicação usa dentro desse container
EXPOSE 8080

# Execuçaõ da aplicação
ENTRYPOINT ["java","-jar","app.jar"]