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

## Executando o Projeto

Abra este clone do projeto no terminal ou CMD, e execute o seguinte comando:
`docker-compose -f "docker-compose.yml" up -d --build`.

Tenha em mente que é necessário ter instalado em sua máquina o ``Docker com DockerCompose habilitado.

Links:
- API (**localhost:8080**)
- MySQL Server (**localhost:9903**)
- FrontEnd (**localhost:81**)

Antes de abrir no navegador, verifique bem se não há conflitos de porta ou se o projeto ainda esta instalando
sobre os logs do Docker.

Para alterar as portas globais edite em `docker-compose.yml`.
---

## Visualizando a documentação da API

Rode o projeto, para abrir a documentação pelo Swagger abra o link `localhost:8080/swagger-ui.html`.
Também é possível consultar a api em Json pelo end-point `localhost:8080/v2/api-docs`.

## Etapas do Projeto

1. Modelagem e Integração com Banco de dados -> **concluido**
2. Operações de Cadastro -> **concluido**
3. Operações de Listagem -> **concluido**
4. Testes de Unidade -> **concluido**
5. Interface Web -> **concluido**
6. Documentação da API -> **concluido**
7. Configuração para Docker Compose -> **concluido**

8. Autenticação e Autorização -> não concluido por falta de tempo.

## Testes no Postman

Os arquivos de teste no Postman são `Documentacao Swagger.postman_collection.json` e `Transacoes.postman_collection.json`.
Importe para seu Postman para visualizar os testes de execução da API.

## Meu Setup
- Windows 11 Pro (64bits)
- Acer Aspire 3 (Ryzen7 3700u / 12Gb Ram / 512Gb SSD)
- IDE's SpringToolSuite4 / VSCode
- MySQL 8.0.63
- Maven 3.5.8
- Java 17 (Oracle JVM)
- NodeJs 16
- Docker 4.3.2