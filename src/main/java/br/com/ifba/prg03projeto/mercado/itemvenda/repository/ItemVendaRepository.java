package br.com.ifba.prg03projeto.mercado.itemvenda.repository;

import br.com.ifba.prg03projeto.mercado.itemvenda.entity.ItemVenda;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemVendaRepository
        extends JpaRepository<ItemVenda, Long> {

    List<ItemVenda> findByVendaId(Long vendaId);
}