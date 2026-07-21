package br.com.ifba.prg03projeto.mercado.pagamento.entity;

import br.com.ifba.prg03projeto.mercado.venda.entity.Venda;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataPagamento;

    private String formaPagamento;

    private BigDecimal valorPago;

    private BigDecimal troco;

    private Integer numeroParcelas;

    @OneToOne
    @JoinColumn(
            name = "venda_id",
            nullable = false,
            unique = true
    )
    private Venda venda;
}