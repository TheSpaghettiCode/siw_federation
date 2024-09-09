
package it.uniroma3.siw.siw_federation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class HomeController {

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

    @PostMapping("/login")
    public String handleLogin() {
        // Logica di login
        return "redirect:/";
    }
}

