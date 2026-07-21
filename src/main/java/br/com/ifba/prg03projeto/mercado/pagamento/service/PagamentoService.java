package br.com.ifba.prg03projeto.mercado.pagamento.service;

import br.com.ifba.prg03projeto.mercado.pagamento.entity.Pagamento;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PagamentoService {

    Pagamento registrarPagamento(
            Long vendaId,
            String formaPagamento,
            BigDecimal valorPago,
            Integer numeroParcelas
    );

    List<Pagamento> findAll();

    Optional<Pagamento> findById(Long id);

    Optional<Pagamento> findByVendaId(Long vendaId);

    void delete(Long id);
}