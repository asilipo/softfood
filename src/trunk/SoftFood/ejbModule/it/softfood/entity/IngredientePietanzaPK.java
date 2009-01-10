package it.softfood.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Embeddable
public class IngredientePietanzaPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@OneToOne()
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "pietanza")
    private Pietanza pietanza;
    @OneToOne()
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "ingrediente")
    private Ingrediente ingrediente;

    public IngredientePietanzaPK() {}

	public Pietanza getPietanza() {
		return pietanza;
	}

	public void setPietanza(Pietanza pietanza) {
		this.pietanza = pietanza;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

}
