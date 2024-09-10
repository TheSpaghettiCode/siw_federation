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

import java.io.IOException;
import java.time.LocalDate;
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

    /**
     * GET Mapping per visualizzare il form di creazione di una nuova squadra.
     * Questo metodo prepara il modello per il form, includendo una nuova istanza di Squadra
     * e la lista dei Presidenti disponibili.
     *
     * @param model Il modello da passare alla vista.
     * @return Il nome della vista che visualizza il form di creazione di una nuova squadra.
     */
    @GetMapping("/nuova")
    public String showNuovaSquadraForm(Model model) {

        // Recuperiamo la lista di tutti i Presidenti dal database
        List<Presidente> presidenti = presidenteService.getAllPresidenti();

        // Aggiungiamo l'istanza di Squadra e la lista di Presidenti al modello
        model.addAttribute("presidenti", presidenti);

        // Ritorniamo la vista "nuovaSquadra" per visualizzare il form
        return "squadre/nuovaSquadra.html";
    }

    /**
     * POST Mapping per gestire l'invio del form e salvare la nuova squadra nel database.
     * Se ci sono errori di validazione, viene mostrato di nuovo il form con gli errori.
     * Se il form è valido, la squadra viene salvata e si viene reindirizzati alla lista delle squadre.
     *
     * @param squadra L'oggetto squadra riempito con i dati del form.
     * @param bindingResult L'oggetto che contiene gli errori di validazione.
     * @param model Il modello da passare alla vista.
     * @return Un redirect alla lista delle squadre o alla vista del form in caso di errore.
     */
    /*@PostMapping("/nuova")
    public String saveNuovaSquadra(
            @Valid @ModelAttribute("squadra") Squadra squadra,
            BindingResult bindingResult,
            Model model) {

        // Se ci sono errori di validazione, torniamo alla pagina del form
        if (bindingResult.hasErrors()) {
            // Ricarichiamo la lista di presidenti da passare di nuovo alla vista
            List<Presidente> presidenti = presidenteService.getAllPresidenti();
            model.addAttribute("presidenti", presidenti);
            return "nuovaSquadra";
        }

        // Salviamo la nuova squadra utilizzando il servizio SquadraService
        squadraService.saveSquadra(squadra);

        // Redirigiamo alla lista delle squadre o alla pagina desiderata
        return "redirect:/squadre";  // Puoi modificare l'URL di redirect in base alle tue esigenze
    }*/

    @PostMapping("/nuovaSquadra")
    public String registerUser(@RequestParam(required = false, name = "nome") String nome, 
                                @RequestParam(required = false, name = "dataFondazione") LocalDate dataFondazione,
                                @RequestParam(required = false, name = "indirizzoSede") String indirizzoSede,
                                @RequestParam(required = false, name = "descrizione") String descrizione,
                                @RequestParam(required = false, name = "presidente") Presidente presidente,     
                                @RequestParam(required = false, name = "image") MultipartFile file,
                                Model model) {

                Squadra squadra = new Squadra(nome, dataFondazione, indirizzoSede, descrizione, presidente);
                squadraService.saveSquadra(squadra);
                /*try {
                    byte[] byteFoto = file.getBytes();
                    squadra.setImageBase64(Base64.getEncoder().encodeToString(byteFoto));
                    
                } catch (IOException e) {
                    model.addAttribute("message", "Upload della foto fallito!");
                    return "squadre/nuovaSquadra.html";
                }*/ 

        return "squadre/nuovaSquadra.html";
    }

    

    // Mostra il form per modificare una squadra esistente
    @GetMapping("/modifica/{id}")
    public String showUpdateSquadraForm(@PathVariable("id") Long id, Model model) {
        Squadra squadra = squadraService.getSquadraById(id);

            model.addAttribute("squadra", squadra);
            model.addAttribute("presidenti", presidenteService.getAllPresidenti());
            return "squadre/modificaSquadra.html"; // Indica il template Thymeleaf per il form di modifica

        //return "redirect:/squadre"; // Se l'ID non esiste, torna alla lista delle squadre
    }

    // Gestisce l'aggiornamento di una squadra esistente
    @PostMapping("/modifica/{id}")
    public String updateSquadra(@PathVariable("id") Long id,
                                @Valid @ModelAttribute("squadra") Squadra squadra, 
                                @RequestParam(required = false, name = "nome") String nome, 
                                @RequestParam(required = false, name = "dataFondazione") LocalDate dataFondazione,
                                @RequestParam(required = false, name = "indirizzoSede") String indirizzoSede,
                                @RequestParam(required = false, name = "descrizione") String descrizione,
                                @RequestParam(required = false, name = "presidente") Presidente presidente,     
                                @RequestParam(required = false, name = "image") MultipartFile file,
                                Model model)  {

        /*if (bindingResult.hasErrors()) {
            model.addAttribute("presidenti", presidenteService.getAllPresidenti());
            return "squadre/modificaSquadra.html"; // Ritorna al form se ci sono errori di validazione
        }

        Presidente presidente = presidenteService.getPresidenteById(presidenteId);
        if (presidente == null) {
            model.addAttribute("message", "Il presidente selezionato non esiste");
            model.addAttribute("presidenti", presidenteService.getAllPresidenti());
            return "squadre/modificaSquadra.html"; // Ritorna al form se il presidente non esiste
        }
        squadra.setPresidente(presidente);*/
        
        squadra.setNome(nome);
        squadra.setDataFondazione(dataFondazione);
        squadra.setIndirizzoSede(indirizzoSede);
        squadra.setDescrizione(descrizione);
        squadra.setPresidente(presidente);

        squadraService.saveSquadra(squadra);
        return "redirect:/squadre/all"; // Reindirizza alla lista delle squadre dopo l'aggiornamento
    }

    // Gestisce la cancellazione di una squadra
    @GetMapping("/elimina/{id}")
    public String deleteSquadra(@PathVariable("id") Long id) {
        Squadra squadra = squadraService.getSquadraById(id);
        squadra.getPresidente().setSquadra(null);
        squadra.setPresidente(null);
        squadraService.deleteSquadraById(id);
        return "redirect:/squadre/all"; // Reindirizza alla lista delle squadre dopo la cancellazione
    }
}
