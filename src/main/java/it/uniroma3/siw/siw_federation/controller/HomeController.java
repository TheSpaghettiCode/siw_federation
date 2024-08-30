/*package it.uniroma3.siw.siw_federation.controller;

import java.util.List;
import it.uniroma3.siw.siw_federation.service.SquadraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.siw_federation.model.Credentials;
import it.uniroma3.siw.siw_federation.model.Squadra;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHomePage() {
        return "home.html";
    }


    @GetMapping("/squadre/{id}")
    public String getDettagliSquadraPage(@PathVariable Long id, Model model) {
        // Popolare model con i dettagli della squadra
        return "squadre/dettagliSquadra.html";
    }

    @GetMapping("/giocatori/{id}")
    public String getDettagliGiocatorePage(@PathVariable Long id, Model model) {
        // Popolare model con i dettagli del giocatore
        return "giocatori/dettagliGiocatore.html";
    }

    @GetMapping("/registrationPage")
    public String getRegistrationPage() {
        return "registrationPage.html";
    }

    @PostMapping("/registrationData")
    public String handleRegistration(@ModelAttribute("user") Credentials username) {
        // Logica di registrazione
        return "redirect:/loginPage";
    }

    @GetMapping("/loginPage")
    public String getLoginPage() {
        return "loginPage.html";
    }

    @PostMapping("/login")
    public String handleLogin() {
        // Logica di login
        return "redirect:/";
    }
}*/

package it.uniroma3.siw.siw_federation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.siw_federation.model.Credentials;
import it.uniroma3.siw.siw_federation.service.PresidenteService;

@Controller
public class HomeController {


    @Autowired
    private PresidenteService presidenteService;

    /**
     * Mappa la root ("/") per la visualizzazione della home page.
     */
    @GetMapping("/")
    public String home(Model model) {
        return "home";  // Ritorna la vista "home.html"
    }

        /**
     * Mappa la root ("/") per la visualizzazione della about page.
     */
    @GetMapping("/about")
    public String about(Model model) {
        return "about.html";  // Ritorna la vista "home.html"
    }

    /**
     * Mappa la navigazione alla pagina che visualizza la lista di tutti i presidenti.
     */
    @GetMapping("/presidenti")
    public String showPresidenti(Model model) {
        model.addAttribute("presidenti", presidenteService.getAllPresidenti());
        return "presidenti";  // Ritorna la vista "presidenti.html"
    }

    @PostMapping("/login")
    public String handleLogin() {
        // Logica di login
        return "redirect:/";
    }
}

