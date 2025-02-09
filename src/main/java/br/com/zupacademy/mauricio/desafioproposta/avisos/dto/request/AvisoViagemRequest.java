package br.com.zupacademy.mauricio.desafioproposta.avisos.dto.request;

import br.com.zupacademy.mauricio.desafioproposta.avisos.AvisoViagem;
import br.com.zupacademy.mauricio.desafioproposta.cartao.Cartao;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagemRequest {

    @NotNull
    @NotEmpty
    private String destino;
    @NotNull @Future
    private LocalDate validoAte;

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    @Deprecated
    public AvisoViagemRequest() {
    }

    public AvisoViagemRequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public AvisoViagem toModel(String userAgent, String ipCliente, Cartao cartao) {
        return new AvisoViagem(this.destino, this.validoAte, userAgent, ipCliente, cartao);
    }
}
