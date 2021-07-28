package br.com.zupacademy.mauricio.desafioproposta.biometrias.repository;

import br.com.zupacademy.mauricio.desafioproposta.biometrias.Biometria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiometriaRepository extends JpaRepository<Biometria, Long> {
}
