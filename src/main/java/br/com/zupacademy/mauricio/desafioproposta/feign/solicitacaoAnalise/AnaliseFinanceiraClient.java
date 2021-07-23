package br.com.zupacademy.mauricio.desafioproposta.feign.solicitacaoAnalise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "solicitacao-analise", url = "http://localhost:9999/api/solicitacao")
public interface AnaliseFinanceiraClient {
    @PostMapping
    AnaliseFinanceiraResponse consultaAnaliseFinanceira(@RequestBody AnaliseFinanceiraRequest request);
}
