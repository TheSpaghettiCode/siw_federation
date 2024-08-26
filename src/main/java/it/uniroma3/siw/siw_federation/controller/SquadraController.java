package it.uniroma3.siw.siw_federation.controller;


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
} 

