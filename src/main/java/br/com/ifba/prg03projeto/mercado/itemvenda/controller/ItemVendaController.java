package br.com.ifba.prg03projeto.mercado.itemvenda.controller;

import br.com.ifba.prg03projeto.mercado.itemvenda.entity.ItemVenda;
import br.com.ifba.prg03projeto.mercado.itemvenda.service.ItemVendaService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;

@Controller
public class ItemVendaController {

    private final ItemVendaService itemVendaService;

    public ItemVendaController(ItemVendaService itemVendaService) {
        this.itemVendaService = itemVendaService;
    }

    public ItemVenda adicionarItem(
            Long vendaId,
            Long produtoId,
            Integer quantidade
    ) {
        return itemVendaService.adicionarItem(
                vendaId,
                produtoId,
                quantidade
        );
    }

    public ItemVenda update(Long id, Integer quantidade) {
        return itemVendaService.update(id, quantidade);
    }

    public List<ItemVenda> findByVendaId(Long vendaId) {
        return itemVendaService.findByVendaId(vendaId);
    }

    public Optional<ItemVenda> findById(Long id) {
        return itemVendaService.findById(id);
    }

    public void delete(Long id) {
        itemVendaService.delete(id);
    }
}