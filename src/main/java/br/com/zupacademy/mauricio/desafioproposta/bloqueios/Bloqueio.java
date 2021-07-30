package br.com.zupacademy.mauricio.desafioproposta.bloqueios;

import br.com.zupacademy.mauricio.desafioproposta.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant instanteBloqueio = Instant.now();
    private String userAgent;
    private String ipCliente;
    @NotNull @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(String userAgent, String ipCliente, Cartao cartao) {
        this.userAgent = userAgent;
        this.ipCliente = ipCliente;
        this.cartao = cartao;
    }
}
