package it.softfood.entity;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class IngredientePietanzaPK implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long ingrediente;
	private Long pietanza;

	public IngredientePietanzaPK() {
	}

	public IngredientePietanzaPK(Long ingrediente, Long pietanza) {
		this.ingrediente = ingrediente;
		this.pietanza = pietanza;
	}

	public Long getIngrediente() {
		return this.ingrediente;
	}

	public void setIngrediente(Long ingrediente) {
		this.ingrediente = ingrediente;
	}

	public Long getPietanza() {
		return this.pietanza;
	}

	public void setPietanza(Long pietanza) {
		this.pietanza = pietanza;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof IngredientePietanzaPK))
			return false;
		IngredientePietanzaPK castOther = (IngredientePietanzaPK) other;

		return (this.getIngrediente() == castOther.getIngrediente())
				&& (this.getPietanza() == castOther.getPietanza());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getIngrediente().intValue();
		result = 37 * result + (int) this.getPietanza().intValue();
		return result;
	}   

}