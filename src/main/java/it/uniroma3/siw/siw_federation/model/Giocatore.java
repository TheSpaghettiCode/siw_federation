package it.uniroma3.siw.siw_federation.model;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Giocatore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String CF;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataNascita;

    @Column(nullable = false)
    private String luogoNascita;
    
    @Enumerated(EnumType.STRING)
    private RuoloGiocatore ruolo;

    @Column(length = 10000000)
    private String imageBase64;

    @ManyToOne
    @JoinColumn(name = "squadra_id", nullable = false)
    private Squadra squadra;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tessera_id", referencedColumnName = "id")
    private Tessera tessera;
    

   public Giocatore(String nome, String cognome, Date dataNascita, String luogoNascita, RuoloGiocatore ruolo, Squadra squadra) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.luogoNascita = luogoNascita;
        this.ruolo = ruolo;
        this.squadra = squadra;
    }


    public Giocatore(){}


    @Lob
    private String foto;

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

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getLuogoNascita() {
        return luogoNascita;
    }

    public void setLuogoNascita(String luogoNascita) {
        this.luogoNascita = luogoNascita;
    }

    public RuoloGiocatore getRuolo() {
        return ruolo;
    }

    public void setRuolo(RuoloGiocatore ruolo) {
        this.ruolo = ruolo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    
}
