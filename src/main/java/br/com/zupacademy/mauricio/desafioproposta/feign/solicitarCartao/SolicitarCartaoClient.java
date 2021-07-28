package br.com.zupacademy.mauricio.desafioproposta.feign.solicitarCartao;

import br.com.zupacademy.mauricio.desafioproposta.cartao.dto.request.CartaoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cartao", url = "${associar-cartao.host}")
public interface SolicitarCartaoClient {
    @PostMapping("/api/cartoes")
    CartaoRequest solicitaCartao(@RequestBody SolicitarCartaoRequest request);
}
