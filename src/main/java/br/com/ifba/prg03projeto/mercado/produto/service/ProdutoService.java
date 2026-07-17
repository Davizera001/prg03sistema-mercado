/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.prg03projeto.mercado.produto.service;

import br.com.ifba.prg03projeto.mercado.produto.entity.Produto;
import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    Produto save(Produto produto);

    Produto update(Long id, Produto produto);

    List<Produto> findAll();

    Optional<Produto> findById(Long id);

    List<Produto> findByNome(String nome);

    void delete(Long id);
}