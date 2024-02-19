[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/mineda/spring3app)

# Exemplo de backend com Spring Boot 3

Aplicação backend usando Spring Boot 3, Spring Data JPA, Spring Web, Spring Security com JWT e SGBD MySQL

## Setup

Crie o Banco de Dados usando o arquivo DDL.sql
No terminal use o comando: mysql < DDL.sql

## Execução

Para executar utilize: mvn spring-boot:run

### Caso tenha algum erro envolvendo versão de Java:
* mvn clean

### Para executar testes: 
* mvn test

### Para executar testes e gerar relatório: 
* mvn test jacoco:report

Para visualizar os testes, abra no navegador o arquivo "index.html" em "/target/site/jacoco"
