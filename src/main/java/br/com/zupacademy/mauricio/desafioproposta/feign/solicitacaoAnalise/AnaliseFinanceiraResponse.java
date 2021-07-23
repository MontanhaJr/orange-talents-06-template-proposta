package br.com.zupacademy.mauricio.desafioproposta.feign.solicitacaoAnalise;

public class AnaliseFinanceiraResponse {

    private String documento, nome, idProposta;
    private String resultadoSolicitacao;

    public AnaliseFinanceiraResponse(String documento, String nome, String resultadoSolicitacao, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
