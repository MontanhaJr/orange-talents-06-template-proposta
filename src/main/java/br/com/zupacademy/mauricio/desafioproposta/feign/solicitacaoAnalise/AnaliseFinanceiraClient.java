package br.com.zupacademy.mauricio.desafioproposta.feign.solicitacaoAnalise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "solicitacao-analise", url = "${analise-financeira.host}")
public interface AnaliseFinanceiraClient {
    @PostMapping("/api/solicitacao")
    AnaliseFinanceiraResponse consultaAnaliseFinanceira(@RequestBody AnaliseFinanceiraRequest request);
}
