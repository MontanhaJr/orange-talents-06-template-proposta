package br.com.zupacademy.mauricio.desafioproposta.cartao;

import br.com.zupacademy.mauricio.desafioproposta.avisos.AvisoViagem;
import br.com.zupacademy.mauricio.desafioproposta.biometrias.Biometria;
import br.com.zupacademy.mauricio.desafioproposta.bloqueios.Bloqueio;
import br.com.zupacademy.mauricio.desafioproposta.cartao.dto.request.CartaoRequest;
import br.com.zupacademy.mauricio.desafioproposta.carteiras.Carteira;
import br.com.zupacademy.mauricio.desafioproposta.propostas.Proposta;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Cartao {

    @Id
    private String id;
    @NotNull
    private LocalDateTime emitidoEm;
    @NotBlank
    private String titular;
    @OneToOne
    private Proposta proposta;
    @OneToOne
    private Bloqueio bloqueio;
    private Boolean bloqueado;
    @Valid @OneToMany(mappedBy = "cartao")
    private Set<AvisoViagem> avisosViagem = new HashSet<>();
    @Valid @OneToMany(mappedBy = "cartao")
    private Set<Carteira> carteiras = new HashSet<>();

    @Deprecated
    public Cartao() {
    }

    public Cartao(String id, LocalDateTime emitidoEm, Proposta proposta, String titular, Boolean bloqueado) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.proposta = proposta;
        this.titular = titular;
        this.bloqueado = bloqueado;
    }

    public Boolean bloqueado() {
        return bloqueado;
    }

    public void adicionarBloqueio(Bloqueio bloqueio) {
        this.bloqueio = bloqueio;
        this.bloqueado = true;
    }

    public void adicionarAviso(AvisoViagem avisosViagem) {
        this.avisosViagem.add(avisosViagem);
    }

    public void adicionarCarteira(Carteira carteira) {
        this.carteiras.add(carteira);
    }

    public boolean cartaoJaCadastradoNaCarteira(String carteiraRequest) {
        for (Carteira carteira: carteiras) {
            if (carteira.getCarteira().equals(carteiraRequest))
            {
                return true;
            }
        }
        return false;
    }
}
