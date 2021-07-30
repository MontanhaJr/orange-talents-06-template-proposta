package br.com.zupacademy.mauricio.desafioproposta.bloqueios.dto.response;

public class BloqueioCartaoResponse {
    private String resultado;

    @Deprecated
    public BloqueioCartaoResponse() {
    }

    public BloqueioCartaoResponse(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
