package it.uniroma3.siw.siw_federation.repository;

import it.uniroma3.siw.siw_federation.model.Squadra;
import it.uniroma3.siw.siw_federation.model.Giocatore;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SquadraRepository extends JpaRepository<Squadra, Long> {

   /*@Query("SELECT g FROM Giocatore g WHERE g.giocatori.id = :giocatoriId")
    public List<Giocatore> findAllByGiocatoreId(Long giocatoreId);*/

}



