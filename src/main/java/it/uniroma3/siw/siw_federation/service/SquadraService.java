package it.uniroma3.siw.siw_federation.service;

import it.uniroma3.siw.siw_federation.model.Squadra;
import it.uniroma3.siw.siw_federation.repository.SquadraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SquadraService {

    @Autowired
    private SquadraRepository squadraRepository;

    // Salva una nuova squadra o aggiorna una squadra esistente
    @Transactional
    public Squadra saveSquadra(Squadra squadra) {
        return squadraRepository.save(squadra);
    }
    
    // Recupera tutte le squadre dal database
    @Transactional(readOnly = true)
    public List<Squadra> getAllSquadre() {
        return squadraRepository.findAll();
    }

    @Transactional
    public Squadra updateSquadra(Long id, Squadra squadraDetails) {
        Squadra squadra = squadraRepository.findById(id).orElseThrow(() -> new RuntimeException("Squadra non trovata"));
        squadra.setNome(squadraDetails.getNome());
        squadra.setDataFondazione(squadraDetails.getDataFondazione());
        squadra.setIndirizzoSede(squadraDetails.getIndirizzoSede());
        return squadraRepository.save(squadra);
    }

    // Recupera tutte le squadre ordinate per nome
    @Transactional(readOnly = true)
    public List<Squadra> getAllSquadreOrdinatePerNome() {
        return squadraRepository.findAllByOrderByNomeAsc();
    }

    // Recupera una squadra per ID
    @Transactional(readOnly = true)
    public Squadra getSquadraById(Long id) {
        Optional<Squadra> result = squadraRepository.findById(id);
        return result.orElse(null);
    }

    // Recupera una squadra per nome
    @Transactional(readOnly = true)
    public Squadra getSquadraByNome(String nome) {
        Optional<Squadra> result = squadraRepository.findByNome(nome);
        return result.orElse(null);
    }

    // Recupera tutte le squadre con un certo presidente
    @Transactional(readOnly = true)
    public List<Squadra> getSquadreByPresidente(Long presidenteId) {
        return squadraRepository.findByPresidenteId(presidenteId);
    }

    // Cancella una squadra per ID
    @Transactional
    public void deleteSquadraById(Long id) {
        squadraRepository.deleteById(id);
    }

    // Verifica se una squadra con un certo nome esiste già
    @Transactional(readOnly = true)
    public boolean existsByNome(String nome) {
        return squadraRepository.findByNome(nome).isPresent();
    }
}

