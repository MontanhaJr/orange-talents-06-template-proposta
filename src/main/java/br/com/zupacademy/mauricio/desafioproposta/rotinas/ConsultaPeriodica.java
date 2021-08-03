package br.com.zupacademy.mauricio.desafioproposta.rotinas;

import br.com.zupacademy.mauricio.desafioproposta.cartao.CartaoRepository;
import br.com.zupacademy.mauricio.desafioproposta.cartao.dto.request.CartaoRequest;
import br.com.zupacademy.mauricio.desafioproposta.cartao.dto.request.SolicitarCartaoRequest;
import br.com.zupacademy.mauricio.desafioproposta.feign.solicitacaoAnalise.StatusAnaliseFinanceira;
import br.com.zupacademy.mauricio.desafioproposta.feign.cartao.CartaoClient;
import br.com.zupacademy.mauricio.desafioproposta.propostas.Proposta;
import br.com.zupacademy.mauricio.desafioproposta.propostas.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
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
    CartaoClient cartaoClient;

    @Value("${criptografia.senha}")
    private String senha;
    @Value("${criptografia.chave}")
    private String chave;

    @Scheduled(fixedDelayString = "${periodo-consulta-cartao}")
    @Transactional
    void solicitarCartao() {
        TextEncryptor encryptor = Encryptors.queryableText(senha, chave);

        List<Proposta> propostasElegiveisSemCartao = propostaRepository.findByStatusAnaliseFinanceiraAndCartaoIsNull(StatusAnaliseFinanceira.ELEGIVEL.name());

        for (Proposta proposta:propostasElegiveisSemCartao) {
            CartaoRequest request = cartaoClient.solicitaCartao(new SolicitarCartaoRequest(proposta.getNome(), encryptor.encrypt(proposta.getDocumento()), proposta.getId().toString()));
            cartaoRepository.save(request.toModel(proposta));
            proposta.associaCartao(request);
            propostaRepository.save(proposta);
        }

    }
}
