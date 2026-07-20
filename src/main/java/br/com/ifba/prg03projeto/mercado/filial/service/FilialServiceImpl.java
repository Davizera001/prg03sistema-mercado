package br.com.ifba.prg03projeto.mercado.filial.service;

import br.com.ifba.prg03projeto.mercado.filial.entity.Filial;
import br.com.ifba.prg03projeto.mercado.filial.repository.FilialRepository;
import br.com.ifba.prg03projeto.mercado.solicitacaoreposicao.repository.SolicitacaoReposicaoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class FilialServiceImpl implements FilialService {

    private final FilialRepository filialRepository;
    private final SolicitacaoReposicaoRepository solicitacaoReposicaoRepository;

    public FilialServiceImpl(
            FilialRepository filialRepository,
            SolicitacaoReposicaoRepository solicitacaoReposicaoRepository) {

        this.filialRepository = filialRepository;
        this.solicitacaoReposicaoRepository = solicitacaoReposicaoRepository;
    }

    @Override
    public Filial save(Filial filial) {
        return filialRepository.save(filial);
    }

    @Override
    public Filial update(Long id, Filial filial) {

        Filial filialExistente = filialRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Filial não encontrada."));

        filialExistente.setNome(filial.getNome());
        filialExistente.setEndereco(filial.getEndereco());

        return filialRepository.save(filialExistente);
    }

    @Override
    public List<Filial> findAll() {
        return filialRepository.findAll();
    }

    @Override
    public Optional<Filial> findById(Long id) {
        return filialRepository.findById(id);
    }

    @Override
    public void delete(Long id) {

        if (!filialRepository.existsById(id)) {
            throw new RuntimeException("Filial não encontrada.");
        }

        if (solicitacaoReposicaoRepository.existsByFilialId(id)) {
            throw new RuntimeException(
                    "A filial não pode ser removida porque possui "
                    + "solicitações de reposição vinculadas."
            );
        }

        filialRepository.deleteById(id);
    }
}