package br.com.ifba.prg03projeto.mercado.pagamento.service;

import br.com.ifba.prg03projeto.mercado.itemvenda.repository.ItemVendaRepository;
import br.com.ifba.prg03projeto.mercado.pagamento.entity.Pagamento;
import br.com.ifba.prg03projeto.mercado.pagamento.repository.PagamentoRepository;
import br.com.ifba.prg03projeto.mercado.venda.entity.Venda;
import br.com.ifba.prg03projeto.mercado.venda.repository.VendaRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PagamentoServiceImpl implements PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final VendaRepository vendaRepository;
    private final ItemVendaRepository itemVendaRepository;

    public PagamentoServiceImpl(
            PagamentoRepository pagamentoRepository,
            VendaRepository vendaRepository,
            ItemVendaRepository itemVendaRepository
    ) {
        this.pagamentoRepository = pagamentoRepository;
        this.vendaRepository = vendaRepository;
        this.itemVendaRepository = itemVendaRepository;
    }

    @Override
    @Transactional
    public Pagamento registrarPagamento(
            Long vendaId,
            String formaPagamento,
            BigDecimal valorPago,
            Integer numeroParcelas
    ) {
        Venda venda = vendaRepository.findById(vendaId)
                .orElseThrow(() ->
                        new RuntimeException("Venda não encontrada.")
                );

        validarVenda(venda);
        validarFormaPagamento(formaPagamento);

        validarValorPago(
                formaPagamento,
                valorPago,
                venda.getValorTotal()
        );

        Integer parcelas = validarParcelas(
                formaPagamento,
                numeroParcelas
        );

        if (pagamentoRepository.existsByVendaId(vendaId)) {
            throw new RuntimeException(
                    "Esta venda já possui um pagamento."
            );
        }

        BigDecimal troco = BigDecimal.ZERO;

        if ("DINHEIRO".equals(formaPagamento)) {
            troco = valorPago.subtract(
                    venda.getValorTotal()
            );
        }

        Pagamento pagamento = new Pagamento();

        pagamento.setDataPagamento(LocalDateTime.now());
        pagamento.setFormaPagamento(formaPagamento);
        pagamento.setValorPago(valorPago);
        pagamento.setTroco(troco);
        pagamento.setNumeroParcelas(parcelas);
        pagamento.setVenda(venda);

        Pagamento pagamentoSalvo =
                pagamentoRepository.save(pagamento);

        venda.setStatus("CONCLUIDA");
        vendaRepository.save(venda);

        return pagamentoSalvo;
    }

    @Override
    public List<Pagamento> findAll() {
        return pagamentoRepository.findAll();
    }

    @Override
    public Optional<Pagamento> findById(Long id) {
        return pagamentoRepository.findById(id);
    }

    @Override
    public Optional<Pagamento> findByVendaId(Long vendaId) {
        return pagamentoRepository.findByVendaId(vendaId);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!pagamentoRepository.existsById(id)) {
            throw new RuntimeException(
                    "Pagamento não encontrado."
            );
        }

        throw new RuntimeException(
                "Pagamentos concluídos não podem ser excluídos."
        );
    }

    private void validarVenda(Venda venda) {
        if (!"EM_ANDAMENTO".equals(venda.getStatus())) {
            throw new RuntimeException(
                    "Somente vendas em andamento podem ser pagas."
            );
        }

        if (itemVendaRepository
                .findByVendaId(venda.getId())
                .isEmpty()) {

            throw new RuntimeException(
                    "A venda precisa possuir pelo menos um item."
            );
        }

        if (venda.getValorTotal() == null
                || venda.getValorTotal()
                        .compareTo(BigDecimal.ZERO) <= 0) {

            throw new RuntimeException(
                    "O valor total da venda deve ser maior que zero."
            );
        }
    }

    private void validarFormaPagamento(
            String formaPagamento
    ) {
        if (formaPagamento == null
                || formaPagamento.isBlank()) {

            throw new RuntimeException(
                    "Informe a forma de pagamento."
            );
        }

        boolean formaValida =
                "DINHEIRO".equals(formaPagamento)
                || "CARTAO_CREDITO".equals(formaPagamento)
                || "CARTAO_DEBITO".equals(formaPagamento)
                || "PIX".equals(formaPagamento);

        if (!formaValida) {
            throw new RuntimeException(
                    "Forma de pagamento inválida."
            );
        }
    }

    private void validarValorPago(
            String formaPagamento,
            BigDecimal valorPago,
            BigDecimal valorTotal
    ) {
        if (valorPago == null
                || valorPago.compareTo(BigDecimal.ZERO) <= 0) {

            throw new RuntimeException(
                    "Informe um valor válido."
            );
        }

        if ("DINHEIRO".equals(formaPagamento)) {
            if (valorPago.compareTo(valorTotal) < 0) {
                throw new RuntimeException(
                        "Valor insuficiente."
                );
            }

            return;
        }

        if (valorPago.compareTo(valorTotal) != 0) {
            throw new RuntimeException(
                    "PIX e cartão devem pagar exatamente "
                    + "o valor da venda."
            );
        }
    }

    private Integer validarParcelas(
            String formaPagamento,
            Integer numeroParcelas
    ) {
        if (!"CARTAO_CREDITO".equals(formaPagamento)) {
            return 1;
        }

        if (numeroParcelas == null
                || numeroParcelas < 1
                || numeroParcelas > 12) {

            throw new RuntimeException(
                    "O número de parcelas deve estar "
                    + "entre 1 e 12."
            );
        }

        return numeroParcelas;
    }
}