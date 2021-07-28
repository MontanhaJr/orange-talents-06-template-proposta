package br.com.zupacademy.mauricio.desafioproposta.biometrias.dto.request;

import br.com.zupacademy.mauricio.desafioproposta.biometrias.Biometria;
import br.com.zupacademy.mauricio.desafioproposta.cartao.Cartao;
import br.com.zupacademy.mauricio.desafioproposta.validation.annotation.biometria.Base64Validator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BiometriaRequest {

    @Base64Validator @NotBlank @NotNull
    private String fingerprint;

    private String cartao;

    public Biometria toModel(Cartao cartao) {
        return new Biometria(this.fingerprint, cartao);
    }

    public String getFingerprint() {
        return fingerprint;
    }
}
