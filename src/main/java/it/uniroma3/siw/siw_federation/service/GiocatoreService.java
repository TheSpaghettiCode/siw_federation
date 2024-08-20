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
    public Giocatore saveGiocatore(Giocatore giocatore, Long squadraId) {
        Squadra squadra = squadraRepository.findById(squadraId)
                .orElseThrow(() -> new RuntimeException("Squadra non trovata"));
        giocatore.setSquadra(squadra);
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

    @Transactional(readOnly = true)
    public List<Giocatore> findAllGiocatori() {
        return giocatoreRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Giocatore> findGiocatoreById(Long id) {
        return giocatoreRepository.findById(id);
    }

    @Transactional
    public void deleteGiocatore(Long id) {
        giocatoreRepository.deleteById(id);
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
    public void removeGiocatoreFromSquadra(Long giocatoreId) {
        Giocatore giocatore = giocatoreRepository.findById(giocatoreId)
                .orElseThrow(() -> new RuntimeException("Giocatore non trovato"));
        giocatore.setSquadra(null);
        giocatoreRepository.save(giocatore);
    }
}
