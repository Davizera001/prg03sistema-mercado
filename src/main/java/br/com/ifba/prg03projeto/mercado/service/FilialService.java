/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.prg03projeto.mercado.service;

// Importa a entidade Filial.
import br.com.ifba.prg03projeto.mercado.entity.Filial;

// Importa o repositório de Filial.
import br.com.ifba.prg03projeto.mercado.repository.FilialRepository;

// Importa as coleções usadas.
import java.util.List;
import java.util.Optional;

// Importa a anotação de serviço do Spring.
import org.springframework.stereotype.Service;

@Service
public class FilialService {

    // Repositório usado para acessar o banco de dados.
    private final FilialRepository filialRepository;

    // Construtor usado pelo Spring para injetar o repositório.
    public FilialService(FilialRepository filialRepository) {
        this.filialRepository = filialRepository;
    }

    // Salva ou atualiza uma filial.
    public Filial salvar(Filial filial) {
        return filialRepository.save(filial);
    }

    // Lista todas as filiais.
    public List<Filial> listarTodos() {
        return filialRepository.findAll();
    }

    // Busca uma filial pelo ID.
    public Optional<Filial> buscarPorId(Long id) {
        return filialRepository.findById(id);
    }

    // Remove uma filial pelo ID.
    public void deletar(Long id) {
        filialRepository.deleteById(id);
    }
}