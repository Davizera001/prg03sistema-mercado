/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.prg03projeto.mercado.entity;

/*
 * Importações das anotações do JPA
 * utilizadas para mapear a classe para o banco.
 */

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/*
 * Lombok gera automaticamente:
 * getters
 * setters
 * toString
 * equals
 * hashCode
 */
import lombok.Data;

/**
 * Classe responsável por representar
 * um produto do mercado.
 *
 * Esta classe será transformada em uma
 * tabela do banco de dados pelo Hibernate.
 *
 * @author Davi
 */

/*
 * @Entity indica que esta classe será
 * uma entidade persistida no banco.
 */
@Entity

/*
 * @Data gera automaticamente os métodos
 * getters, setters e outros métodos úteis.
 */

@Data
public class Produto {
    
    /*
     * Chave primária da tabela.
     */
    @Id

    /*
     * Define que o ID será gerado
     * automaticamente pelo banco.
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Nome do produto.
     */
    private String nome;

    /*
     * Código de barras utilizado
     * para identificação do produto.
     */
    private String codigoBarras;

    /*
     * Valor unitário do produto.
     */
    private Double preco;

    /*
     * Quantidade mínima permitida
     * em estoque antes de solicitar reposição.
     */
    private Integer estoqueMinimo;
    
}
