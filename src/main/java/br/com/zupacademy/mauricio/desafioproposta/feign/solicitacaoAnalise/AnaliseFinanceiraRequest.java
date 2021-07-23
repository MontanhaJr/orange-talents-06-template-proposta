package br.com.zupacademy.mauricio.desafioproposta.feign.solicitacaoAnalise;

public class AnaliseFinanceiraRequest {

    private String nome, documento, idProposta;

    public AnaliseFinanceiraRequest(String nome, String documento, String idProposta) {
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
