package it.uniroma3.siw.siw_federation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.siw_federation.model.Giocatore;

@Repository
public interface GiocatoreRepository extends JpaRepository<Giocatore, Long> {

    // Trova tutti i giocatori associati a una squadra
    List<Giocatore> findBySquadraId(Long squadraId);

    // Trova un giocatore per CF
    Giocatore findByCF(String CF);

    // Trova tutti i giocatori con un certo ruolo
    List<Giocatore> findByRuolo(String ruolo);

    // Trova tutti i giocatori ordinati per cognome
    List<Giocatore> findAllByOrderByCognomeAsc();
}



