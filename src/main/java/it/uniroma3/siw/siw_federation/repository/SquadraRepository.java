package it.uniroma3.siw.siw_federation.repository;

import it.uniroma3.siw.siw_federation.model.Squadra;
//import it.uniroma3.siw.siw_federation.model.Giocatore;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SquadraRepository extends JpaRepository<Squadra, Long> {

   /*@Query("SELECT g FROM Giocatore g WHERE g.giocatori.id = :giocatoriId")
    public List<Giocatore> findAllByGiocatoreId(Long giocatoreId);*/

      // Trova una squadra per nome
      Optional<Squadra> findByNome(String nome);

      // Trova tutte le squadre ordinate per nome
      List<Squadra> findAllByOrderByNomeAsc();
  
      // Trova tutte le squadre con il presidente specifico
      List<Squadra> findByPresidenteId(Long presidenteId);

}



