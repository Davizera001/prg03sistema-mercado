package br.com.ifba.prg03projeto.mercado.produto.controller;

import br.com.ifba.prg03projeto.mercado.produto.entity.Produto;
import br.com.ifba.prg03projeto.mercado.produto.service.ProdutoService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;

@Controller
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    public Produto save(Produto produto) {
        return produtoService.save(produto);
    }

    public Produto update(Long id, Produto produto) {
        return produtoService.update(id, produto);
    }

    public List<Produto> findAll() {
        return produtoService.findAll();
    }

    public Optional<Produto> findById(Long id) {
        return produtoService.findById(id);
    }

    public List<Produto> findByNome(String nome) {
        return produtoService.findByNome(nome);
    }

    public void delete(Long id) {
        produtoService.delete(id);
    }
}