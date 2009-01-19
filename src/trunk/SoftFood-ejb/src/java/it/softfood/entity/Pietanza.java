package it.softfood.entity;

import it.softfood.enumeration.TipoPietanza;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Entity
@DiscriminatorValue(value = "Pietanza")
@NamedQueries({
	@NamedQuery(name = "Pietanza.selezionaPietanzePerTipo", query = "SELECT p FROM Pietanza p WHERE tipo = :tipo"),
    @NamedQuery(name = "Pietanza.selezionaPietanze", query = "SELECT p FROM Pietanza p")
})
public class Pietanza extends Articolo implements Serializable {

	private static final long serialVersionUID = 1L;

    @Column(name = "tipo_pietanza", nullable = false)
	private TipoPietanza tipo;

    public TipoPietanza getTipo() {
        return tipo;
    }

    public void setTipo(TipoPietanza tipo) {
        this.tipo = tipo;
    }

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
