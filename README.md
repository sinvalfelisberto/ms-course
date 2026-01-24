# ms-course

Projeto de **Microservices com Spring Boot, Spring Cloud e Docker**, baseado em um sistema de **HR (Human Resources)**.  
Este repositório contém vários microsserviços independentes que se comunicam entre si usando **Eureka**, **API Gateway (Zuul)**, **OAuth2**, **Config Server** e **PostgreSQL**, todos executando em **containers Docker**.

---

## 🧱 Arquitetura

- **hr-config-server** – Configurações centralizadas (Spring Cloud Config)
- **hr-eureka-server** – Service Discovery
- **hr-api-gateway-zuul** – API Gateway
- **hr-oauth** – Autenticação e autorização (OAuth2 / JWT)
- **hr-user** – Usuários e permissões
- **hr-worker** – Funcionários
- **hr-payroll** – Folha de pagamento
- **PostgreSQL** – Banco de dados (Docker)

---

## 🐳 Docker – Criando e testando containers

### Criar rede Docker
```bash
docker network create hr-net
```

---

## 🗄️ Banco de Dados (PostgreSQL – perfil dev)

### Worker DB
```bash
docker pull postgres:12-alpine

docker run -p 5432:5432 --name hr-worker-pg12 --network hr-net \
-e POSTGRES_PASSWORD=1234567 \
-e POSTGRES_DB=db_hr_worker \
postgres:12-alpine
```

### User DB
```bash
docker run -p 5433:5432 --name hr-user-pg12 --network hr-net \
-e POSTGRES_PASSWORD=1234567 \
-e POSTGRES_DB=db_hr_user \
postgres:12-alpine
```

---

## ⚙️ Build dos microsserviços

Todos os projetos usam **Maven Wrapper**:

```bash
./mvnw clean package
```

Ou sem testes:
```bash
./mvnw clean package -DskipTests
```

---

## 📦 Containers Docker dos Microsserviços

### hr-config-server
```dockerfile
FROM openjdk:11
VOLUME /tmp
EXPOSE 8888
ADD ./target/hr-config-server-0.0.1-SNAPSHOT.jar hr-config-server.jar
ENTRYPOINT ["java","-jar","/hr-config-server.jar"]
```

```bash
docker build -t hr-config-server:v1 .
docker run -p 8888:8888 --name hr-config-server --network hr-net \
-e GITHUB_USER=acenelio \
-e GITHUB_PASS= \
hr-config-server:v1
```

---

### hr-eureka-server
```dockerfile
FROM openjdk:11
VOLUME /tmp
EXPOSE 8761
ADD ./target/hr-eureka-server-0.0.1-SNAPSHOT.jar hr-eureka-server.jar
ENTRYPOINT ["java","-jar","/hr-eureka-server.jar"]
```

```bash
docker build -t hr-eureka-server:v1 .
docker run -p 8761:8761 --name hr-eureka-server --network hr-net hr-eureka-server:v1
```

---

### hr-worker
```dockerfile
FROM openjdk:11
VOLUME /tmp
ADD ./target/hr-worker-0.0.1-SNAPSHOT.jar hr-worker.jar
ENTRYPOINT ["java","-jar","/hr-worker.jar"]
```

```bash
docker build -t hr-worker:v1 .
docker run -P --network hr-net hr-worker:v1
```

---

### hr-user
```dockerfile
FROM openjdk:11
VOLUME /tmp
ADD ./target/hr-user-0.0.1-SNAPSHOT.jar hr-user.jar
ENTRYPOINT ["java","-jar","/hr-user.jar"]
```

```bash
docker build -t hr-user:v1 .
docker run -P --network hr-net hr-user:v1
```

---

### hr-payroll
```dockerfile
FROM openjdk:11
VOLUME /tmp
ADD ./target/hr-payroll-0.0.1-SNAPSHOT.jar hr-payroll.jar
ENTRYPOINT ["java","-jar","/hr-payroll.jar"]
```

```bash
docker build -t hr-payroll:v1 .
docker run -P --network hr-net hr-payroll:v1
```

---

### hr-oauth
```dockerfile
FROM openjdk:11
VOLUME /tmp
ADD ./target/hr-oauth-0.0.1-SNAPSHOT.jar hr-oauth.jar
ENTRYPOINT ["java","-jar","/hr-oauth.jar"]
```

```bash
docker build -t hr-oauth:v1 .
docker run -P --network hr-net hr-oauth:v1
```

---

### hr-api-gateway-zuul
```dockerfile
FROM openjdk:11
VOLUME /tmp
EXPOSE 8765
ADD ./target/hr-api-gateway-zuul-0.0.1-SNAPSHOT.jar hr-api-gateway-zuul.jar
ENTRYPOINT ["java","-jar","/hr-api-gateway-zuul.jar"]
```

```bash
docker build -t hr-api-gateway-zuul:v1 .
docker run -p 8765:8765 --name hr-api-gateway-zuul --network hr-net hr-api-gateway-zuul:v1
```

---

## 🌐 Rotas da API (via API Gateway)

Base URL:
```
http://localhost:8765
```

### Workers
- `GET /hr-worker/workers`
- `GET /hr-worker/workers/{id}`

### Payroll
- `GET /hr-payroll/payments/{workerId}/days/{days}`

### Users
- `GET /hr-user/users/{id}`
- `GET /hr-user/users/search?email=`

### OAuth
- `POST /hr-oauth/oauth/token`

---

## 🔐 Autenticação (OAuth2)

Fluxo padrão:
- **Grant Type:** password
- **Token:** JWT
- Autenticação obrigatória para acessar serviços protegidos via Gateway

---

## 🐳 Comandos Docker úteis

Criar rede:
```bash
docker network create nome-da-rede
```

Baixar imagem:
```bash
docker pull nome-da-imagem:tag
```

Listar imagens:
```bash
docker images
```

Rodar container:
```bash
docker run -p porta_host:porta_container --name nome --network rede imagem:tag
```

Listar containers:
```bash
docker ps
docker ps -a
```

Ver logs:
```bash
docker logs -f nome-do-container
```

---

## 📚 Tecnologias

- Java 11
- Spring Boot
- Spring Cloud (Eureka, Config, Zuul)
- OAuth2 / JWT
- PostgreSQL
- Docker
- Maven

---

## 👨‍💻 Autor

**Sinval Felisberto**  
Projeto educacional para estudo de microsserviços com Spring Cloud.
