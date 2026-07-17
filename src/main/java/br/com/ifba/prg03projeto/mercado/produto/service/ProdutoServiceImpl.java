package br.com.ifba.prg03projeto.mercado.produto.service;

import br.com.ifba.prg03projeto.mercado.repository.EstoqueFilialRepository;
import br.com.ifba.prg03projeto.mercado.repository.SolicitacaoReposicaoRepository;
import br.com.ifba.prg03projeto.mercado.produto.entity.Produto;
import br.com.ifba.prg03projeto.mercado.produto.repository.ProdutoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;


@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final EstoqueFilialRepository estoqueFilialRepository;
    private final SolicitacaoReposicaoRepository solicitacaoReposicaoRepository;

    public ProdutoServiceImpl(
        ProdutoRepository produtoRepository,
        EstoqueFilialRepository estoqueFilialRepository,
        SolicitacaoReposicaoRepository solicitacaoReposicaoRepository) {

    this.produtoRepository = produtoRepository;
    this.estoqueFilialRepository = estoqueFilialRepository;
    this.solicitacaoReposicaoRepository = solicitacaoReposicaoRepository;
    }

    @Override
    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public Produto update(Long id, Produto produto) {

        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        produtoExistente.setNome(produto.getNome());
        produtoExistente.setCodigoBarras(produto.getCodigoBarras());
        produtoExistente.setPreco(produto.getPreco());
        produtoExistente.setEstoqueMinimo(produto.getEstoqueMinimo());

        return produtoRepository.save(produtoExistente);
    }

    @Override
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    @Override
    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }

    @Override
    public List<Produto> findByNome(String nome) {

        if (nome == null || nome.trim().isEmpty()) {
            return produtoRepository.findAll();
        }

        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Override
    public void delete(Long id) {

        if (!produtoRepository.existsById(id)) {
        throw new RuntimeException("Produto não encontrado.");
        }

        if (estoqueFilialRepository.existsByProdutoId(id)) {
        throw new RuntimeException(
                "O produto não pode ser removido porque possui estoque cadastrado em uma filial."
        );
        }

        if (solicitacaoReposicaoRepository.existsByProdutoId(id)) {
        throw new RuntimeException(
                "O produto não pode ser removido porque possui solicitação de reposição."
        );
        }

    produtoRepository.deleteById(id);
}
}