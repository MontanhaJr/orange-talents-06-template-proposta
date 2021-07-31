package br.com.zupacademy.mauricio.desafioproposta.avisos;

import br.com.zupacademy.mauricio.desafioproposta.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDate;

@Entity
public class AvisoViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @NotEmpty
    private String destino;
    @NotNull @Future
    private LocalDate dataTermino;
    private Instant instanteAviso = Instant.now();
    private String userAgent;
    private String ipCliente;
    @NotNull @ManyToOne
    private Cartao cartao;

    @Deprecated
    public AvisoViagem() {
    }

    public AvisoViagem(String destino, LocalDate dataTermino, String userAgent, String ipCliente, Cartao cartao) {
        this.destino = destino;
        this.dataTermino = dataTermino;
        this.userAgent = userAgent;
        this.ipCliente = ipCliente;
        this.cartao = cartao;
    }
}
