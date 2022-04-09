# Desafio de Programação p/ Vaga Desenvolvedor

Este é um projeto com base em processo seletivo, veja o desafio proposto [clicando aqui](https://github.com/ByCodersTec/desafio-dev)
---

## Sobre

O desafio deste projeto é, utilizar uma interface Web que possa, através de uma comunicação com
uma API, possa receber o upload de um arquivo TXT possuindo diversas transações que devem ser
devidamente tratadas e armazenadas pelo sistema.

Esta mesma interface WEB deve consultar e mostrar todos as transações registradas. Separados por
loja e totalizando seus devidos saldos em conta.
---

## Etapas do Projeto

1. Modelagem e Integração com Banco de dados -> **concluido**
2. Operações de Cadastro -> não concluido
3. Operações de Listagem -> não concluido
4. Autenticação e Autorização -> não concluido
5. Testes de Unidade -> **concluido**
6. Interface Web -> não concluido
7. Documentação da API -> não concluido
8. Configuração para Docker Compose -> não concluido


## Meu Setup
- Windows 11 Pro (64bits)
- Acer Aspire 3 (Ryzen7 3700u / 12Gb Ram / 512Gb SSD)
- IDE's SpringToolSuite4 / VSCode
- MySQL 8.0.63
- Maven 3.5.8
- Java 17 (Oracle JVM)
- Docker 4.3.2

## Configurações do banco de dados

Ao executar este projeto, configure antes o banco de dados abrindo o arquivo ``src/main/resources/application.properties`, 
para alterar o usuario, host e senha edite as seguintes linhas:
````
# JDBC Uri possui: host, porta e nome do banco de dados
spring.datasource.url=jdbc:mysql://localhost:3306/desafiodev
# Usuario do Banco de dados
spring.datasource.username=root
# Senha
spring.datasource.password=
````

Para a execução devida do projeto, abra seu servidor MySQL e crie o banco de dados:
````
CREATE DATABASE `desafiodev`;
````
De acordo com a configuração no `application.properties`.


Caso já possua o banco de dados pronto com estrutura e dados prontos, troque
a estrategia do Hibernate para `update` ou `validate`:
````
spring.jpa.hibernate.ddl-auto=update
````

## Rodando a aplicação

Clone este projeto, abra a pasta sobre o prompt de comando ou terminal
e execute os seguintes comandos:
````
mvnw clean
mvnw install
java -jar target/desafiodev-0.0.1-SNAPSHOT.jar
````