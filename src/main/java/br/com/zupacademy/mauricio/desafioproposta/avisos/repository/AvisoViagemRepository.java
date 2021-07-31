package br.com.zupacademy.mauricio.desafioproposta.avisos.repository;

import br.com.zupacademy.mauricio.desafioproposta.avisos.AvisoViagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisoViagemRepository extends JpaRepository<AvisoViagem, Long> {
}
