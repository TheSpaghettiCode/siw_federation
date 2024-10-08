/*package it.uniroma3.siw.siw_federation.repository;

import java.util.List;

//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import it.uniroma3.siw.siw_federation.model.Presidente;


public interface PresidenteRepository extends CrudRepository<Presidente,Long>{

   boolean existsByNomeAndCognome(String nome, String cognome);

   List<Presidente> findByNomeContainingIgnoreCaseOrCognomeContainingIgnoreCase(String nome , String cognome);


}*/

package it.uniroma3.siw.siw_federation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.siw_federation.model.Presidente;

@Repository
public interface PresidenteRepository extends JpaRepository<Presidente, Long> {

    // Trova un presidente per codice fiscale (CF)
    Optional<Presidente> findByCF(String CF);

    // Trova un presidente per nome e cognome
    Optional<Presidente> findByNomeAndCognome(String nome, String cognome);
}
