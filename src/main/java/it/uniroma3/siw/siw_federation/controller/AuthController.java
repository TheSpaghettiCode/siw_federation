/*package it.uniroma3.siw.siw_federation.controller;

import java.io.IOException;
import java.util.Base64;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import it.uniroma3.siw.siw_federation.model.Credentials;
import it.uniroma3.siw.siw_federation.model.Giocatore;
import it.uniroma3.siw.siw_federation.model.Presidente;
import it.uniroma3.siw.siw_federation.model.RuoloGiocatore;
import it.uniroma3.siw.siw_federation.model.Squadra;
import it.uniroma3.siw.siw_federation.service.CredentialsService;
import it.uniroma3.siw.siw_federation.service.GiocatoreService;
import it.uniroma3.siw.siw_federation.service.PresidenteService;
import jakarta.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private GiocatoreService giocatoreService;

    @Autowired
    private PresidenteService presidenteService;

    @GetMapping("/loginPage")
    public String getLoginPage() {
        return "loginPage.html";
    }

    @GetMapping(value = "/registrationPage")
    public String getRegistrationPage(Model model) {
		model.addAttribute("credentials", new Credentials());
        return "registrationPage.html";
    }

    @PostMapping("/registrationData")
    public String registerUser(@RequestParam(required = false, name = "CF") String CF, 
                                @RequestParam(required = false, name = "nome") String nome, 
                                @RequestParam(required = false, name = "cognome") String cognome, 
                                @RequestParam(required = false, name = "dataDiNascita") Date dataDiNascita,
                                @RequestParam(required = false, name = "luogoNascita") String luogoNascita,  
                                @RequestParam(required = false, name = "squadra") Squadra squadra,
                                @RequestParam(required = false, name = "ruolo") RuoloGiocatore ruolo,
                                @RequestParam(required = false, name = "image") MultipartFile file,
                                @Valid @ModelAttribute("credentials") Credentials credentials, BindingResult credentialsBindingResult,
                                @RequestParam("role") String role,
                                Model model) {

        //se name è presente nell'invio al controller ma senza valore equivale ad una stringa vuota, se non è proprio presente equivale a NULL
        
        // se user e credential hanno entrambi contenuti validi, memorizza User e the Credentials nel DB
        if(!credentialsBindingResult.hasErrors()) {
            if(role.equals("GIOCATORE")){
                Giocatore giocatore = new Giocatore(CF, nome, cognome, dataDiNascita, luogoNascita, ruolo, squadra);

                try {
                    byte[] byteFoto = file.getBytes();
                    giocatore.setImageBase64(Base64.getEncoder().encodeToString(byteFoto));
                    giocatoreService.saveGiocatore(giocatore);
                    credentials.setGiocatore(giocatore);
                    } catch (IOException e) {
                        model.addAttribute("message", "Chef upload failed!");
                        return "/registrationPage";
                    }
            }
            
            else if(role.equals("PRESIDENTE")){
                Presidente presidente = new Presidente(CF, nome, cognome, dataDiNascita, squadra, luogoNascita);
                presidenteService.savePresidente(presidente);
                credentials.setPresidente(presidente);
            }
            
            credentialsService.saveCredentials(credentials, role);
            return "redirect:/loginPage?registration=true";
        }
        return "registrationPage.html";
    }

}*/


package it.uniroma3.siw.siw_federation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.time.LocalDate;

import it.uniroma3.siw.siw_federation.model.Credentials;
import it.uniroma3.siw.siw_federation.model.Giocatore;
import it.uniroma3.siw.siw_federation.model.Presidente;
import it.uniroma3.siw.siw_federation.model.RuoloGiocatore;
import it.uniroma3.siw.siw_federation.model.Squadra;
import it.uniroma3.siw.siw_federation.service.CredentialsService;
import it.uniroma3.siw.siw_federation.service.GiocatoreService;
import it.uniroma3.siw.siw_federation.service.PresidenteService;
import jakarta.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private GiocatoreService giocatoreService;

    @Autowired
    private PresidenteService presidenteService;

    @GetMapping("/loginPage")
    public String getLoginPage() {
        return "loginPage.html";
    }

    @GetMapping("/registrationPage.html")
    public String getRegistrationPage(Model model) {
        model.addAttribute("credentials", new Credentials());
        return "/registrationPage.html"; // Assumendo che la tua pagina sia registrationPage.html
    }

    @PostMapping("/registrationData")
    public String registerUser(@RequestParam(required = false, name = "CF") String CF, 
                                @RequestParam(required = false, name = "nome") String nome, 
                                @RequestParam(required = false, name = "cognome") String cognome, 
                                @RequestParam(required = false, name = "dataDiNascita") LocalDate dataDiNascita,
                                @RequestParam(required = false, name = "luogoNascita") String luogoNascita,  
                                @RequestParam(required = false, name = "image") MultipartFile file,
                                @Valid @ModelAttribute("credentials") Credentials credentials, BindingResult credentialsBindingResult,
                                @RequestParam("role") String role,
                                Model model) {

        if (!credentialsBindingResult.hasErrors()) {

            if (role.equals("PRESIDENTE")) {
                Presidente presidente = new Presidente(CF, nome, cognome, dataDiNascita, luogoNascita);
                presidenteService.savePresidente(presidente);
                credentials.setPresidente(presidente);
            }
            
            else if (role.equals("GIOCATORE")) {
                Giocatore giocatore = new Giocatore(CF, nome, cognome, dataDiNascita, luogoNascita);

                try {
                    byte[] byteFoto = file.getBytes();
                    giocatore.setImageBase64(Base64.getEncoder().encodeToString(byteFoto));
                    giocatoreService.saveGiocatore(giocatore);
                    credentials.setGiocatore(giocatore);
                } catch (IOException e) {
                    model.addAttribute("message", "Upload della foto fallito!");
                    return "/registrationPage";
                }
            } 

            credentialsService.saveCredentials(credentials, role);
            return "redirect:/loginPage?registration=true";
        }
        return "registrationPage.html";
    }

    // Rotta protetta per presidente
    @GetMapping("/presidente/editor")
    public String getPresidenteEditorPage(Authentication authentication, Model model) {
        Credentials credentials = credentialsService.getCredentials(authentication.getName());

        if (credentials.getRole().equals(Credentials.PRESIDENTE_ROLE)) {
            model.addAttribute("presidente", credentials.getPresidente());
            return "presidenteEditorPage.html";
        }

        return "redirect:/accessDenied";
    }

    // Rotta protetta per giocatore
    @GetMapping("/editor")
    public String getEditorPage(Authentication authentication, Model model) {
        Credentials credentials = credentialsService.getCredentials(authentication.getName());

        if (credentials.getRole().equals(Credentials.GIOCATORE_ROLE)) {
            model.addAttribute("giocatore", credentials.getGiocatore());
            return "editorPage.html";
        }

        return "redirect:/accessDenied";
    }
}
