package it.softfood.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Entity
@DiscriminatorValue(value = "Bevanda")
@NamedQueries({
        @NamedQuery(name = "Bevanda.selezionaBevande", query = "SELECT b FROM Bevanda b")
})
public class Bevanda extends Articolo implements Serializable {

	private static final long serialVersionUID = 1L;

    @Column(name = "capacita", nullable = true)
	private Float capacita;

    public Float getCapacita() {
        return capacita;
    }

    public void setCapacita(Float capacita) {
        this.capacita = capacita;
    }
   
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}

