package it.uniroma3.siw.siw_federation.service;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.siw_federation.model.Credentials;
import it.uniroma3.siw.siw_federation.repository.CredentialsRepository;
import jakarta.transaction.Transactional;

@Service
public class CredentialsService {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    protected CredentialsRepository credentialsRepository;

    public Credentials saveCredentialsNoPassword(Credentials credentials){
        return this.credentialsRepository.save(credentials);
    }

    @Transactional
    public Credentials getCredentials(Long id) {
        Optional<Credentials> result = this.credentialsRepository.findById(id);
        return result.orElse(null);
    }

    @Transactional
    public Credentials getCredentials(String username) {
        Optional<Credentials> result = this.credentialsRepository.findByUsername(username);
        return result.orElse(null);
    }

    @Transactional
    public Credentials saveCredentials(Credentials credentials, String role) {
        if(role.equals("GIOCATORE")){
            credentials.setRole(Credentials.GIOCATORE_ROLE);
        }
        else if(role.equals("PRESIDENTE")){
            credentials.setRole(Credentials.PRESIDENTE_ROLE);
        }

        credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        return this.credentialsRepository.save(credentials);
    }

    public void deleteByPresidenteId(Long Id){
        this.credentialsRepository.deleteByPresidenteId(Id);
    }

    public void deleteByGiocatoreId(Long Id){
        this.credentialsRepository.deleteByGiocatoreId(Id);
    }

}
