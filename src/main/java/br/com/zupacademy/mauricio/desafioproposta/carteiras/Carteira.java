package br.com.zupacademy.mauricio.desafioproposta.carteiras;

import br.com.zupacademy.mauricio.desafioproposta.cartao.Cartao;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Carteira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email @NotEmpty @NotNull
    private String email;
    private String carteira;
    @NotNull @Valid @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Carteira() {
    }

    public Carteira(String email, String carteira, Cartao cartao) {
        this.email = email;
        this.carteira = carteira;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
