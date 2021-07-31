package br.com.zupacademy.mauricio.desafioproposta.carteiras.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CarteiraRequest {
    @Email @NotEmpty @NotNull
    private String email;
    private String carteira;

    @Deprecated
    public CarteiraRequest() {
    }

    public CarteiraRequest(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
