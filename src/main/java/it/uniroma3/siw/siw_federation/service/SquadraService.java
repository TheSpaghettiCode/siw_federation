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

    @Transactional
    public Squadra saveSquadra(Squadra squadra) {
        return squadraRepository.save(squadra);
    }

    @Transactional
    public Squadra updateSquadra(Long id, Squadra squadraDetails) {
        Squadra squadra = squadraRepository.findById(id).orElseThrow(() -> new RuntimeException("Squadra non trovata"));
        squadra.setNome(squadraDetails.getNome());
        squadra.setDataFondazione(squadraDetails.getDataFondazione());
        squadra.setIndirizzoSede(squadraDetails.getIndirizzoSede());
        return squadraRepository.save(squadra);
    }

    @Transactional(readOnly = true)
    public List<Squadra> findAllSquadre() {
        return squadraRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Squadra> findSquadraById(Long id) {
        return squadraRepository.findById(id);
    }

    @Transactional
    public void deleteSquadra(Long id) {
        squadraRepository.deleteById(id);
    }
}

