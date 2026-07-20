package br.com.ifba.prg03projeto.mercado.usuario.session;

import br.com.ifba.prg03projeto.mercado.usuario.entity.Usuario;

public final class SessaoUsuario {

    private static Usuario usuarioLogado;

    private SessaoUsuario() {
    }

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void iniciar(Usuario usuario) {

        if (usuario == null) {
            throw new RuntimeException(
                    "Não é possível iniciar a sessão sem um usuário."
            );
        }

        usuarioLogado = usuario;
    }

    public static boolean estaLogado() {
        return usuarioLogado != null;
    }

    public static boolean usuarioPossuiPerfil(String perfil) {

        if (!estaLogado()
                || perfil == null
                || usuarioLogado.getPerfil() == null) {

            return false;
        }

        return usuarioLogado.getPerfil()
                .equalsIgnoreCase(perfil);
    }

    public static void encerrar() {
        usuarioLogado = null;
    }
}