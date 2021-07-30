package br.com.zupacademy.mauricio.desafioproposta.bloqueios.repository;

import br.com.zupacademy.mauricio.desafioproposta.bloqueios.Bloqueio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloqueioRepository extends JpaRepository<Bloqueio, Long> {
}
