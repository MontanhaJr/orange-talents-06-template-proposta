package br.com.zupacademy.mauricio.desafioproposta.feign.cartao;

import br.com.zupacademy.mauricio.desafioproposta.bloqueios.dto.request.BloqueioCartaoRequest;
import br.com.zupacademy.mauricio.desafioproposta.bloqueios.dto.response.BloqueioCartaoResponse;
import br.com.zupacademy.mauricio.desafioproposta.cartao.dto.request.CartaoRequest;
import br.com.zupacademy.mauricio.desafioproposta.cartao.dto.request.SolicitarCartaoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cartao", url = "${associar-cartao.host}")
public interface CartaoClient {
    @PostMapping("/api/cartoes")
    CartaoRequest solicitaCartao(@RequestBody SolicitarCartaoRequest request);


    @PostMapping(value = "/api/cartoes/{id}/bloqueios", consumes = "application/json")
    BloqueioCartaoResponse bloquearCartao(@PathVariable String id, @RequestBody BloqueioCartaoRequest request);
}
