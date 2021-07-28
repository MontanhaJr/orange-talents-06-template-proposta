package br.com.zupacademy.mauricio.desafioproposta.rotinas;

import br.com.zupacademy.mauricio.desafioproposta.cartao.CartaoRepository;
import br.com.zupacademy.mauricio.desafioproposta.cartao.dto.request.CartaoRequest;
import br.com.zupacademy.mauricio.desafioproposta.feign.solicitarCartao.SolicitarCartaoRequest;
import br.com.zupacademy.mauricio.desafioproposta.feign.solicitacaoAnalise.StatusAnaliseFinanceira;
import br.com.zupacademy.mauricio.desafioproposta.feign.solicitarCartao.SolicitarCartaoClient;
import br.com.zupacademy.mauricio.desafioproposta.propostas.Proposta;
import br.com.zupacademy.mauricio.desafioproposta.propostas.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class ConsultaPeriodica {

    @Autowired
    PropostaRepository propostaRepository;

    @Autowired
    CartaoRepository cartaoRepository;

    @Autowired
    SolicitarCartaoClient solicitarCartaoClient;

    @Scheduled(fixedDelayString = "${periodo-consulta-cartao}")
    @Transactional
    void solicitarCartao() {
        List<Proposta> propostasElegiveisSemCartao = propostaRepository.findByStatusAnaliseFinanceiraAndCartaoIsNull(StatusAnaliseFinanceira.ELEGIVEL.name());

        for (Proposta proposta:propostasElegiveisSemCartao) {
            CartaoRequest request = solicitarCartaoClient.solicitaCartao(new SolicitarCartaoRequest(proposta.getNome(), proposta.getDocumento(), proposta.getId().toString()));
            cartaoRepository.save(request.toModel(proposta));
            proposta.associaCartao(request);
            propostaRepository.save(proposta);
        }

    }
}
