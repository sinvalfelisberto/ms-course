#!/bin/bash

# Lista de diretórios dos projetos
projects=(
  "/home/sinvalfelisberto/Desktop/dev/ms-course/hr-eureka-server"
  "/home/sinvalfelisberto/Desktop/dev/ms-course/hr-api-gateway-zuul"
  "/home/sinvalfelisberto/Desktop/dev/ms-course/hr-payroll"
  "/home/sinvalfelisberto/Desktop/dev/ms-course/hr-payroll"
  "/home/sinvalfelisberto/Desktop/dev/ms-course/hr-worker"
  "/home/sinvalfelisberto/Desktop/dev/ms-course/hr-worker"
)

# Loop para iniciar cada projeto
for project in "${projects[@]}"; do
  echo "Iniciando projeto em $project"
  (cd "$project" && ./mvnw spring-boot:run -q &) # Executa em background
done

echo "Todos os projetos foram iniciados."
