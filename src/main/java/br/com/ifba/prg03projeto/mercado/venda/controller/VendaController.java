package br.com.ifba.prg03projeto.mercado.venda.controller;

import br.com.ifba.prg03projeto.mercado.venda.entity.Venda;
import br.com.ifba.prg03projeto.mercado.venda.service.VendaService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;

@Controller
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    public Venda iniciarVenda() {
        return vendaService.iniciarVenda();
    }

    public Venda update(Long id, Venda venda) {
        return vendaService.update(id, venda);
    }

    public List<Venda> findAll() {
        return vendaService.findAll();
    }

    public Optional<Venda> findById(Long id) {
        return vendaService.findById(id);
    }

    public Venda cancelar(Long id) {
        return vendaService.cancelar(id);
    }

    public void delete(Long id) {
        vendaService.delete(id);
    }
}