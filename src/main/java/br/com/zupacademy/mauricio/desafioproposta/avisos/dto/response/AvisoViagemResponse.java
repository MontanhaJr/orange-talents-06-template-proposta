package br.com.zupacademy.mauricio.desafioproposta.avisos.dto.response;

public class AvisoViagemResponse {
    private String resultado;

    @Deprecated
    public AvisoViagemResponse() {
    }

    public AvisoViagemResponse(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
