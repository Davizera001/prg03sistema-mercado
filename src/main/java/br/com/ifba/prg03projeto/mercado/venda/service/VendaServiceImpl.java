package br.com.ifba.prg03projeto.mercado.venda.service;

import br.com.ifba.prg03projeto.mercado.usuario.entity.Usuario;
import br.com.ifba.prg03projeto.mercado.usuario.session.SessaoUsuario;
import br.com.ifba.prg03projeto.mercado.venda.entity.Venda;
import br.com.ifba.prg03projeto.mercado.venda.repository.VendaRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class VendaServiceImpl implements VendaService {

    private final VendaRepository vendaRepository;

    public VendaServiceImpl(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    @Override
    public Venda iniciarVenda() {

        Usuario usuarioLogado = SessaoUsuario.getUsuarioLogado();

        if (usuarioLogado == null) {
            throw new RuntimeException(
                    "É necessário estar autenticado para iniciar uma venda."
            );
        }

        Venda venda = new Venda();

        venda.setDataVenda(LocalDateTime.now());
        venda.setStatus("EM_ANDAMENTO");
        venda.setValorTotal(BigDecimal.ZERO);
        venda.setUsuario(usuarioLogado);

        return vendaRepository.save(venda);
    }

    @Override
    public Venda update(Long id, Venda venda) {

        Venda vendaExistente =
                vendaRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Venda não encontrada."
                                )
                        );

        if ("CONCLUIDA".equalsIgnoreCase(vendaExistente.getStatus())) {
            throw new RuntimeException(
                    "Uma venda concluída não pode ser alterada."
            );
        }

        if ("CANCELADA".equalsIgnoreCase(vendaExistente.getStatus())) {
            throw new RuntimeException(
                    "Uma venda cancelada não pode ser alterada."
            );
        }

        if (venda.getStatus() != null
                && !venda.getStatus().isBlank()) {

            vendaExistente.setStatus(
                    venda.getStatus().toUpperCase()
            );
        }

        /*
         * O valor total será calculado pelos itens da venda.
         * Porém, enquanto ItemVenda ainda não existe, aceitamos o valor
         * recebido somente para permitir testar este módulo isoladamente.
         */
        if (venda.getValorTotal() != null) {

            if (venda.getValorTotal().compareTo(BigDecimal.ZERO) < 0) {
                throw new RuntimeException(
                        "O valor total não pode ser negativo."
                );
            }

            vendaExistente.setValorTotal(
                    venda.getValorTotal()
            );
        }

        return vendaRepository.save(vendaExistente);
    }

    @Override
    public List<Venda> findAll() {
        return vendaRepository.findAll();
    }

    @Override
    public Optional<Venda> findById(Long id) {
        return vendaRepository.findById(id);
    }

    @Override
    public Venda cancelar(Long id) {

        Venda venda =
                vendaRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Venda não encontrada."
                                )
                        );

        if ("CONCLUIDA".equalsIgnoreCase(venda.getStatus())) {
            throw new RuntimeException(
                    "Uma venda concluída não pode ser cancelada."
            );
        }

        if ("CANCELADA".equalsIgnoreCase(venda.getStatus())) {
            throw new RuntimeException(
                    "A venda já está cancelada."
            );
        }

        venda.setStatus("CANCELADA");

        return vendaRepository.save(venda);
    }

    @Override
    public void delete(Long id) {

        Venda venda =
                vendaRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Venda não encontrada."
                                )
                        );

        if (!"EM_ANDAMENTO".equalsIgnoreCase(venda.getStatus())) {
            throw new RuntimeException(
                    "Somente vendas em andamento podem ser excluídas."
            );
        }

        vendaRepository.delete(venda);
    }
}