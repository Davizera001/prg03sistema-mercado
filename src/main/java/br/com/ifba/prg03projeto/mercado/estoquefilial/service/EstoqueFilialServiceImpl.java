package br.com.ifba.prg03projeto.mercado.estoquefilial.service;

import br.com.ifba.prg03projeto.mercado.estoquefilial.entity.EstoqueFilial;
import br.com.ifba.prg03projeto.mercado.estoquefilial.repository.EstoqueFilialRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class EstoqueFilialServiceImpl
        implements EstoqueFilialService {

    private final EstoqueFilialRepository estoqueFilialRepository;

    public EstoqueFilialServiceImpl(
            EstoqueFilialRepository estoqueFilialRepository) {

        this.estoqueFilialRepository =
                estoqueFilialRepository;
    }

    @Override
    public EstoqueFilial save(
            EstoqueFilial estoqueFilial) {

        validate(estoqueFilial);

        return estoqueFilialRepository.save(
                estoqueFilial
        );
    }

    @Override
    public EstoqueFilial update(
            Long id,
            EstoqueFilial estoqueFilial) {

        EstoqueFilial estoqueExistente =
                estoqueFilialRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Estoque não encontrado."
                                )
                        );

        validate(estoqueFilial);

        estoqueExistente.setQuantidade(
                estoqueFilial.getQuantidade()
        );

        estoqueExistente.setFilial(
                estoqueFilial.getFilial()
        );

        estoqueExistente.setProduto(
                estoqueFilial.getProduto()
        );

        return estoqueFilialRepository.save(
                estoqueExistente
        );
    }

    @Override
    public List<EstoqueFilial> findAll() {
        return estoqueFilialRepository.findAll();
    }

    @Override
    public Optional<EstoqueFilial> findById(
            Long id) {

        return estoqueFilialRepository.findById(id);
    }

    @Override
    public void delete(Long id) {

        if (!estoqueFilialRepository.existsById(id)) {
            throw new RuntimeException(
                    "Estoque não encontrado."
            );
        }

        estoqueFilialRepository.deleteById(id);
    }

    private void validate(
            EstoqueFilial estoqueFilial) {

        if (estoqueFilial == null) {
            throw new RuntimeException(
                    "Informe os dados do estoque."
            );
        }

        if (estoqueFilial.getFilial() == null) {
            throw new RuntimeException(
                    "A filial é obrigatória."
            );
        }

        if (estoqueFilial.getProduto() == null) {
            throw new RuntimeException(
                    "O produto é obrigatório."
            );
        }

        if (estoqueFilial.getQuantidade() == null) {
            throw new RuntimeException(
                    "A quantidade é obrigatória."
            );
        }

        if (estoqueFilial.getQuantidade() < 0) {
            throw new RuntimeException(
                    "A quantidade não pode ser negativa."
            );
        }
    }
}