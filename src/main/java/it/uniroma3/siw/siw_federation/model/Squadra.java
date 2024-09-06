package it.uniroma3.siw.siw_federation.model;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
public class Squadra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dataFondazione;
    private String indirizzoSede;

    @Column(length = 10000000)
    private String imageBase64;

    private String descrizione;

    @OneToMany(mappedBy = "squadra", cascade = CascadeType.ALL)
    private List<Giocatore> giocatori = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "presidente_id", referencedColumnName = "id")
    private Presidente presidente;

    
    
    public Squadra(String nome, LocalDate dataFondazione, String indirizzoSede,String descrizione, Presidente presidente) {
        this.nome = nome;
        this.dataFondazione = dataFondazione;
        this.indirizzoSede = indirizzoSede;
        this.descrizione = descrizione;
        this.presidente = presidente;
        
    }
     public Squadra(){}

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
    public LocalDate getDataFondazione() {
        return dataFondazione;
    }
    public void setDataFondazione(LocalDate dataFondazione) {
        this.dataFondazione = dataFondazione;
    }
    public String getIndirizzoSede() {
        return indirizzoSede;
    }
    public void setIndirizzoSede(String indirizzoSede) {
        this.indirizzoSede = indirizzoSede;
    }
    public List<Giocatore> getGiocatori() {
        return giocatori;
    }
    public void setGiocatori(List<Giocatore> giocatori) {
        this.giocatori = giocatori;
    }
    public Presidente getPresidente() {
        return presidente;
    }
    public void setPresidente(Presidente presidente) {
        this.presidente = presidente;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((dataFondazione == null) ? 0 : dataFondazione.hashCode());
        result = prime * result + ((indirizzoSede == null) ? 0 : indirizzoSede.hashCode());
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
        Squadra other = (Squadra) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (dataFondazione == null) {
            if (other.dataFondazione != null)
                return false;
        } else if (!dataFondazione.equals(other.dataFondazione))
            return false;
        if (indirizzoSede == null) {
            if (other.indirizzoSede != null)
                return false;
        } else if (!indirizzoSede.equals(other.indirizzoSede))
            return false;
        return true;
    }


}
