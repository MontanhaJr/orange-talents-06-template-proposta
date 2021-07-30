package br.com.zupacademy.mauricio.desafioproposta.cartao.dto.response;

public class SolicitarCartaoResponse {
    private String id, nome, documento, idProposta;

    public SolicitarCartaoResponse(String id, String nome, String documento, String idProposta) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
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
