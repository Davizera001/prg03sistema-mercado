# PRG03 Sistema Mercado

Sistema acadêmico desenvolvido em Java com Spring Boot para gerenciamento de produtos, filiais, estoques e solicitações de reposição de uma rede de mercados.

O projeto possui API REST, interface gráfica em Java Swing e banco de dados PostgreSQL hospedado no Supabase.

## Tecnologias utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Supabase
* Maven
* Lombok
* Java Swing

## Arquitetura do projeto

O sistema foi organizado em camadas:

```text
View → Controller → Service → Repository → Banco de Dados
```

* `view`: telas desenvolvidas em Java Swing;
* `controller`: endpoints REST e comunicação com as telas;
* `service`: operações e regras da aplicação;
* `repository`: acesso ao banco usando Spring Data JPA;
* `entity`: entidades persistidas no banco de dados.

# Sprint 1

## Itens entregues

* Diagrama de classes com mais de 8 relacionamentos;
* CRUD completo da entidade `Produto`;
* Repositório no GitHub;
* Banco de dados online no Supabase PostgreSQL;
* API REST de produtos;
* Interface gráfica Swing para gerenciamento de produtos.

## Funcionalidades de Produto

* Cadastrar produto;
* Listar produtos;
* Pesquisar produtos por nome;
* Buscar produto por ID;
* Editar produto;
* Remover produto;
* Validar preço e estoque mínimo.

## Entidade Produto

Campos implementados:

* `id`
* `nome`
* `codigoBarras`
* `preco`
* `estoqueMinimo`

## Tela ProdutoListar

A tela principal do sistema permite:

* visualizar produtos;
* pesquisar produtos;
* cadastrar novos produtos;
* editar produtos;
* remover produtos;
* abrir as telas da Sprint 2.

## Tela ProdutoSave

Utilizada para:

* cadastrar novos produtos;
* editar produtos existentes.

# Sprint 2

## Itens entregues

* Correção e atualização do diagrama de classes;
* Implementação das entidades `Filial`, `EstoqueFilial` e `SolicitacaoReposicao`;
* Implementação de relacionamentos JPA;
* Utilização controlada de cascade;
* Criação de três novas telas Swing;
* CRUD completo das três entidades;
* Integração das novas telas com Produto;
* Novos endpoints REST.

## Entidade Filial

Campos implementados:

* `id`
* `nome`
* `endereco`
* `estoques`

### Funcionalidades

* Cadastrar filial;
* Listar filiais;
* Editar filial;
* Remover filial;
* Validar nome e endereço obrigatórios.

## Entidade EstoqueFilial

Representa a quantidade de um produto disponível em determinada filial.

Campos implementados:

* `id`
* `quantidade`
* `filial`
* `produto`

### Funcionalidades

* Cadastrar estoque;
* Selecionar uma filial;
* Selecionar um produto;
* Informar a quantidade;
* Editar estoque;
* Remover estoque;
* Validar quantidade vazia, inválida ou negativa.

## Entidade SolicitacaoReposicao

Representa uma solicitação de reposição feita por uma filial.

Campos implementados:

* `id`
* `dataSolicitacao`
* `status`
* `quantidadeSolicitada`
* `filial`
* `produto`

### Status disponíveis

* `PENDENTE`
* `APROVADA`
* `RECUSADA`
* `CONCLUIDA`

### Funcionalidades

* Cadastrar solicitação;
* Selecionar filial e produto;
* Informar quantidade solicitada;
* Definir status;
* Editar solicitação;
* Remover solicitação;
* Registrar automaticamente data e horário;
* Validar quantidade maior que zero.

# Relacionamentos JPA

## Filial e EstoqueFilial

Uma filial pode possuir vários registros de estoque.

```text
Filial 1 → 0..* EstoqueFilial
```

O relacionamento foi implementado com:

```java
@OneToMany(
    mappedBy = "filial",
    cascade = CascadeType.ALL,
    orphanRemoval = true
)
```

