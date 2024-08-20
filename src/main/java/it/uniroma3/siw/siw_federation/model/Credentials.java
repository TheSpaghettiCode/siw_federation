package it.uniroma3.siw.siw_federation.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Credentials {

    public static final String PRESIDENTE_ROLE = "PRESIDENTE";
    public static final String GIOCATORE_ROLE = "GIOCATORE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String username;
	private String password;
	private String role;

	@OneToOne(cascade = CascadeType.ALL)
	private Giocatore giocatore;

	@OneToOne(cascade = CascadeType.ALL)
	private Presidente presidente;


    public String getUsername() {
		return username;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

	public static String getPresidenteRole() {
		return PRESIDENTE_ROLE;
	}

	public static String getGiuocatoreRole() {
		return GIOCATORE_ROLE;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}

	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}

	public Presidente getPresidente() {
		return presidente;
	}

	public void setPresidente(Presidente presidente) {
		this.presidente = presidente;
	}
}

