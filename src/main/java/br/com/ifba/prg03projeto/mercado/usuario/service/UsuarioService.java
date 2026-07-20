package br.com.ifba.prg03projeto.mercado.usuario.service;

import br.com.ifba.prg03projeto.mercado.usuario.entity.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario save(Usuario usuario);

    Usuario update(Long id, Usuario usuario);

    List<Usuario> findAll();

    Optional<Usuario> findById(Long id);

    Optional<Usuario> findByLogin(String login);
    
    Usuario autenticar(String login, String senha);

    void delete(Long id);
}