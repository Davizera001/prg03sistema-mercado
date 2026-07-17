package br.com.ifba.prg03projeto.mercado;

// Importa a tela principal de produtos.
import br.com.ifba.prg03projeto.mercado.produto.view.ProdutoListar;

// Importa o construtor da aplicação Spring Boot.
import org.springframework.boot.builder.SpringApplicationBuilder;

// Importa a anotação principal do Spring Boot.
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Importa o contexto do Spring.
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Prg03sistemaMercadoApplication {

    // Guarda o contexto do Spring para as telas acessarem controllers e services.
    public static ConfigurableApplicationContext contexto;

    public static void main(String[] args) {
        
        // Inicia o Spring Boot com suporte a interface gráfica Swing.
        contexto = new SpringApplicationBuilder(Prg03sistemaMercadoApplication.class)
                .headless(false)
                .run(args);

        // Abre a tela de produtos depois que o Spring já iniciou.
        java.awt.EventQueue.invokeLater(() -> {

            // Abre a tela principal do sistema.
            new ProdutoListar().setVisible(true);
        });
        
    }
}