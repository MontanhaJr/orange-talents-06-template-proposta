package br.com.zupacademy.mauricio.desafioproposta.rotinas;

import br.com.zupacademy.mauricio.desafioproposta.feign.solicitarCartao.SolicitarCartaoRequest;
import br.com.zupacademy.mauricio.desafioproposta.feign.solicitarCartao.SolicitarCartaoResponse;
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
    SolicitarCartaoClient solicitarCartaoClient;

    @Scheduled(fixedDelayString = "${periodo-consulta-cartao}")
    @Transactional
    void solicitarCartao() {
        List<Proposta> propostasElegiveisSemCartao = propostaRepository.findByStatusAnaliseFinanceiraAndIdCartaoIsNull(StatusAnaliseFinanceira.ELEGIVEL.name());

        for (Proposta proposta:propostasElegiveisSemCartao) {
            SolicitarCartaoResponse solicitarCartaoResponse = solicitarCartaoClient.solicitaCartao(new SolicitarCartaoRequest(proposta.getNome(), proposta.getDocumento(), proposta.getId().toString()));
            proposta.associaCartao(solicitarCartaoResponse.getId());
            propostaRepository.save(proposta);
        }

    }
}
