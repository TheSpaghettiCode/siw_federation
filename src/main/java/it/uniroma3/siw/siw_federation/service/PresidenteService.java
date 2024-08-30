package it.uniroma3.siw.siw_federation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.siw_federation.model.Presidente;
import it.uniroma3.siw.siw_federation.repository.PresidenteRepository;

@Service
public class PresidenteService {

    @Autowired
    private PresidenteRepository presidenteRepository;

    // Salva un nuovo presidente o aggiorna un presidente esistente
    @Transactional
    public Presidente savePresidente(Presidente presidente) {
        return presidenteRepository.save(presidente);
    }

    // Recupera tutti i presidenti dal database
    @Transactional(readOnly = true)
    public List<Presidente> getAllPresidenti() {
        return presidenteRepository.findAll();
    }

    // Recupera un presidente per ID
    @Transactional(readOnly = true)
    public Presidente getPresidenteById(Long id) {
        Optional<Presidente> result = presidenteRepository.findById(id);
        return result.orElse(null);
    }

    // Recupera un presidente per CF
    @Transactional(readOnly = true)
    public Presidente getPresidenteByCF(String CF) {
        Optional<Presidente> result = presidenteRepository.findByCF(CF);
        return result.orElse(null);
    }

    // Recupera un presidente per nome e cognome
    @Transactional(readOnly = true)
    public Presidente getPresidenteByNomeAndCognome(String nome, String cognome) {
        Optional<Presidente> result = presidenteRepository.findByNomeAndCognome(nome, cognome);
        return result.orElse(null);
    }

    // Cancella un presidente per ID
    @Transactional
    public void deletePresidenteById(Long id) {
        presidenteRepository.deleteById(id);
    }

    // Verifica se un presidente con un certo CF esiste già
    @Transactional(readOnly = true)
    public boolean existsByCF(String CF) {
        return presidenteRepository.findByCF(CF).isPresent();
    }
}



