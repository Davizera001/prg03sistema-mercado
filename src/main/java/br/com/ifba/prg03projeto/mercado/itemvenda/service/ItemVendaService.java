package br.com.ifba.prg03projeto.mercado.itemvenda.service;

import br.com.ifba.prg03projeto.mercado.itemvenda.entity.ItemVenda;
import java.util.List;
import java.util.Optional;

public interface ItemVendaService {

    ItemVenda adicionarItem(
            Long vendaId,
            Long produtoId,
            Integer quantidade
    );

    ItemVenda update(
            Long id,
            Integer quantidade
    );

    List<ItemVenda> findByVendaId(Long vendaId);

    Optional<ItemVenda> findById(Long id);

    void delete(Long id);
}