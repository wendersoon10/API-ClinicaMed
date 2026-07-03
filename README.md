# Clínica Médica API

API REST para gerenciamento de uma clínica médica, desenvolvida com Spring Boot. O sistema permite o cadastro de médicos e pacientes, além do agendamento e cancelamento de consultas com regras de negócio bem definidas e validações desacopladas.

---

## Tecnologias utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security (JWT - stateless)
- Hibernate
- Bean Validation (Jakarta Validation)
- Flyway
- Swagger / OpenAPI
- Lombok
- Banco de dados relacional (MySQL/PostgreSQL)

---

## Arquitetura

O projeto segue arquitetura em camadas:
      controller → service → repository → database


### Conceitos aplicados

- DTOs com `record`
- Value Object (Endereço como embeddable)
- Soft delete (inativação lógica)
- Validações desacopladas (Strategy Pattern)
- Separação por domínio

---

## Segurança

Autenticação via JWT (stateless)

### Login
POST /login


### Uso do token

Todas as requisições protegidas devem conter:
      Authorization: Bearer <token>


---

## Módulo de Médicos

### Funcionalidades

- Cadastro
- Listagem (apenas ativos)
- Atualização
- Detalhamento
- Inativação (soft delete)

### Regras

- Médicos inativos não participam de consultas
- CRM e email não podem ser alterados após criação
- Endereço pode ser atualizado parcialmente

---

## Módulo de Pacientes

### Funcionalidades

- Cadastro
- Listagem
- Atualização
- Detalhamento
- Inativação (soft delete)

---

## Módulo de Consultas

### Funcionalidades

- Agendamento
- Cancelamento
- Seleção automática de médico
- Controle de disponibilidade

---

## Regras de Agendamento

- Consulta deve ser agendada com pelo menos 30 minutos de antecedência
- Horário permitido: 07h às 18h
- Não permitido agendamento aos domingos
- Médico deve estar ativo
- Paciente deve estar ativo
- Médico não pode ter conflito de horário
- Paciente não pode ter mais de uma consulta no mesmo dia

### Seleção automática de médico

Caso o médico não seja informado, o sistema seleciona automaticamente um médico disponível da especialidade informada.

---

## Regras de Cancelamento

- Consulta deve existir
- Cancelamento com pelo menos 24h de antecedência
- Motivo obrigatório
- Cancelamento é lógico (não remove do banco)

### Motivos

- PACIENTE_DESISTIU
- MEDICO_CANCELOU
- OUTROS

---

## Validações

As regras de negócio são desacopladas em validadores.

### Agendamento
    ValidadorAgendamentoDeConsultas

### Cancelamento
    ValidadorCancelamentoDeConsulta


Isso permite adicionar novas regras sem alterar o fluxo principal.

---

## Estrutura de pacotes
med.clinica.api
├── controller
├── domain
│ ├── medicos
│ ├── pacientes
│ ├── consulta
│ ├── endereco
│ └── usuarios
├── infra
│ ├── security
│ ├── exception
│ └── config
└── resources
└── db/migration


---

## Endpoints

### Autenticação


POST /login


### Médicos


POST /medicos
GET /medicos
GET /medicos/{id}
PUT /medicos
DELETE /medicos/{id}


### Pacientes


POST /pacientes
GET /pacientes
GET /pacientes/{id}
PUT /pacientes
DELETE /pacientes/{id}


### Consultas


POST /consultas
DELETE /consultas


---

## Exemplo de agendamento

```json
{
  "idMedico": 1,
  "idPaciente": 2,
  "data": "2026-07-20T10:00:00",
  "especialidade": "CARDIOLOGIA"
}

