/*package it.uniroma3.siw.siw_federation.controller;

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

    @GetMapping("/nuovo")
    public String showCreateGiocatoreForm(Model model) {
        model.addAttribute("giocatore", new Giocatore());
        List<Squadra> squadre = squadraService.findAllSquadre();
        model.addAttribute("squadre", squadre);
        return "admin/nuovo-giocatore.html";
    }

    // Salva un nuovo giocatore e lo assegna a una squadra
    @PostMapping("/salva")
    public String saveGiocatore(@ModelAttribute Giocatore giocatore, @RequestParam Long squadraId) {
        giocatoreService.saveGiocatore(giocatore);
        return "redirect:/giocatore/giocatori";
    }

    // Mostra l'elenco di tutti i giocatori
    @GetMapping("/giocatori")
    public String getAllGiocatori(Model model) {
        List<Giocatore> giocatori = giocatoreService.findAllGiocatori();
        model.addAttribute("giocatori", giocatori);
        return "all/giocatori.html";
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
        return "redirect:/giocatore/giocatori";
    }

    // Assegna un giocatore ad una squadra
    @PostMapping("/assegna")
    public String assignGiocatoreToSquadra(@RequestParam Long giocatoreId, @RequestParam Long squadraId) {
        giocatoreService.addGiocatoreToSquadra(giocatoreId, squadraId);
        return "redirect:/giocatore/giocatori";
    }

    // Cancella un giocatore esistente
    @GetMapping("/cancella/{id}")
    public String deleteGiocatore(@PathVariable Long id) {
        giocatoreService.deleteGiocatore(id);
        return "redirect:/giocatore/giocatori";
    }

    // Rimuove un giocatore da una squadra
    @PostMapping("/rimuoviDaSquadra")
    public String removeGiocatoreFromSquadra(@RequestParam Long giocatoreId) {
        giocatoreService.removeGiocatoreFromSquadra(giocatoreId);
        return "redirect:/giocatore/giocatori";
    }
}*/

package it.uniroma3.siw.siw_federation.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;
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
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.siw_federation.model.Giocatore;
import it.uniroma3.siw.siw_federation.model.RuoloGiocatore;
import it.uniroma3.siw.siw_federation.model.Squadra;
import it.uniroma3.siw.siw_federation.service.CredentialsService;
import it.uniroma3.siw.siw_federation.service.GiocatoreService;
import it.uniroma3.siw.siw_federation.service.SquadraService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/giocatori")
public class GiocatoreController {

    @Autowired
    private GiocatoreService giocatoreService;

    @Autowired
    private SquadraService squadraService;

    @Autowired
    private CredentialsService credentialsService;

    // Mostra la lista di tutti i giocatori
    @GetMapping("/all")
    public String getAllGiocatori(Model model) {
        List<Giocatore> giocatori = giocatoreService.getAllGiocatori();
        model.addAttribute("giocatori", giocatori);
        return "giocatori/listaGiocatori.html"; // Indica il template Thymeleaf per la visualizzazione della lista
    }

    // Mostra i dettagli di un singolo giocatore
    @GetMapping("/dettagli/{id}")
    public String getGiocatore(@PathVariable("id") Long id, Model model) {
        Giocatore giocatore = giocatoreService.getGiocatoreById(id);
        model.addAttribute("giocatore", giocatore);
        return "giocatori/dettagliGiocatore.html"; // Indica il template Thymeleaf per la visualizzazione dei dettagli
    }

