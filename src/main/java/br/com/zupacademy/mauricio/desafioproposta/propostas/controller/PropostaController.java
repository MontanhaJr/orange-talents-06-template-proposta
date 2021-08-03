package br.com.zupacademy.mauricio.desafioproposta.propostas.controller;

import br.com.zupacademy.mauricio.desafioproposta.feign.solicitacaoAnalise.AnaliseFinanceiraClient;
import br.com.zupacademy.mauricio.desafioproposta.feign.solicitacaoAnalise.AnaliseFinanceiraRequest;
import br.com.zupacademy.mauricio.desafioproposta.propostas.Proposta;
import br.com.zupacademy.mauricio.desafioproposta.propostas.dto.request.PropostaRequest;
import br.com.zupacademy.mauricio.desafioproposta.propostas.dto.response.PropostaResponse;
import br.com.zupacademy.mauricio.desafioproposta.propostas.repository.PropostaRepository;
import br.com.zupacademy.mauricio.desafioproposta.validation.ErroDeFormularioDto;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class PropostaController {

    @Autowired
    PropostaRepository propostaRepository;

    @Autowired
    AnaliseFinanceiraClient analiseFinanceiraClient;

    @Value("${criptografia.senha}")
    private String senha;
    @Value("${criptografia.chave}")
    private String chave;

    @PostMapping("/proposta")
    @Transactional
    public ResponseEntity<?> criar(@RequestBody @Valid PropostaRequest propostaRequest, UriComponentsBuilder uriBuilder) {
        TextEncryptor encryptor = Encryptors.queryableText(senha, chave);

        Optional<Proposta> documentoJaCadastrado = propostaRepository.findByDocumento(encryptor.encrypt(propostaRequest.getDocumento()));

        Proposta proposta = propostaRequest.toModel(encryptor);
        propostaRepository.save(proposta);

        try {
            analiseFinanceiraClient.consultaAnaliseFinanceira(new AnaliseFinanceiraRequest(propostaRequest.getNome(), propostaRequest.getDocumento(), proposta.getId().toString()));
            proposta.defineComoElegivel();
        } catch (FeignException e) {
            proposta.defineComoNaoElegivel();
        }

        return ResponseEntity.created(uriBuilder.path("/propostas/{id}")
                .buildAndExpand(proposta.getId())
                .toUri())
                .body(proposta.getId());
    }

    @GetMapping("/proposta/{id}")
    public ResponseEntity<?> buscarPropostaPorId(@PathVariable Long id) {
        TextEncryptor encryptor = Encryptors.queryableText(senha, chave);
        Optional<Proposta> proposta = propostaRepository.findById(id);
        if (proposta.isPresent()) {
            return ResponseEntity.ok(new PropostaResponse(proposta.get(), encryptor));
        }

        return ResponseEntity.notFound().build();
    }
}
