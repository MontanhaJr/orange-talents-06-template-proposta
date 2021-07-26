package br.com.zupacademy.mauricio.desafioproposta.feign.solicitarCartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cartao", url = "http://localhost:8888/api/cartoes")
public interface SolicitarCartaoClient {
    @PostMapping
    SolicitarCartaoResponse solicitaCartao(@RequestBody SolicitarCartaoRequest request);
}
