# partimos de uma imagem padrão do JAVA 11 
# para a construção da imagem docker do nosso micro serviço
FROM adoptopenjdk/openjdk11:alpine

# RUN é utilizado para executar comando no "terminal" do micro serviço
# apk update é utilizado para atualizar os 
#    repositórios que o container pode fazer download
# apk add bash é utilizado para adicionar o bash (shell script - linguagem de terminal CMD)
RUN apk update && apk add bash

# criar uma pasta nova dentro do container
RUN mkdir /opt/app

# copiar o arquivo app.jar para a pasta criada anteriormente
COPY target/app.jar /opt/app/app.jar

#abrir (expor) porta para receber começar requisições
EXPOSE 8080

# para executar o jar do nosso micro serviço
CMD [ "java", "-Dspring.profiles.active=docker", "-jar", "/opt/app/app.jar" ]

# docker build -t primeiro-ms .