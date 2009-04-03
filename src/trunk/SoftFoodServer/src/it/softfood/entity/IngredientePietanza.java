package it.softfood.entity;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class IngredientePietanza implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private IngredientePietanzaPK id;
	private Articolo articolo;
	private Ingrediente ingrediente;
	private int quantita;

	public IngredientePietanza() {
	}

	public IngredientePietanza(IngredientePietanzaPK id, Articolo articolo,
			Ingrediente ingrediente, int quantita) {
		this.id = id;
		this.articolo = articolo;
		this.ingrediente = ingrediente;
		this.quantita = quantita;
	}

	public IngredientePietanzaPK getId() {
		return this.id;
	}

	public void setId(IngredientePietanzaPK id) {
		this.id = id;
	}

	public Articolo getArticolo() {
		return this.articolo;
	}

	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}

	public Ingrediente getIngrediente() {
		return this.ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public int getQuantita() {
		return this.quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
        
	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

}
