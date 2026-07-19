package br.com.ifba.prg03projeto.mercado.filial.service;

import br.com.ifba.prg03projeto.mercado.filial.entity.Filial;
import java.util.List;
import java.util.Optional;

public interface FilialService {

    Filial save(Filial filial);

    Filial update(Long id, Filial filial);

    List<Filial> findAll();

    Optional<Filial> findById(Long id);

    void delete(Long id);

}