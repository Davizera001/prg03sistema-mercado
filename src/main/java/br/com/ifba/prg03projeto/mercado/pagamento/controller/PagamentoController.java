package br.com.ifba.prg03projeto.mercado.pagamento.controller;

import br.com.ifba.prg03projeto.mercado.pagamento.entity.Pagamento;
import br.com.ifba.prg03projeto.mercado.pagamento.service.PagamentoService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;

@Controller
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(
            PagamentoService pagamentoService
    ) {
        this.pagamentoService = pagamentoService;
    }

    public Pagamento registrarPagamento(
            Long vendaId,
            String formaPagamento,
            BigDecimal valorPago,
            Integer numeroParcelas
    ) {
        return pagamentoService.registrarPagamento(
                vendaId,
                formaPagamento,
                valorPago,
                numeroParcelas
        );
    }

    public List<Pagamento> findAll() {
        return pagamentoService.findAll();
    }

    public Optional<Pagamento> findById(Long id) {
        return pagamentoService.findById(id);
    }

    public Optional<Pagamento> findByVendaId(Long vendaId) {
        return pagamentoService.findByVendaId(vendaId);
    }

    public void delete(Long id) {
        pagamentoService.delete(id);
    }
}