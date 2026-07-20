package br.com.ifba.prg03projeto.mercado;

import br.com.ifba.prg03projeto.mercado.login.view.LoginView;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Prg03sistemaMercadoApplication {

    // Contexto do Spring usado pelas telas para acessar os Controllers.
    public static ConfigurableApplicationContext contexto;

    public static void main(String[] args) {

        // Inicializa o Spring Boot permitindo o uso da interface Swing.
        contexto = new SpringApplicationBuilder(
                Prg03sistemaMercadoApplication.class
        )
                .headless(false)
                .run(args);

        // Abre a tela principal somente depois que o Spring estiver iniciado.
        java.awt.EventQueue.invokeLater(() -> {

            LoginView loginView = new LoginView();

            loginView.setLocationRelativeTo(null);
            loginView.setVisible(true);
        });
    }
}