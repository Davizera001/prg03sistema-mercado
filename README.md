# PRG03 Sistema Mercado

Sistema acadêmico desenvolvido em Java com Spring Boot para gerenciamento de produtos de um mercado.

O projeto possui uma API REST para o CRUD da entidade Produto e também uma interface gráfica em Swing conectada ao banco de dados online.

## Sprint 1

### Itens entregues

- Diagrama de classes com mais de 8 relacionamentos
- CRUD completo da entidade Produto
- Interface gráfica em Swing para gerenciamento de produtos
- API REST para Produto
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
- Swing

## Funcionalidades implementadas

### Produto

- Cadastrar produto
- Listar produtos
- Pesquisar produtos por nome
- Editar produto
- Remover produto
- Visualizar dados pela API REST

## Banco de dados

O sistema utiliza um banco de dados PostgreSQL hospedado no Supabase.

A tabela principal implementada na Sprint 1 é:

- Produto

Campos da entidade Produto:

- id
- nome
- codigoBarras
- preco
- estoqueMinimo

## Interface gráfica

A interface gráfica foi desenvolvida com Java Swing.

Telas implementadas:

- ProdutoListar
- ProdutoSave

A tela `ProdutoListar` permite listar, pesquisar, editar, remover e abrir o cadastro de produtos.

A tela `ProdutoSave` permite cadastrar novos produtos e editar produtos existentes.

## API REST

A API pode ser acessada localmente em:

```bash
http://localhost:8080/produtos