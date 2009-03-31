package it.softfood.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Magazzino implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Indirizzo indirizzo;
	private String descrizione;
	private List<IngredienteMagazzino> ingredientiMagazzino = new ArrayList<IngredienteMagazzino>(0);
	private List<BevandaMagazzino> bevandeMagazzino = new ArrayList<BevandaMagazzino>(0);

	public Magazzino() {
	}

	public Magazzino(Long id) {
		this.id = id;
	}

	public Magazzino(Long id, Indirizzo indirizzo, String descrizione, 
			List<IngredienteMagazzino> ingredientiMagazzino, List<BevandaMagazzino> bevandeMagazzino) {
		this.id = id;
		this.indirizzo = indirizzo;
		this.descrizione = descrizione;
		this.ingredientiMagazzino = ingredientiMagazzino;
		this.bevandeMagazzino = bevandeMagazzino;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
        public Indirizzo getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(Indirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<IngredienteMagazzino> getIngredientiMagazzino() {
		return this.ingredientiMagazzino;
	}

	public void setIngredienteMagazzinos(List<IngredienteMagazzino> ingredientiMagazzino) {
		this.ingredientiMagazzino = ingredientiMagazzino;
	}

	public List<BevandaMagazzino> getBevandeMagazzino() {
		return this.bevandeMagazzino;
	}

	public void setBevandeMagazzino(List<BevandaMagazzino> bevandeMagazzino) {
		this.bevandeMagazzino = bevandeMagazzino;
	}
        
	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

}
