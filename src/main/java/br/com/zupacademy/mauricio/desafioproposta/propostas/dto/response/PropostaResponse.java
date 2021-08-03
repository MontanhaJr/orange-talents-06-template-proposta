package br.com.zupacademy.mauricio.desafioproposta.propostas.dto.response;

import br.com.zupacademy.mauricio.desafioproposta.propostas.Proposta;
import org.springframework.security.crypto.encrypt.TextEncryptor;

public class PropostaResponse {
    private String nome, email, documento, endereco, status;
    private double salario;

    public PropostaResponse(Proposta proposta, TextEncryptor encryptor) {
        this.nome = proposta.getNome();
        this.email = proposta.getEmail();
        this.documento = encryptor.decrypt(proposta.getDocumento());
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.status = proposta.getStatusAnaliseFinanceira();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getStatus() {
        return status;
    }

    public double getSalario() {
        return salario;
    }
}
