# 🏦 Sistema de Gestão Bancária (TBancaria)

API RESTful para gerenciamento de contas bancárias e operações financeiras, desenvolvida com Java e Spring Boot. O sistema permite o cadastro de clientes (Pessoas Físicas), abertura de múltiplos tipos de conta (Corrente e Poupança) via herança/polimorfismo e atualização de saldos.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17+
* **Framework:** Spring Boot 3+
* **Persistência de Dados:** Spring Data JPA / Hibernate
* **Banco de Dados:** Oracle SQL / H2 / PostgreSQL
* **Utilitários:** Lombok (Geração automática de getters/setters/construtores)
* **Design Patterns & Práticas:**
  * Arquitetura em camadas (Controller, Service, Repository, DTO, Model)
  * Mapeamento Objeto-Relacional (ORM) com Estratégia de Herança (`SINGLE_TABLE`)
  * Padronização de respostas de API usando Data Transfer Objects (Records/DTOs)

---

## 📋 Arquitetura e Modelagem

O projeto utiliza o conceito de **herança em banco de dados relacional** gerenciado pelo JPA:

* **PessoaFisica:** Entidade que representa os clientes titulares.
* **Conta:** Entidade base (abstrata/superclasse) contendo atributos comuns como `idConta`, `saldo` e o relacionamento `@ManyToOne` com o titular.
* **ContaCorrente / ContaPoupanca:** Subclasses filhas que herdam da entidade `Conta` utilizando a estratégia `@DiscriminatorColumn(name = "tipo_conta")`.

---

## 🚀 Endpoints da API

### 👤 Gestão de Clientes (Pessoa Física)
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/api/pessoas` | Cadastra um novo cliente |
| `GET` | `/api/pessoas/{id}` | Busca cliente por ID |
| `PUT` | `/api/pessoas/{id}` | Atualiza dados do cliente |
| `DELETE` | `/api/pessoas/{id}` | Deleta cliente do sistema |

### 💳 Gestão de Contas Bancárias
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/api/contas` | Abre uma nova conta (Corrente ou Poupança) vinculada a um titular |
| `GET` | `/api/contas/{id}` | Consulta os detalhes de uma conta específica |
| `PUT` | `/api/contas/{id}/saldo` | Atualiza o saldo de uma conta existente |

---

## 📑 Exemplos de Payload (JSON)

### 1. Criar uma nova conta (`POST /api/contas`)
```json
{
  "idTitular": 1,
  "tipoConta": "CORRENTE",
  "saldoInicial": 1000.00
}
```

**Resposta (`200 OK` / `201 Created`):**
```json
{
  "idConta": 1,
  "nomeTitular": "Roberto Neiva",
  "tipoConta": "CORRENTE",
  "saldo": 1000.00
}
```

### 2. Atualizar saldo da conta (`PUT /api/contas/{id}/saldo`)
```json
{
  "saldo": 2500.50
}
```

---

## 🔧 Como Executar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/tbancaria.git
   ```

2. **Acesse a pasta do projeto:**
   ```bash
   cd tbancaria
   ```

3. **Configure o banco de dados:**
   Verifique o arquivo `src/main/resources/application.properties` para garantir que as credenciais do banco de dados estejam corretas.

4. **Execute a aplicação via Maven:**
   ```bash
   ./mvnw spring-boot:run
   ```
   A API estará acessível em `http://localhost:8080`.

---

## 👨‍💻 Autor

Desenvolvido por **Roberto Neiva Corvino**
