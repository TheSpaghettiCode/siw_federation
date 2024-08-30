/*package it.uniroma3.siw.siw_federation.controller;


import it.uniroma3.siw.siw_federation.model.Presidente;
import it.uniroma3.siw.siw_federation.model.Squadra;
import it.uniroma3.siw.siw_federation.service.PresidenteService;
import it.uniroma3.siw.siw_federation.service.SquadraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/squadra")
public class SquadraController {

    @Autowired
    private SquadraService squadraService;
    @Autowired
    private PresidenteService presidenteService;

    // Consultazione dell'elenco delle squadre
    @GetMapping("/squadre")
    public String getAllSquadre(Model model) {
        List<Squadra> squadre = squadraService.findAllSquadre();
        model.addAttribute("squadre", squadre);
        return "all/squadre.html";
    }

    // Mostra il form per creare una nuova squadra
    @GetMapping("/nuova")
    public String showCreateSquadraForm(Model model) {
        model.addAttribute("squadra", new Squadra());
        List<Presidente> presidenti = presidenteService.findAll();
        model.addAttribute("presidenti", presidenti);
        return "admin/nuova-squadra.html";
    }

    // Salva una nuova squadra
    @PostMapping("/salva")
    public String saveSquadra(@ModelAttribute Squadra squadra) {
        squadraService.saveSquadra(squadra);
        return "redirect:/squadra/squadre";
    }

    // Mostra il form per modificare una squadra esistente
    @GetMapping("/modifica/{id}")
    public String showUpdateSquadraForm(@PathVariable Long id, Model model) {
        Squadra squadra = squadraService.findSquadraById(id)
            .orElseThrow(() -> new RuntimeException("Squadra non trovata"));
        model.addAttribute("squadra", squadra);
        return "admin/nuova-squadra.html";
    }

    // Aggiorna una squadra esistente
    @PostMapping("/aggiorna/{id}")
    public String updateSquadra(@PathVariable Long id, @ModelAttribute Squadra squadraDetails) {
        squadraService.updateSquadra(id, squadraDetails);
        return "redirect:/squadra/squadre";
    }

    // Cancella una squadra esistente
    @GetMapping("/cancella/{id}")
    public String deleteSquadra(@PathVariable Long id) {
        squadraService.deleteSquadra(id);
        return "redirect:/squadra/squadre";
    }
}*/ 

package it.uniroma3.siw.siw_federation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.siw_federation.model.Presidente;
import it.uniroma3.siw.siw_federation.model.Squadra;
import it.uniroma3.siw.siw_federation.service.PresidenteService;
import it.uniroma3.siw.siw_federation.service.SquadraService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/squadre")
public class SquadraController {

    @Autowired
    private SquadraService squadraService;

    @Autowired
    private PresidenteService presidenteService;

    // Mostra la lista di tutte le squadre
    @GetMapping("/all")
    public String getAllSquadre(Model model) {
        List<Squadra> squadre = squadraService.getAllSquadre();
        model.addAttribute("squadre", squadre);
        return "squadre/listaSquadre.html"; // Indica il template Thymeleaf per la visualizzazione della lista delle squadre
    }

    // Mostra i dettagli di una singola squadra
    @GetMapping("/{id}")
    public String getSquadra(@PathVariable("id") Long id, Model model) {
        Squadra squadra = squadraService.getSquadraById(id);
        if (squadra != null) {
            model.addAttribute("squadra", squadra);
            return "squadre/dettagliSquadra.html"; // Indica il template Thymeleaf per la visualizzazione dei dettagli della squadra
        }
        return "redirect:/squadre"; // Se l'ID non esiste, torna alla lista delle squadre
    }

    // Mostra il form per creare una nuova squadra
    @GetMapping("/nuova")
    public String showCreateSquadraForm(Model model) {
        model.addAttribute("squadra", new Squadra());
        model.addAttribute("presidenti", presidenteService.getAllPresidenti()); // Popola il dropdown dei presidenti
        return "squadre/nuovaSquadra.html"; // Indica il template Thymeleaf per il form di creazione
    }

    // Gestisce la creazione di una nuova squadra
    @PostMapping("/nuova")
    public String createSquadra(@Valid @ModelAttribute("squadra") Squadra squadra, 
                                BindingResult bindingResult, 
                                @RequestParam("presidenteId") Long presidenteId,
                                Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("presidenti", presidenteService.getAllPresidenti());
            return "squadre/nuovaSquadra.html"; // Ritorna al form se ci sono errori di validazione
        }

        Presidente presidente = presidenteService.getPresidenteById(presidenteId);
        if (presidente == null) {
            model.addAttribute("message", "Il presidente selezionato non esiste");
            model.addAttribute("presidenti", presidenteService.getAllPresidenti());
            return "squadre/nuovaSquadra.html"; // Ritorna al form se il presidente non esiste
        }

        squadra.setPresidente(presidente);
        squadraService.saveSquadra(squadra);
        return "redirect:/squadre"; // Reindirizza alla lista delle squadre dopo la creazione
    }

    // Mostra il form per modificare una squadra esistente
    @GetMapping("/modifica/{id}")
    public String showUpdateSquadraForm(@PathVariable("id") Long id, Model model) {
        Squadra squadra = squadraService.getSquadraById(id);
        if (squadra != null) {
            model.addAttribute("squadra", squadra);
            model.addAttribute("presidenti", presidenteService.getAllPresidenti());
            return "squadre/modificaSquadra.html"; // Indica il template Thymeleaf per il form di modifica
        }
        return "redirect:/squadre"; // Se l'ID non esiste, torna alla lista delle squadre
    }

    // Gestisce l'aggiornamento di una squadra esistente
    @PostMapping("/modifica/{id}")
    public String updateSquadra(@PathVariable("id") Long id,
                                @Valid @ModelAttribute("squadra") Squadra squadra, 
                                BindingResult bindingResult, 
                                @RequestParam("presidenteId") Long presidenteId,
                                Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("presidenti", presidenteService.getAllPresidenti());
            return "squadre/modificaSquadra.html"; // Ritorna al form se ci sono errori di validazione
        }

        Presidente presidente = presidenteService.getPresidenteById(presidenteId);
        if (presidente == null) {
            model.addAttribute("message", "Il presidente selezionato non esiste");
            model.addAttribute("presidenti", presidenteService.getAllPresidenti());
            return "squadre/modificaSquadra.html"; // Ritorna al form se il presidente non esiste
        }

        squadra.setPresidente(presidente);
        squadraService.saveSquadra(squadra);
        return "redirect:/squadre"; // Reindirizza alla lista delle squadre dopo l'aggiornamento
    }

    // Gestisce la cancellazione di una squadra
    @GetMapping("/elimina/{id}")
    public String deleteSquadra(@PathVariable("id") Long id) {
        squadraService.deleteSquadraById(id);
        return "redirect:/squadre"; // Reindirizza alla lista delle squadre dopo la cancellazione
    }
}
