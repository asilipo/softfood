package it.softfood.entity;

import it.softfood.enumeration.TipoPietanza;

import java.io.Serializable;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Pietanza extends Articolo implements Serializable {

	private static final long serialVersionUID = 1L;

	private TipoPietanza tipo;

    public TipoPietanza getTipo() {
        return tipo;
    }

    public void setTipo(TipoPietanza tipo) {
        this.tipo = tipo;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pietanza other = (Pietanza) obj;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

}
