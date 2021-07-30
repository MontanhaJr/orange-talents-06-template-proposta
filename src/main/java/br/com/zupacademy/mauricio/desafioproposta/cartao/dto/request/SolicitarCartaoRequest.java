package br.com.zupacademy.mauricio.desafioproposta.feign.solicitarCartao;

public class SolicitarCartaoRequest {
    private String nome, documento, idProposta;

    public SolicitarCartaoRequest(String nome, String documento, String idProposta) {
        this.nome = nome;
        this.documento = documento;
        this.idProposta = idProposta;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
