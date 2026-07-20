package br.com.ifba.prg03projeto.mercado.usuario.service;

import br.com.ifba.prg03projeto.mercado.usuario.entity.Usuario;
import br.com.ifba.prg03projeto.mercado.usuario.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl
        implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(
            UsuarioRepository usuarioRepository) {

        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario save(Usuario usuario) {

        validate(usuario);

        if (usuarioRepository.existsByLogin(usuario.getLogin())) {
            throw new RuntimeException(
                    "Já existe um usuário com esse login."
            );
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario update(
            Long id,
            Usuario usuario) {

        Usuario usuarioExistente =
                usuarioRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Usuário não encontrado."
                                )
                        );

        validate(usuario);

        Optional<Usuario> usuarioLogin =
                usuarioRepository.findByLogin(usuario.getLogin());

        if (usuarioLogin.isPresent()
                && !usuarioLogin.get().getId().equals(id)) {

            throw new RuntimeException(
                    "Já existe um usuário com esse login."
            );
        }

        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setLogin(usuario.getLogin());
        usuarioExistente.setSenha(usuario.getSenha());
        usuarioExistente.setPerfil(usuario.getPerfil());

        return usuarioRepository.save(usuarioExistente);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<Usuario> findByLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }

    @Override
    public void delete(Long id) {

        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException(
                    "Usuário não encontrado."
            );
        }

        usuarioRepository.deleteById(id);
    }

    private void validate(
            Usuario usuario) {

        if (usuario.getNome() == null
                || usuario.getNome().isBlank()) {

            throw new RuntimeException(
                    "O nome é obrigatório."
            );
        }

        if (usuario.getLogin() == null
                || usuario.getLogin().isBlank()) {

            throw new RuntimeException(
                    "O login é obrigatório."
            );
        }

        if (usuario.getSenha() == null
                || usuario.getSenha().isBlank()) {

            throw new RuntimeException(
                    "A senha é obrigatória."
            );
        }

        if (usuario.getPerfil() == null
                || usuario.getPerfil().isBlank()) {

            throw new RuntimeException(
                    "O perfil é obrigatório."
            );
        }
    }
}