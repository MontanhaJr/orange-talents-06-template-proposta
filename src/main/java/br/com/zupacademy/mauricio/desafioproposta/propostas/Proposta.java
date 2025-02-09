package br.com.zupacademy.mauricio.desafioproposta.propostas;

import br.com.zupacademy.mauricio.desafioproposta.cartao.Cartao;
import br.com.zupacademy.mauricio.desafioproposta.cartao.dto.request.CartaoRequest;
import br.com.zupacademy.mauricio.desafioproposta.feign.solicitacaoAnalise.StatusAnaliseFinanceira;

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
    private String statusAnaliseFinanceira;
    @OneToOne
    private Cartao cartao;

    @Deprecated
    public Proposta() {
    }

    public Proposta(@NotBlank String email,
                    @NotBlank String nome,
                    @NotBlank String documento,
                    @NotBlank String endereco,
                    @NotBlank @Positive Double salario) {
        this.email = email;
        this.nome = nome;
        this.documento = documento;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public Double getSalario() {
        return salario;
    }

    public String getStatusAnaliseFinanceira() {
        return statusAnaliseFinanceira;
    }

    public void defineComoElegivel() {
        this.statusAnaliseFinanceira = StatusAnaliseFinanceira.ELEGIVEL.name();
    }

    public void defineComoNaoElegivel() {
        this.statusAnaliseFinanceira = StatusAnaliseFinanceira.NAO_ELEGIVEL.name();
    }

    public void associaCartao(CartaoRequest request) {
        this.cartao = request.toModel(this);
    }
}
