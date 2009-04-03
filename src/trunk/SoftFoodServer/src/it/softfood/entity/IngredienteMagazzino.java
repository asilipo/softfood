package it.softfood.entity;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class IngredienteMagazzino implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Magazzino magazzino;
	private Ingrediente ingrediente;
	private int quantita;

	public IngredienteMagazzino() {
	}

	public IngredienteMagazzino(Long id, Magazzino magazzino,
			Ingrediente ingrediente, int quantita) {
		this.id = id;
		this.magazzino = magazzino;
		this.ingrediente = ingrediente;
		this.quantita = quantita;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Magazzino getMagazzino() {
		return this.magazzino;
	}

	public void setMagazzino(Magazzino magazzino) {
		this.magazzino = magazzino;
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
