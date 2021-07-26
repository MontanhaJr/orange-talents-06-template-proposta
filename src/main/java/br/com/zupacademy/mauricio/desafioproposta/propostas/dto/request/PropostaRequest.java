package br.com.zupacademy.mauricio.desafioproposta.propostas.dto.request;

import br.com.zupacademy.mauricio.desafioproposta.propostas.Proposta;
import br.com.zupacademy.mauricio.desafioproposta.validation.annotation.document.DocumentValidator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class PropostaRequest {

    private Long id;
    @NotNull @NotBlank @Email
    private String email;
    @NotNull @NotBlank
    private String nome;
    @NotNull @NotBlank @DocumentValidator
    private String documento;
    @NotNull @NotBlank
    private String endereco;
    @NotNull @Positive
    private Double salario;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public Double getSalario() {
        return salario;
    }

    @Deprecated
    public PropostaRequest() {
    }

    public PropostaRequest(@NotNull @NotBlank @Email String email,
                           @NotNull @NotBlank String nome,
                           @NotNull @NotBlank String documento,
                           @NotNull @NotBlank String endereco,
                           @NotNull @Positive Double salario) {
        this.email = email;
        this.nome = nome;
        this.documento = documento;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta toModel() {
        return new Proposta(this.email, this.nome, this.documento, this.endereco, this.salario);
    }
}