O cascade foi aplicado somente nessa relação porque os registros de estoque pertencem à filial.

Não foi utilizado cascade entre `EstoqueFilial` e `Produto`, pois um produto possui existência independente e não deve ser removido quando um registro de estoque for excluído.

## EstoqueFilial e Produto

Vários registros de estoque podem apontar para o mesmo produto.

```text
Produto 1 → 0..* EstoqueFilial
```

## SolicitacaoReposicao

Cada solicitação está relacionada a:

* uma filial;
* um produto.

```text
Filial 1 → 0..* SolicitacaoReposicao
Produto 1 → 0..* SolicitacaoReposicao
```

# Telas implementadas

* `ProdutoListar`
* `ProdutoSave`
* `FilialView`
* `EstoqueFilialView`
* `SolicitacaoReposicaoView`

A tela `ProdutoListar` funciona como tela principal e possui botões para abrir as demais telas do sistema.

# API REST

A aplicação utiliza a porta padrão:

```text
http://localhost:8080
```

## Produto

### Listar produtos

```bash
curl http://localhost:8080/produtos
```

### Buscar produto por ID

```bash
curl http://localhost:8080/produtos/1
```

### Pesquisar produto por nome

```bash
curl "http://localhost:8080/produtos?nome=arroz"
```

### Cadastrar produto

```bash
curl -X POST http://localhost:8080/produtos \
-H "Content-Type: application/json" \
-d '{"nome":"Arroz","codigoBarras":"789123","preco":25.90,"estoqueMinimo":10}'
```

### Atualizar produto

```bash
curl -X PUT http://localhost:8080/produtos/1 \
-H "Content-Type: application/json" \
-d '{"nome":"Arroz Integral","codigoBarras":"789123","preco":29.90,"estoqueMinimo":12}'
```

### Remover produto

```bash
curl -X DELETE http://localhost:8080/produtos/1
```

## Filial

### Listar filiais

```bash
curl http://localhost:8080/filiais
```

### Buscar filial por ID

```bash
curl http://localhost:8080/filiais/1
```

### Cadastrar filial

```bash
curl -X POST http://localhost:8080/filiais \
-H "Content-Type: application/json" \
-d '{"nome":"Filial Centro","endereco":"Avenida Sete de Setembro, 1200"}'
```

### Atualizar filial

```bash
curl -X PUT http://localhost:8080/filiais/1 \
-H "Content-Type: application/json" \
-d '{"nome":"Filial Centro Atualizada","endereco":"Avenida Brasil, 1500"}'
```

### Remover filial

```bash
curl -X DELETE http://localhost:8080/filiais/1
```

## Estoque da filial

### Listar estoques

```bash
curl http://localhost:8080/estoques-filiais
```

### Buscar estoque por ID

```bash
curl http://localhost:8080/estoques-filiais/1
```

## Solicitações de reposição

### Listar solicitações

```bash
curl http://localhost:8080/solicitacoes-reposicao
```

### Buscar solicitação por ID

```bash
curl http://localhost:8080/solicitacoes-reposicao/1
```

# Banco de dados

O banco utilizado é PostgreSQL hospedado no Supabase.

Tabelas implementadas:

* `produto`
* `filial`
* `estoque_filial`
* `solicitacao_reposicao`

As tabelas e relacionamentos são atualizados pelo Hibernate.

# Como executar o projeto

1. Clonar o repositório:

```bash
git clone https://github.com/Davizera001/prg03sistema-mercado.git
```

2. Abrir o projeto no Apache NetBeans.

3. Configurar o arquivo:

```text
src/main/resources/application.properties
```

4. Informar os dados de conexão com o PostgreSQL/Supabase.

5. Executar a classe:

```text
Prg03sistemaMercadoApplication
```

6. A aplicação iniciará o Spring Boot e abrirá a tela principal de produtos.

