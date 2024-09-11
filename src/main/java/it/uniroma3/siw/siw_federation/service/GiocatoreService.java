package it.uniroma3.siw.siw_federation.service;

import it.uniroma3.siw.siw_federation.model.Giocatore;
import it.uniroma3.siw.siw_federation.model.Squadra;
import it.uniroma3.siw.siw_federation.repository.GiocatoreRepository;
import it.uniroma3.siw.siw_federation.repository.SquadraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GiocatoreService {

    @Autowired
    private GiocatoreRepository giocatoreRepository;

    @Autowired
    private SquadraRepository squadraRepository;

    @Transactional
    public Giocatore saveGiocatore(Giocatore giocatore) {
        return giocatoreRepository.save(giocatore);
    }

    @Transactional
    public Giocatore updateGiocatore(Long id, Giocatore giocatoreDetails) {
        Giocatore giocatore = giocatoreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Giocatore non trovato"));
        giocatore.setNome(giocatoreDetails.getNome());
        giocatore.setCognome(giocatoreDetails.getCognome());
        giocatore.setCF(giocatoreDetails.getCF());
        giocatore.setDataNascita(giocatoreDetails.getDataNascita());
        giocatore.setLuogoNascita(giocatoreDetails.getLuogoNascita());
        giocatore.setRuolo(giocatoreDetails.getRuolo());
        return giocatoreRepository.save(giocatore);
    }

    // Recupera tutti i giocatori dal database
    @Transactional(readOnly = true)
    public List<Giocatore> getAllGiocatori() {
        return giocatoreRepository.findAll();
    }

    // Recupera tutti i giocatori ordinati per cognome
    @Transactional(readOnly = true)
    public List<Giocatore> getAllGiocatoriOrdinatiPerCognome() {
        return giocatoreRepository.findAllByOrderByCognomeAsc();
    }

    // Recupera un giocatore per ID
    @Transactional(readOnly = true)
    public Giocatore getGiocatoreById(Long id) {
        Optional<Giocatore> result = giocatoreRepository.findById(id);
        return result.orElse(null);
    }

        // Recupera un giocatore per CF
        @Transactional(readOnly = true)
        public Giocatore getGiocatoreByCF(String CF) {
            return giocatoreRepository.findByCF(CF);
        }
    
        // Recupera tutti i giocatori associati a una squadra
        @Transactional(readOnly = true)
        public List<Giocatore> getGiocatoriBySquadra(Long squadraId) {
            return giocatoreRepository.findBySquadraId(squadraId);
        }
    
        // Recupera tutti i giocatori con un certo ruolo
        @Transactional(readOnly = true)
        public List<Giocatore> getGiocatoriByRuolo(String ruolo) {
            return giocatoreRepository.findByRuolo(ruolo);
        }

    @Transactional
    public void addGiocatoreToSquadra(Long giocatoreId, Long squadraId) {
        Giocatore giocatore = giocatoreRepository.findById(giocatoreId)
                .orElseThrow(() -> new RuntimeException("Giocatore non trovato"));
        Squadra squadra = squadraRepository.findById(squadraId)
                .orElseThrow(() -> new RuntimeException("Squadra non trovata"));
        giocatore.setSquadra(squadra);
        giocatoreRepository.save(giocatore);
    }

    @Transactional
    public void deleteGiocatoreFromSquadra(Long giocatoreId) {
        Giocatore giocatore = giocatoreRepository.findById(giocatoreId)
                .orElseThrow(() -> new RuntimeException("Giocatore non trovato"));
        giocatore.setSquadra(null);
        giocatoreRepository.save(giocatore);
    }

    // Cancella un giocatore per ID
    @Transactional
    public void deleteGiocatoreById(Long id) {
        giocatoreRepository.deleteById(id);
    }

    public Optional<Giocatore> findById(Long id) {
        return giocatoreRepository.findById(id);
    }


}
