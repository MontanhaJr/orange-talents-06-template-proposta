package br.com.zupacademy.mauricio.desafioproposta.bloqueios.controller;

import br.com.zupacademy.mauricio.desafioproposta.bloqueios.Bloqueio;
import br.com.zupacademy.mauricio.desafioproposta.bloqueios.dto.request.BloqueioCartaoRequest;
import br.com.zupacademy.mauricio.desafioproposta.bloqueios.dto.response.BloqueioCartaoResponse;
import br.com.zupacademy.mauricio.desafioproposta.bloqueios.repository.BloqueioRepository;
import br.com.zupacademy.mauricio.desafioproposta.cartao.Cartao;
import br.com.zupacademy.mauricio.desafioproposta.cartao.CartaoRepository;
import br.com.zupacademy.mauricio.desafioproposta.feign.cartao.CartaoClient;
import br.com.zupacademy.mauricio.desafioproposta.validation.ErroDeFormularioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class BloqueioController {

    @Autowired
    CartaoRepository cartaoRepository;
    @Autowired
    BloqueioRepository bloqueioRepository;
    @Autowired
    CartaoClient cartaoClient;

    @PostMapping("/bloqueio/{idCartao}")
    public ResponseEntity<?> bloquear(@PathVariable String idCartao,
                                            HttpServletRequest request) {

        String userAgent = request.getHeader("User-Agent");
        String ipCliente = request.getHeader("X-FORWARDED-FOR");
        if (ipCliente == null) {
            ipCliente = request.getRemoteAddr();
        }

        Optional<Cartao> cartao = cartaoRepository.findById(idCartao);
        if (!cartao.isPresent())
        {
            return ResponseEntity.notFound().build();
        }

        if (cartao.get().bloqueado())
        {
            return ResponseEntity.unprocessableEntity()
                    .body(new ErroDeFormularioDto("idCartao", "Esse cartão já está bloqueado!"));
        }

        Bloqueio bloqueio = new Bloqueio(userAgent, ipCliente, cartao.get());

        try {
            BloqueioCartaoResponse bloqueioResponse = cartaoClient.bloquearCartao(idCartao, new BloqueioCartaoRequest("DesafioProposta"));
            if (bloqueioResponse.getResultado().equals("BLOQUEADO"))
            {
                bloqueioRepository.save(bloqueio);
                cartao.get().adicionarBloqueio(bloqueio);
                cartaoRepository.save(cartao.get());
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.internalServerError().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
