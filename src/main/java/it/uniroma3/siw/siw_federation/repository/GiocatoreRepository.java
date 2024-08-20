package it.uniroma3.siw.siw_federation.repository;

//import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.siw_federation.model.Giocatore;
import it.uniroma3.siw.siw_federation.model.Squadra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface GiocatoreRepository extends JpaRepository<Giocatore, Long> {

   boolean existsByNomeAndCognome(String nome, String cognome);

    public List<Giocatore> findBySquadra(Squadra squadra);

}


