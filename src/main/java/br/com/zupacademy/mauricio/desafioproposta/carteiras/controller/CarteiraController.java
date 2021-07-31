package br.com.zupacademy.mauricio.desafioproposta.carteiras.controller;

import br.com.zupacademy.mauricio.desafioproposta.avisos.dto.request.AvisoViagemRequest;
import br.com.zupacademy.mauricio.desafioproposta.bloqueios.Bloqueio;
import br.com.zupacademy.mauricio.desafioproposta.bloqueios.dto.request.BloqueioCartaoRequest;
import br.com.zupacademy.mauricio.desafioproposta.bloqueios.dto.response.BloqueioCartaoResponse;
import br.com.zupacademy.mauricio.desafioproposta.bloqueios.repository.BloqueioRepository;
import br.com.zupacademy.mauricio.desafioproposta.cartao.Cartao;
import br.com.zupacademy.mauricio.desafioproposta.cartao.CartaoRepository;
import br.com.zupacademy.mauricio.desafioproposta.carteiras.Carteira;
import br.com.zupacademy.mauricio.desafioproposta.carteiras.dto.request.CarteiraRequest;
import br.com.zupacademy.mauricio.desafioproposta.carteiras.dto.response.CarteiraResponse;
import br.com.zupacademy.mauricio.desafioproposta.carteiras.repository.CarteiraRepository;
import br.com.zupacademy.mauricio.desafioproposta.feign.cartao.CartaoClient;
import br.com.zupacademy.mauricio.desafioproposta.validation.ErroDeFormularioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class CarteiraController {

    @Autowired
    CartaoRepository cartaoRepository;
    @Autowired
    CarteiraRepository carteiraRepository;
    @Autowired
    CartaoClient cartaoClient;

    @Transactional
    @PostMapping("/carteira/{idCartao}")
    public ResponseEntity<?> associarCarteira(@PathVariable String idCartao,
                                              @RequestBody @Valid CarteiraRequest carteiraRequest,
                                              UriComponentsBuilder uriBuilder) {

        Optional<Cartao> cartao = cartaoRepository.findById(idCartao);
        if (!cartao.isPresent())
        {
            return ResponseEntity.notFound().build();
        }

        if (cartao.get().cartaoJaCadastradoNaCarteira(carteiraRequest.getCarteira()))
        {
            return ResponseEntity.unprocessableEntity()
                    .body(new ErroDeFormularioDto("idCartao", "Esse cartão já está cadastrado nessa carteira!"));
        }

        Carteira carteira = new Carteira(carteiraRequest.getEmail(), carteiraRequest.getCarteira(), cartao.get());

        try {
            CarteiraResponse bloqueioResponse = cartaoClient.adicionarNaCarteira(idCartao, carteiraRequest);
            if (bloqueioResponse.getResultado().equals("ASSOCIADA"))
            {
                carteiraRepository.save(carteira);
                cartao.get().adicionarCarteira(carteira);
                cartaoRepository.save(cartao.get());
                return ResponseEntity.created(uriBuilder.path("/carteira/{id}")
                        .buildAndExpand(carteira.getId())
                        .toUri())
                        .build();
            }
            return ResponseEntity.internalServerError().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
