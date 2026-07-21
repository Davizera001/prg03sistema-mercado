package br.com.ifba.prg03projeto.mercado.pagamento.repository;

import br.com.ifba.prg03projeto.mercado.pagamento.entity.Pagamento;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository
        extends JpaRepository<Pagamento, Long> {

    Optional<Pagamento> findByVendaId(Long vendaId);

    boolean existsByVendaId(Long vendaId);
}