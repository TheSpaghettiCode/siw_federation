package it.uniroma3.siw.siw_federation.service;

import it.uniroma3.siw.siw_federation.model.Presidente;
import it.uniroma3.siw.siw_federation.repository.PresidenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PresidenteService {

    @Autowired
    private PresidenteRepository presidenteRepository;

    public boolean existsByNomeAndCognome(String nome, String cognome){
        return presidenteRepository.existsByNomeAndCognome(nome, cognome);
    }

    public Presidente savePresidente(Presidente presidente) {
        return this.presidenteRepository.save(presidente);
    }

    public List<Presidente> findAll() {
        return (List<Presidente>) presidenteRepository.findAll();
    }

    public Presidente findById(Long id){
        return presidenteRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id){
        presidenteRepository.deleteById(id);
    }


    public List<Presidente> searchPresidente(String query) {
      return presidenteRepository.findByNomeContainingIgnoreCaseOrCognomeContainingIgnoreCase(query, query);
    }
}


