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
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
