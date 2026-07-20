package br.com.ifba.prg03projeto.mercado.venda.repository;

import br.com.ifba.prg03projeto.mercado.venda.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {

}