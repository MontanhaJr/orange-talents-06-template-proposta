package br.com.zupacademy.mauricio.desafioproposta.carteiras.repository;

import br.com.zupacademy.mauricio.desafioproposta.carteiras.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
}
