package br.com.zupacademy.mauricio.desafioproposta.proposta.controller;

import br.com.zupacademy.mauricio.desafioproposta.proposta.Proposta;
import br.com.zupacademy.mauricio.desafioproposta.proposta.dto.request.PropostaRequest;
import br.com.zupacademy.mauricio.desafioproposta.proposta.repository.PropostaRepository;
import br.com.zupacademy.mauricio.desafioproposta.validation.ErroDeFormularioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class PropostaController {

    @Autowired
    PropostaRepository propostaRepository;

    @PostMapping("/proposta")
    public ResponseEntity<?> criar (@RequestBody @Valid PropostaRequest propostaRequest, UriComponentsBuilder uriBuilder) {
        Optional<Proposta> documentoJaCadastrado = propostaRepository.findByDocumento(propostaRequest.getDocumento());

        if(documentoJaCadastrado.isPresent()) {
            return ResponseEntity.unprocessableEntity()
                    .body(new ErroDeFormularioDto("documento", "Já existe um cadastro de proposta para esse documento!"));
        }
        Proposta proposta = propostaRequest.toModel();
        propostaRepository.save(proposta);

        return ResponseEntity.created(uriBuilder.path("/propostas/{id}")
                .buildAndExpand(proposta.getId())
                .toUri())
                .body(proposta);
    }
}
