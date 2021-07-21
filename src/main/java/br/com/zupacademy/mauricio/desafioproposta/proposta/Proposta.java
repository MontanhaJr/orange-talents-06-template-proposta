package br.com.zupacademy.mauricio.desafioproposta.proposta;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) @NotBlank
    private String email;
    @Column(nullable = false) @NotBlank
    private String nome;
    @Column(nullable = false) @NotBlank
    private String documento;
    @Column(nullable = false) @NotBlank
    private String endereco;
    @Column(nullable = false) @Positive
    private Double salario;

    @Deprecated
    public Proposta() {
    }

    public Proposta(Long id, String email, String nome, String documento, String endereco, Double salario) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.documento = documento;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }
}
