package it.softfood.entity;

import java.io.Serializable;

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
    @NamedQuery(name = "Pietanza.selezionaPietanzePerNome", query = "SELECT a FROM Articolo a WHERE a.tipo_articolo = Pietanza AND a.nome = :nome")
 })
public class Pietanza extends Articolo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
