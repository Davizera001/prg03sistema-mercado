package br.com.ifba.prg03projeto.mercado.venda.service;

import br.com.ifba.prg03projeto.mercado.venda.entity.Venda;
import java.util.List;
import java.util.Optional;

public interface VendaService {

    Venda iniciarVenda();

    Venda update(Long id, Venda venda);

    List<Venda> findAll();

    Optional<Venda> findById(Long id);

    Venda cancelar(Long id);

    void delete(Long id);
}