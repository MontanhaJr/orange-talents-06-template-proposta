package br.com.zupacademy.mauricio.desafioproposta.biometrias;

import br.com.zupacademy.mauricio.desafioproposta.biometrias.dto.request.BiometriaRequest;
import br.com.zupacademy.mauricio.desafioproposta.biometrias.repository.BiometriaRepository;
import br.com.zupacademy.mauricio.desafioproposta.cartao.Cartao;
import br.com.zupacademy.mauricio.desafioproposta.cartao.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class BiometriaController {

    @Autowired
    BiometriaRepository biometriaRepository;

    @Autowired
    CartaoRepository cartaoRepository;

    @PostMapping("/biometria/{idCartao}")
    public ResponseEntity<?> adicionarBiometria(@PathVariable String idCartao, @RequestBody @Valid BiometriaRequest request, UriComponentsBuilder uriBuilder) {
        Optional<Cartao> cartao = cartaoRepository.findById(idCartao);
        if (!cartao.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Biometria biometria = request.toModel(cartao.get());
        biometriaRepository.save(biometria);

        return ResponseEntity.created(uriBuilder.path("/biometria/{id}")
                .build(biometria.getId())).build();
    }
}
