package it.uniroma3.siw.siw_federation.repository;

import java.util.List;

//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import it.uniroma3.siw.siw_federation.model.Presidente;


public interface PresidenteRepository extends CrudRepository<Presidente,Long>{

   boolean existsByNomeAndCognome(String nome, String cognome);

   List<Presidente> findByNomeContainingIgnoreCaseOrCognomeContainingIgnoreCase(String nome , String cognome);


}
