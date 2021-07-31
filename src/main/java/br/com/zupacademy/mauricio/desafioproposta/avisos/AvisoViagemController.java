package br.com.zupacademy.mauricio.desafioproposta.avisos;

import br.com.zupacademy.mauricio.desafioproposta.avisos.dto.request.AvisoViagemRequest;
import br.com.zupacademy.mauricio.desafioproposta.avisos.dto.response.AvisoViagemResponse;
import br.com.zupacademy.mauricio.desafioproposta.avisos.repository.AvisoViagemRepository;
import br.com.zupacademy.mauricio.desafioproposta.cartao.Cartao;
import br.com.zupacademy.mauricio.desafioproposta.cartao.CartaoRepository;
import br.com.zupacademy.mauricio.desafioproposta.feign.cartao.CartaoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class AvisoViagemController {

    @Autowired
    CartaoRepository cartaoRepository;

    @Autowired
    AvisoViagemRepository avisoViagemRepository;

    @Autowired
    CartaoClient cartaoClient;

    @PostMapping("/aviso/{idCartao}")
    public ResponseEntity<?> avisarViagem(@PathVariable String idCartao,
                                          @RequestBody @Valid AvisoViagemRequest avisoViagemRequest,
                                          HttpServletRequest servletRequest) {

        String userAgent = servletRequest.getHeader("user-agent");
        String ipCliente = servletRequest.getHeader("X-FORWARDED-FOR");
        if (ipCliente == null) {
            ipCliente = servletRequest.getRemoteAddr();
        }

        Optional<Cartao> cartao = cartaoRepository.findById(idCartao);
        if (!cartao.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        AvisoViagem avisoViagem = avisoViagemRequest.toModel(userAgent, ipCliente, cartao.get());

        try {
            AvisoViagemResponse avisoViagemResponse = cartaoClient.avisarViagem(idCartao, avisoViagemRequest);
            if (avisoViagemResponse.getResultado().equals("CRIADO")) {
                avisoViagemRepository.save(avisoViagem);
                cartao.get().adicionarAviso(avisoViagem);
                cartaoRepository.save(cartao.get());
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
