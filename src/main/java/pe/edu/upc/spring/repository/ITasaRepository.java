package pe.edu.upc.spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Tasa;

@Repository
public interface ITasaRepository extends JpaRepository<Tasa, Integer> {
}
