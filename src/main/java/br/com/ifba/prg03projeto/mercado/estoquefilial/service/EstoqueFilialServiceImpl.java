/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.prg03projeto.mercado.estoquefilial.service;

import br.com.ifba.prg03projeto.mercado.estoquefilial.entity.EstoqueFilial;
import br.com.ifba.prg03projeto.mercado.estoquefilial.repository.EstoqueFilialRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class EstoqueFilialServiceImpl implements EstoqueFilialService {

    private final EstoqueFilialRepository estoqueFilialRepository;

    public EstoqueFilialServiceImpl(
            EstoqueFilialRepository estoqueFilialRepository) {

        this.estoqueFilialRepository = estoqueFilialRepository;
    }

    @Override
    public EstoqueFilial save(EstoqueFilial estoqueFilial) {
        return estoqueFilialRepository.save(estoqueFilial);
    }

    @Override
    public EstoqueFilial update(
            Long id,
            EstoqueFilial estoqueFilial) {

        EstoqueFilial estoqueExistente =
                estoqueFilialRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Estoque não encontrado."
                                )
                        );

        estoqueExistente.setQuantidade(
                estoqueFilial.getQuantidade()
        );

        estoqueExistente.setFilial(
                estoqueFilial.getFilial()
        );

        estoqueExistente.setProduto(
                estoqueFilial.getProduto()
        );

        return estoqueFilialRepository.save(estoqueExistente);
    }

    @Override
    public List<EstoqueFilial> findAll() {
        return estoqueFilialRepository.findAll();
    }

    @Override
    public Optional<EstoqueFilial> findById(Long id) {
        return estoqueFilialRepository.findById(id);
    }

    @Override
    public void delete(Long id) {

        if (!estoqueFilialRepository.existsById(id)) {
            throw new RuntimeException(
                    "Estoque não encontrado."
            );
        }

        estoqueFilialRepository.deleteById(id);
    }
}