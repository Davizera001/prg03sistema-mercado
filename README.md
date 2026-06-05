# PRG03 Sistema Mercado

Sistema acadêmico em Java com Spring Boot para gerenciamento de produtos de um mercado.

## Sprint 1

### Itens entregues

- Diagrama de classes com mais de 8 relacionamentos
- CRUD da entidade Produto
- Repositório no GitHub
- Banco de dados online no Supabase PostgreSQL

## Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Supabase
- Lombok
- Maven

## CRUD Produto

### Cadastrar produto

```bash
curl -X POST http://localhost:8080/produtos \
-H "Content-Type: application/json" \
-d '{"nome":"Arroz","codigoBarras":"789123","preco":25.90,"estoqueMinimo":10}'