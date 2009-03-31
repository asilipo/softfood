package it.softfood.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Articolo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Listino listino;
	private String tipoArticolo;
	private String descrizione;
	private String nome;
	private Float capacita;
	private Integer tipoPietanza;
	private List<IngredientePietanza> ingredientiPietanza = new ArrayList<IngredientePietanza>(0);
	private List<BevandaMagazzino> bevandeMagazzino = new ArrayList<BevandaMagazzino>(0);
	private List<LineaOrdinazione> lineeOrdinazione = new ArrayList<LineaOrdinazione>(0);

	public Articolo() {
	}

	public Articolo(Long id, String tipoArticolo, String nome) {
		this.id = id;
		this.tipoArticolo = tipoArticolo;
		this.nome = nome;
	}

	public Articolo(Long id, Listino listino, String tipoArticolo, String descrizione, 
			String nome, Float capacita, Integer tipoPietanza, List<IngredientePietanza> ingredientiPietanza,
				List<BevandaMagazzino> bevandeMagazzino, List<LineaOrdinazione> lineeOrdinazione) {
		this.id = id;
		this.listino = listino;
		this.tipoArticolo = tipoArticolo;
		this.descrizione = descrizione;
		this.nome = nome;
		this.capacita = capacita;
		this.tipoPietanza = tipoPietanza;
		this.bevandeMagazzino = bevandeMagazzino;
		this.lineeOrdinazione = lineeOrdinazione;
		this.ingredientiPietanza = ingredientiPietanza;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Listino getListino() {
		return this.listino;
	}

	public void setListino(Listino listino) {
		this.listino = listino;
	}

	public String getTipoArticolo() {
		return this.tipoArticolo;
	}

	public void setTipoArticolo(String tipoArticolo) {
		this.tipoArticolo = tipoArticolo;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Float getCapacita() {
		return this.capacita;
	}

	public void setCapacita(Float capacita) {
		this.capacita = capacita;
	}

	public Integer getTipoPietanza() {
		return this.tipoPietanza;
	}

	public void setTipoPietanza(Integer tipoPietanza) {
		this.tipoPietanza = tipoPietanza;
	}

	public List<IngredientePietanza> getIngredientiPietanza() {
		return this.ingredientiPietanza;
	}

	public void setIngredientiPietanze(List<IngredientePietanza> ingredientiPietanza) {
		this.ingredientiPietanza = ingredientiPietanza;
	}

	public List<BevandaMagazzino> getBevandeMagazzino() {
		return this.bevandeMagazzino;
	}

	public void setBevandaMagazzinos(List<BevandaMagazzino> bevandeMagazzino) {
		this.bevandeMagazzino = bevandeMagazzino;
	}

	public List<LineaOrdinazione> getLineeOrdinazione() {
		return this.lineeOrdinazione;
	}

	public void setLineeOrdinazione(List<LineaOrdinazione> lineeOrdinazione) {
		this.lineeOrdinazione = lineeOrdinazione;
	}
        
    @Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

}
