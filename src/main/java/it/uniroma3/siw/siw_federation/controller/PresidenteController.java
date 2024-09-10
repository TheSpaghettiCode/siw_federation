package it.uniroma3.siw.siw_federation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.siw.siw_federation.model.Presidente;
import it.uniroma3.siw.siw_federation.service.CredentialsService;
import it.uniroma3.siw.siw_federation.service.PresidenteService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/presidenti")
public class PresidenteController {

    @Autowired
    private PresidenteService presidenteService;

    @Autowired
    private CredentialsService credentialsService;

    // Mostra la lista di tutti i presidenti
    @GetMapping("/all")
    public String getAllPresidenti(Model model) {
        List<Presidente> presidenti = presidenteService.getAllPresidenti();
        model.addAttribute("presidenti", presidenti);
        return "presidenti/listaPresidenti"; // Indica il template Thymeleaf per la visualizzazione della lista
    }

    // Mostra i dettagli di un singolo presidente
    @GetMapping("/{id}")
    public String getPresidente(@PathVariable("id") Long id, Model model) {
        Presidente presidente = presidenteService.getPresidenteById(id);
        if (presidente != null) {
            model.addAttribute("presidente", presidente);
            return "presidenti/dettagliPresidente"; // Indica il template Thymeleaf per la visualizzazione dei dettagli
        }
        return "redirect:/presidenti"; // Se l'ID non esiste, torna alla lista dei presidenti
    }


    // Mostra il form per modificare un presidente esistente
    @GetMapping("/modifica/{id}")
    public String showUpdatePresidenteForm(@PathVariable("id") Long id, Model model) {
        Presidente presidente = presidenteService.getPresidenteById(id);
        if (presidente != null) {
            model.addAttribute("presidente", presidente);
            return "presidenti/modificaPresidente"; // Indica il template Thymeleaf per il form di modifica
        }
        return "redirect:/presidenti"; // Se l'ID non esiste, torna alla lista dei presidenti
    }

    // Gestisce l'aggiornamento di un presidente esistente
    @PostMapping("/modifica/{id}")
    public String updatePresidente(@PathVariable("id") Long id, 
                                   @Valid @ModelAttribute("presidente") Presidente presidente, 
                                   BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "presidenti/modificaPresidente"; // Ritorna al form se ci sono errori di validazione
        }
        presidenteService.savePresidente(presidente); // Salva le modifiche
        return "redirect:/presidenti"; // Reindirizza alla lista dei presidenti dopo l'aggiornamento
    }

    // Gestisce la cancellazione di un presidente
    @GetMapping("/elimina/{id}")
    public String deletePresidente(@PathVariable("id") Long id) {
        credentialsService.deleteByPresidenteId(id);
        presidenteService.deletePresidenteById(id);
        return "redirect:/presidenti/all"; // Reindirizza alla lista dei presidenti dopo la cancellazione
    }
}
