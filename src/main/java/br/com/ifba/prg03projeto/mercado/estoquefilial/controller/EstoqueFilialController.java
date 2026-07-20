package br.com.ifba.prg03projeto.mercado.estoquefilial.controller;

import br.com.ifba.prg03projeto.mercado.estoquefilial.entity.EstoqueFilial;
import br.com.ifba.prg03projeto.mercado.estoquefilial.service.EstoqueFilialService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;

@Controller
public class EstoqueFilialController {

    private final EstoqueFilialService estoqueFilialService;

    public EstoqueFilialController(
            EstoqueFilialService estoqueFilialService) {

        this.estoqueFilialService = estoqueFilialService;
    }

    public EstoqueFilial save(EstoqueFilial estoqueFilial) {
        return estoqueFilialService.save(estoqueFilial);
    }

    public EstoqueFilial update(
            Long id,
            EstoqueFilial estoqueFilial) {

        return estoqueFilialService.update(id, estoqueFilial);
    }

    public List<EstoqueFilial> findAll() {
        return estoqueFilialService.findAll();
    }

    public Optional<EstoqueFilial> findById(Long id) {
        return estoqueFilialService.findById(id);
    }

    public void delete(Long id) {
        estoqueFilialService.delete(id);
    }
}