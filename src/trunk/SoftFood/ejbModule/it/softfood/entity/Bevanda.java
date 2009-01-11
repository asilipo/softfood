package it.softfood.entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Entity
@DiscriminatorValue(value = "Bevanda")
public class Bevanda extends Articolo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}

