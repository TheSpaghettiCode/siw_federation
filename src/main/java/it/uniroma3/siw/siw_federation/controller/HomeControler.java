package it.uniroma3.siw.siw_federation.controller;

import java.util.List;
import it.uniroma3.siw.siw_federation.service.SquadraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.siw_federation.model.Squadra;

public class HomeControler {

    @Autowired
    private SquadraService squadraService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    // Consultazione dell'elenco delle squadre
    @GetMapping("/squadra/tutte")
    public String getAllSquadre(Model model) {
        List<Squadra> squadre = squadraService.findAllSquadre();
        model.addAttribute("squadre", squadre);
        return "all/elenco-squadre.html";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
