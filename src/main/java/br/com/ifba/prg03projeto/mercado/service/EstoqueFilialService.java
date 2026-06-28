/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.prg03projeto.mercado.service;

// Importa a entidade EstoqueFilial.
import br.com.ifba.prg03projeto.mercado.entity.EstoqueFilial;

// Importa o repositório de EstoqueFilial.
import br.com.ifba.prg03projeto.mercado.repository.EstoqueFilialRepository;

// Importa as coleções usadas.
import java.util.List;
import java.util.Optional;

// Importa a anotação de serviço do Spring.
import org.springframework.stereotype.Service;

@Service
public class EstoqueFilialService {

    // Repositório usado para acessar o banco.
    private final EstoqueFilialRepository estoqueFilialRepository;

    // Construtor usado pelo Spring para injetar o repositório.
    public EstoqueFilialService(EstoqueFilialRepository estoqueFilialRepository) {
        this.estoqueFilialRepository = estoqueFilialRepository;
    }

    // Salva ou atualiza um registro de estoque.
    public EstoqueFilial salvar(EstoqueFilial estoqueFilial) {
        return estoqueFilialRepository.save(estoqueFilial);
    }

    // Lista todos os registros de estoque.
    public List<EstoqueFilial> listarTodos() {
        return estoqueFilialRepository.findAll();
    }

    // Busca um estoque pelo ID.
    public Optional<EstoqueFilial> buscarPorId(Long id) {
        return estoqueFilialRepository.findById(id);
    }

    // Remove um estoque pelo ID.
    public void deletar(Long id) {
        estoqueFilialRepository.deleteById(id);
    }
}