    /*// Mostra il form per creare un nuovo giocatore
    @GetMapping("/nuovo")
    public String showCreateGiocatoreForm(Model model) {
        model.addAttribute("giocatore", new Giocatore());
        model.addAttribute("ruoli", RuoloGiocatore.values()); // Per popolare il dropdown dei ruoli
        model.addAttribute("squadre", squadraService.getAllSquadre()); // Per popolare il dropdown delle squadre
        return "giocatori/nuovoGiocatore.html"; // Indica il template Thymeleaf per il form di creazione
    }*/
    /*// Gestisce la creazione di un nuovo giocatore
    @PostMapping("/nuovoGiocatore")
    public String createGiocatore(@Valid @ModelAttribute("giocatore") Giocatore giocatore, 
                                  BindingResult bindingResult,
                                  @RequestParam("image") MultipartFile file,
                                  @RequestParam("squadraId") Long squadraId,
                                  Model model) {
                                    
        if (bindingResult.hasErrors()) {
            model.addAttribute("ruoli", RuoloGiocatore.values());
            model.addAttribute("squadre", squadraService.getAllSquadre());
            return "giocatori/nuovoGiocatore.html"; // Ritorna al form se ci sono errori di validazione
        }

        try {
            if (!file.isEmpty()) {
                byte[] byteFoto = file.getBytes();
                giocatore.setImageBase64(Base64.getEncoder().encodeToString(byteFoto));
            }
        } catch (IOException e) {
            model.addAttribute("message", "Errore nel caricamento dell'immagine");
            return "giocatori/nuovoGiocatore.html";
        }

        Squadra squadra = squadraService.getSquadraById(squadraId);
        if (squadra == null) {
            model.addAttribute("message", "La squadra selezionata non esiste");
            model.addAttribute("ruoli", RuoloGiocatore.values());
            model.addAttribute("squadre", squadraService.getAllSquadre());
            return "giocatori/nuovoGiocatore.html";
        }

        giocatore.setSquadra(squadra);
        giocatoreService.saveGiocatore(giocatore);
        return "redirect:/giocatori"; // Reindirizza alla lista dei giocatori dopo la creazione
    }*/

    @GetMapping("/nuovo")
    public String showNuovoGiocatoreForm(Model model) {
    
        // Recuperiamo la lista di tutte le squadre e ruoli disponibili dal database
        List<Squadra> squadre = squadraService.getAllSquadre();
        List<String> ruoli = Arrays.asList("Attaccante", "Centrocampista", "Difensore", "Portiere");  // O recuperati dal DB
    
        // Aggiungiamo l'istanza di Giocatore e le liste al modello
        model.addAttribute("giocatore", new Giocatore());
        model.addAttribute("squadre", squadre);
        model.addAttribute("ruoli", ruoli);
    
        // Ritorniamo la vista "nuovoGiocatore.html" per visualizzare il form
        return "giocatori/nuovoGiocatore.html";
    }
    
    @PostMapping("/nuovoGiocatore")
public String registerGiocatore(@RequestParam(required = false, name = "CF") String codiceFiscale,
                                @RequestParam(required = false, name = "nome") String nome, 
                                @RequestParam(required = false, name = "cognome") String cognome,
                                @RequestParam(required = false, name = "dataNascita") LocalDate dataNascita,
                                @RequestParam(required = false, name = "luogoNascita") String luogoNascita,
                                @RequestParam(required = false, name = "ruolo") String ruolo,
                                @RequestParam(required = false, name = "squadra") Squadra squadra,
                                @RequestParam(required = false, name = "inizioTesseramento") LocalDate inizioTesseramento,
                                @RequestParam(required = false, name = "fineTesseramento") LocalDate fineTesseramento,
                                //@RequestParam(required = false, name = "image") MultipartFile file,
                                Model model) {


    // Creiamo una nuova istanza di Giocatore
    Giocatore giocatore = new Giocatore(codiceFiscale, nome, cognome, dataNascita, luogoNascita, ruolo, squadra, inizioTesseramento, fineTesseramento);

    // Se è presente un'immagine, la convertiamo in Base64 e la salviamo
    /*try {
        if (!file.isEmpty()) {
            byte[] byteFoto = file.getBytes();
            giocatore.setImageBase64(Base64.getEncoder().encodeToString(byteFoto));
        }
    } catch (IOException e) {
        model.addAttribute("message", "Upload della foto fallito!");
        return "giocatori/nuovoGiocatore.html";
    }*/

    // Salviamo il giocatore nel database
    giocatoreService.saveGiocatore(giocatore);

    // Redirigiamo alla lista dei giocatori o a una pagina di successo
    return "redirect:/giocatori/all";
}




