package br.com.zupacademy.mauricio.desafioproposta.cartao.dto.request;

import br.com.zupacademy.mauricio.desafioproposta.cartao.Cartao;
import br.com.zupacademy.mauricio.desafioproposta.propostas.Proposta;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class CartaoRequest {
    @NotBlank
    private String id;
    @NotBlank
    LocalDateTime emitidoEm;
    @NotBlank
    private String titular;

    public CartaoRequest(String id, LocalDateTime emitidoEm, String titular) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
    }

    public Cartao toModel(Proposta proposta) {
        return new Cartao(this.id, this.emitidoEm, proposta, this.titular, false);
    }
}
