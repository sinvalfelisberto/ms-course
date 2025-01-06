#!/bin/bash

# Encontra e finaliza todos os processos iniciados com spring-boot:run
echo "Finalizando todos os projetos Spring Boot..."

# Lista os processos Java relacionados ao Maven Spring Boot
pids=$(ps aux | grep 'spring-boot' | awk '{print $2}')

# Verifica se algum processo foi encontrado
if [ -z "$pids" ]; then
  echo "Nenhum processo Spring Boot encontrado."
else
  # Mata os processos
  echo "Finalizando processos com PIDs: $pids"
  kill -9 $pids
  echo "Todos os projetos foram finalizados."
fi

