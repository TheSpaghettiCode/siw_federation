package it.uniroma3.siw.siw_federation.controller;

import it.uniroma3.siw.siw_federation.model.Giocatore;
import it.uniroma3.siw.siw_federation.model.Squadra;
import it.uniroma3.siw.siw_federation.service.GiocatoreService;
import it.uniroma3.siw.siw_federation.service.SquadraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/giocatore")
public class GiocatoreController {

    @Autowired
    private GiocatoreService giocatoreService;

    @Autowired
    private SquadraService squadraService;

    // Mostra il form per creare un nuovo giocatore
    @GetMapping("/nuovo")
    public String showCreateGiocatoreForm(Model model) {
        model.addAttribute("giocatore", new Giocatore());
        List<Squadra> squadre = squadraService.findAllSquadre();
        model.addAttribute("squadre", squadre);
        return "nuovo-giocatore";
    }

    // Salva un nuovo giocatore e lo assegna a una squadra
    @PostMapping("/salva")
    public String saveGiocatore(@ModelAttribute Giocatore giocatore, @RequestParam Long squadraId) {
        giocatoreService.saveGiocatore(giocatore, squadraId);
        return "redirect:/giocatore/tutti";
    }

    // Mostra l'elenco di tutti i giocatori
    @GetMapping("/tutti")
    public String getAllGiocatori(Model model) {
        List<Giocatore> giocatori = giocatoreService.findAllGiocatori();
        model.addAttribute("giocatori", giocatori);
        return "elenco-giocatori";
    }

    // Mostra il form per modificare un giocatore esistente
    @GetMapping("/modifica/{id}")
    public String showUpdateGiocatoreForm(@PathVariable Long id, Model model) {
        Giocatore giocatore = giocatoreService.findGiocatoreById(id)
            .orElseThrow(() -> new RuntimeException("Giocatore non trovato"));
        model.addAttribute("giocatore", giocatore);
        List<Squadra> squadre = squadraService.findAllSquadre();
        model.addAttribute("squadre", squadre);
        return "nuovo-giocatore";
    }

    // Aggiorna un giocatore esistente
    @PostMapping("/aggiorna/{id}")
    public String updateGiocatore(@PathVariable Long id, @ModelAttribute Giocatore giocatoreDetails) {
        giocatoreService.updateGiocatore(id, giocatoreDetails);
        return "redirect:/giocatore/tutti";
    }

    // Assegna un giocatore ad una squadra
    @PostMapping("/assegna")
    public String assignGiocatoreToSquadra(@RequestParam Long giocatoreId, @RequestParam Long squadraId) {
        giocatoreService.addGiocatoreToSquadra(giocatoreId, squadraId);
        return "redirect:/giocatore/tutti";
    }

    // Cancella un giocatore esistente
    @GetMapping("/cancella/{id}")
    public String deleteGiocatore(@PathVariable Long id) {
        giocatoreService.deleteGiocatore(id);
        return "redirect:/giocatore/tutti";
    }

    // Rimuove un giocatore da una squadra
    @PostMapping("/rimuoviDaSquadra")
    public String removeGiocatoreFromSquadra(@RequestParam Long giocatoreId) {
        giocatoreService.removeGiocatoreFromSquadra(giocatoreId);
        return "redirect:/giocatore/tutti";
    }
}

