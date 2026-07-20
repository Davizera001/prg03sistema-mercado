package br.com.ifba.prg03projeto.mercado.usuario.controller;

import br.com.ifba.prg03projeto.mercado.usuario.entity.Usuario;
import br.com.ifba.prg03projeto.mercado.usuario.service.UsuarioService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;

@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(
            UsuarioService usuarioService) {

        this.usuarioService = usuarioService;
    }

    public Usuario save(Usuario usuario) {
        return usuarioService.save(usuario);
    }

    public Usuario update(
            Long id,
            Usuario usuario) {

        return usuarioService.update(id, usuario);
    }
    
    public Usuario autenticar(
        String login,
        String senha) {

    return usuarioService.autenticar(login, senha);
    }

    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioService.findById(id);
    }

    public Optional<Usuario> findByLogin(String login) {
        return usuarioService.findByLogin(login);
    }

    public void delete(Long id) {
        usuarioService.delete(id);
    }
}