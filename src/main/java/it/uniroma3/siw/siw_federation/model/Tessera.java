package it.uniroma3.siw.siw_federation.model;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Tessera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date inizioTesseramento;
    private Date fineTesseramento;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "giocatore_id", referencedColumnName = "id")
    private Giocatore giocatore;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getInizioTesseramento() {
        return inizioTesseramento;
    }
    public void setInizioTesseramento(Date inizioTesseramento) {
        this.inizioTesseramento = inizioTesseramento;
    }
    public Date getFineTesseramento() {
        return fineTesseramento;
    }
    public void setFineTesseramento(Date fineTesseramento) {
        this.fineTesseramento = fineTesseramento;
    }
    /*public Giocatore getTesseraGiocatore() {
        return tesseraGiocatore;
    }
    public void setTesseraGiocatore(Giocatore tesseraGiocatore) {
        this.tesseraGiocatore = tesseraGiocatore;
    }*/
    
}
