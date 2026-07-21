package br.com.ifba.prg03projeto.mercado.itemvenda.service;

import br.com.ifba.prg03projeto.mercado.itemvenda.entity.ItemVenda;
import br.com.ifba.prg03projeto.mercado.itemvenda.repository.ItemVendaRepository;
import br.com.ifba.prg03projeto.mercado.produto.entity.Produto;
import br.com.ifba.prg03projeto.mercado.produto.repository.ProdutoRepository;
import br.com.ifba.prg03projeto.mercado.venda.entity.Venda;
import br.com.ifba.prg03projeto.mercado.venda.repository.VendaRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemVendaServiceImpl implements ItemVendaService {

    private final ItemVendaRepository itemVendaRepository;
    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;

    public ItemVendaServiceImpl(
            ItemVendaRepository itemVendaRepository,
            VendaRepository vendaRepository,
            ProdutoRepository produtoRepository
    ) {
        this.itemVendaRepository = itemVendaRepository;
        this.vendaRepository = vendaRepository;
        this.produtoRepository = produtoRepository;
    }

    @Override
    @Transactional
    public ItemVenda adicionarItem(
            Long vendaId,
            Long produtoId,
            Integer quantidade
    ) {
        validarQuantidade(quantidade);

        Venda venda = vendaRepository.findById(vendaId)
                .orElseThrow(() ->
                        new RuntimeException("Venda não encontrada.")
                );

        validarVendaEmAndamento(venda);

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() ->
                        new RuntimeException("Produto não encontrado.")
                );

        if (produto.getPreco() == null || produto.getPreco() < 0) {
            throw new RuntimeException(
                    "O produto não possui um preço válido."
            );
        }

        ItemVenda itemVenda = new ItemVenda();

        itemVenda.setVenda(venda);
        itemVenda.setProduto(produto);
        itemVenda.setQuantidade(quantidade);
        itemVenda.setPrecoUnitario(produto.getPreco());
        itemVenda.setSubtotal(
                produto.getPreco() * quantidade
        );

        ItemVenda itemSalvo = itemVendaRepository.save(itemVenda);

        recalcularTotalVenda(venda);

        return itemSalvo;
    }

    @Override
    @Transactional
    public ItemVenda update(Long id, Integer quantidade) {
        validarQuantidade(quantidade);

        ItemVenda itemVenda = itemVendaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Item da venda não encontrado."
                        )
                );

        validarVendaEmAndamento(itemVenda.getVenda());

        itemVenda.setQuantidade(quantidade);
        itemVenda.setSubtotal(
                itemVenda.getPrecoUnitario() * quantidade
        );

        ItemVenda itemAtualizado =
                itemVendaRepository.save(itemVenda);

        recalcularTotalVenda(itemVenda.getVenda());

        return itemAtualizado;
    }

    @Override
    public List<ItemVenda> findByVendaId(Long vendaId) {
        return itemVendaRepository.findByVendaId(vendaId);
    }

    @Override
    public Optional<ItemVenda> findById(Long id) {
        return itemVendaRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ItemVenda itemVenda = itemVendaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Item da venda não encontrado."
                        )
                );

        Venda venda = itemVenda.getVenda();

        validarVendaEmAndamento(venda);

        itemVendaRepository.delete(itemVenda);

        recalcularTotalVenda(venda);
    }

    private void validarQuantidade(Integer quantidade) {
        if (quantidade == null || quantidade <= 0) {
            throw new RuntimeException(
                    "A quantidade deve ser maior que zero."
            );
        }
    }

    private void validarVendaEmAndamento(Venda venda) {
        if (!"EM_ANDAMENTO".equals(venda.getStatus())) {
            throw new RuntimeException(
                    "Somente vendas em andamento podem ser alteradas."
            );
        }
    }

    private void recalcularTotalVenda(Venda venda) {
        List<ItemVenda> itens =
                itemVendaRepository.findByVendaId(venda.getId());

        double total = itens.stream()
                .mapToDouble(ItemVenda::getSubtotal)
                .sum();

        venda.setValorTotal(BigDecimal.valueOf(total));

        vendaRepository.save(venda);
    }
}