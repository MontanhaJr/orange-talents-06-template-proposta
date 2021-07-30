package br.com.zupacademy.mauricio.desafioproposta.bloqueios.dto.request;

public class BloqueioCartaoRequest {
    private String sistemaResponsavel;

    public BloqueioCartaoRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
