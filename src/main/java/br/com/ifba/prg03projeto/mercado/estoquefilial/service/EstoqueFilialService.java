/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.prg03projeto.mercado.estoquefilial.service;

import br.com.ifba.prg03projeto.mercado.estoquefilial.entity.EstoqueFilial;
import java.util.List;
import java.util.Optional;

public interface EstoqueFilialService {

    EstoqueFilial save(EstoqueFilial estoqueFilial);

    EstoqueFilial update(Long id, EstoqueFilial estoqueFilial);

    List<EstoqueFilial> findAll();

    Optional<EstoqueFilial> findById(Long id);

    void delete(Long id);
}