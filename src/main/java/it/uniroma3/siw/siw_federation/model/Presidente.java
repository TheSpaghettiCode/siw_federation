package it.uniroma3.siw.siw_federation.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToOne;
import java.time.LocalDate;



@Entity
public class Presidente {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String CF;
    private String nome;
    private String cognome;
    private LocalDate dataDiNascita;
    private String luogoNascita;

    @OneToOne(mappedBy = "presidente")
    private Squadra squadra;

    public Presidente(String CF,String nome, String cognome, LocalDate dataDiNascita, String luogoNascita) {
        this.CF = CF;
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.luogoNascita = luogoNascita;
    }

    public Presidente(){}

    // Getters and Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
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
        Presidente other = (Presidente) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (cognome == null) {
            if (other.cognome != null)
                return false;
        } else if (!cognome.equals(other.cognome))
            return false;
        return true;
    }

    public String getLuogoNascita() {
        return luogoNascita;
    }

    public void setLuogoNascita(String luogoNascita) {
        this.luogoNascita = luogoNascita;
    }

    public String getCF() {
        return CF;
    }

    public void setCF(String cF) {
        CF = cF;
    }

    public Squadra getSquadra() {
        return squadra;
    }

    public void setSquadra(Squadra squadra) {
        this.squadra = squadra;
    }

    
}
