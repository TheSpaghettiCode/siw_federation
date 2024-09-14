package it.uniroma3.siw.siw_federation.model;


import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;


@Entity
public class Giocatore {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String CF;
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private String luogoNascita;
    private String ruolo;
    private LocalDate inizioTesseramento;
    private LocalDate fineTesseramento;


    @Column(length = 10000000)
    private String imageBase64;

    @ManyToOne
    @JoinColumn(name = "squadra_id", referencedColumnName = "id")
    private Squadra squadra;


    public Giocatore(String CF,String nome, String cognome, LocalDate dataNascita, String luogoNascita) {
        this.CF = CF;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.luogoNascita = luogoNascita;
    }

    public Giocatore() {
    }

    public Giocatore(String cF, String nome, String cognome, LocalDate dataNascita, String luogoNascita,
                     String ruolo, Squadra squadra, LocalDate inizioTesseramento, LocalDate fineTesseramento ) {
        CF = cF;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.luogoNascita = luogoNascita;
        this.ruolo = ruolo;
        this.squadra = squadra;
        this.inizioTesseramento = inizioTesseramento;
        this.fineTesseramento = fineTesseramento;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCF() {
        return CF;
    }

    public void setCF(String cF) {
        CF = cF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getLuogoNascita() {
        return luogoNascita;
    }

    public void setLuogoNascita(String luogoNascita) {
        this.luogoNascita = luogoNascita;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public LocalDate getFineTesseramento() {
        return fineTesseramento;
    }

    public void setFineTesseramento(LocalDate fineTesseramento) {
        this.fineTesseramento = fineTesseramento;
    }

    public LocalDate getInizioTesseramento() {
        return inizioTesseramento;
    }

    public void setInizioTesseramento(LocalDate inizioTesseramento) {
        this.inizioTesseramento = inizioTesseramento;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((CF == null) ? 0 : CF.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Giocatore other = (Giocatore) obj;
        if (CF == null) {
            if (other.CF != null)
                return false;
        } else if (!CF.equals(other.CF))
            return false;
        return true;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public Squadra getSquadra() {
        return squadra;
    }

    public void setSquadra(Squadra squadra) {
        this.squadra = squadra;
    }



    
}
