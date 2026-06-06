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

### Cadastrar produto (POST)

```bash
curl -X POST http://localhost:8080/produtos \
-H "Content-Type: application/json" \
-d '{"nome":"Arroz","codigoBarras":"789123","preco":25.90,"estoqueMinimo":10}'
```

### Listar produtos (GET)

```bash
curl http://localhost:8080/produtos
```

### Buscar produto por ID (GET)

```bash
curl http://localhost:8080/produtos/1
```

### Atualizar produto (PUT)

```bash
curl -X PUT http://localhost:8080/produtos/1 \
-H "Content-Type: application/json" \
-d '{"nome":"Arroz Integral","codigoBarras":"789123","preco":29.90,"estoqueMinimo":12}'
```

### Remover produto (DELETE)

```bash
curl -X DELETE http://localhost:8080/produtos/1
```