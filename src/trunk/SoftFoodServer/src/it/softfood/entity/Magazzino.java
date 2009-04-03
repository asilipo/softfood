package it.softfood.entity;

import java.util.HashSet;
import java.util.Set;

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
	private Set<IngredienteMagazzino> ingredienteMagazzinos = new HashSet<IngredienteMagazzino>(0);
	private Set<BevandaMagazzino> bevandaMagazzinos = new HashSet<BevandaMagazzino>(0);

	public Magazzino() {
	}

	public Magazzino(Long id) {
		this.id = id;
	}

	public Magazzino(Long id, Indirizzo indirizzo, String descrizione,
			Set<IngredienteMagazzino> ingredienteMagazzinos,
				Set<BevandaMagazzino> bevandaMagazzinos) {
		this.id = id;
		this.indirizzo = indirizzo;
		this.descrizione = descrizione;
		this.ingredienteMagazzinos = ingredienteMagazzinos;
		this.bevandaMagazzinos = bevandaMagazzinos;
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

	public Set<IngredienteMagazzino> getIngredienteMagazzinos() {
		return this.ingredienteMagazzinos;
	}

	public void setIngredienteMagazzinos(Set<IngredienteMagazzino> ingredienteMagazzinos) {
		this.ingredienteMagazzinos = ingredienteMagazzinos;
	}

	public Set<BevandaMagazzino> getBevandaMagazzinos() {
		return this.bevandaMagazzinos;
	}

	public void setBevandaMagazzinos(Set<BevandaMagazzino> bevandaMagazzinos) {
		this.bevandaMagazzinos = bevandaMagazzinos;
	}
        
	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

}