    // Mostra il form per modificare un giocatore esistente
    @GetMapping("/modifica/{id}")
    public String showUpdateGiocatoreForm(@PathVariable("id") Long id, Model model) {
        Giocatore giocatore = giocatoreService.getGiocatoreById(id);
        List<String> ruoli = Arrays.asList("Attaccante", "Centrocampista", "Difensore", "Portiere");
        List<Squadra> squadre = squadraService.getAllSquadre();
            model.addAttribute("giocatore", giocatore);
            model.addAttribute("ruoli", ruoli);
            model.addAttribute("squadre", squadre);
            return "giocatori/modificaGiocatore.html"; // Indica il template Thymeleaf per il form di modifica
        //return "redirect:/giocatori"; // Se l'ID non esiste, torna alla lista dei giocatori
    }

    // Gestisce l'aggiornamento di un giocatore esistente
    @PostMapping("/modifica/{id}")
    public String updateGiocatore(@PathVariable("id") Long id,
                                  @Valid @ModelAttribute("giocatore") Giocatore giocatore, 
                                  @RequestParam(required = false, name = "CF") String codiceFiscale,
                                  @RequestParam(required = false, name = "nome") String nome, 
                                  @RequestParam(required = false, name = "cognome") String cognome,
                                  @RequestParam(required = false, name = "dataNascita") LocalDate dataNascita,
                                  @RequestParam(required = false, name = "luogoNascita") String luogoNascita,
                                  @RequestParam(required = false, name = "ruolo") String ruolo,
                                  @RequestParam(required = false, name = "squadra") Squadra squadra,
                                  @RequestParam(required = false, name = "inizioTesseramento") LocalDate inizioTesseramento,
                                  @RequestParam(required = false, name = "fineTesseramento") LocalDate fineTesseramento,
                                  //@RequestParam(required = false, name = "image") MultipartFile file,
                                  Model model) {
                                    
        /*if (bindingResult.hasErrors()) {
            model.addAttribute("ruoli", RuoloGiocatore.values());
            model.addAttribute("squadre", squadraService.getAllSquadre());
            return "giocatori/modificaGiocatore.html"; // Ritorna al form se ci sono errori di validazione
        }

        try {
            if (!file.isEmpty()) {
                byte[] byteFoto = file.getBytes();
                giocatore.setImageBase64(Base64.getEncoder().encodeToString(byteFoto));
            }
        } catch (IOException e) {
            model.addAttribute("message", "Errore nel caricamento dell'immagine");
            return "giocatori/modificaGiocatore.html";
        }

        
        if (squadra == null) {
            model.addAttribute("message", "La squadra selezionata non esiste");
            model.addAttribute("ruoli", RuoloGiocatore.values());
            model.addAttribute("squadre", squadraService.getAllSquadre());
            return "giocatori/modificaGiocatore.html";
        }*/
        
        giocatore.setCF(ruolo);
        giocatore.setNome(nome);
        giocatore.setCognome(cognome);
        giocatore.setDataNascita(dataNascita);
        giocatore.setLuogoNascita(luogoNascita);
        giocatore.setRuolo(ruolo);
        giocatore.setSquadra(squadra);
        giocatore.setInizioTesseramento(inizioTesseramento);
        giocatore.setFineTesseramento(fineTesseramento);
        
        giocatoreService.saveGiocatore(giocatore);
        return "redirect:/giocatori/all"; // Reindirizza alla lista dei giocatori dopo l'aggiornamento
    }

    // Gestisce la cancellazione di un giocatore
    @GetMapping("/elimina/{id}")
    public String deleteGiocatore(@PathVariable("id") Long id) {
        credentialsService.deleteByGiocatoreId(id);
        giocatoreService.deleteGiocatoreById(id);
        return "redirect:/giocatori/all"; // Reindirizza alla lista dei giocatori dopo la cancellazione
    }
}

