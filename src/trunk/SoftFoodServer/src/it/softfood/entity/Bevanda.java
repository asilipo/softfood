package it.softfood.entity;

import java.io.Serializable;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Bevanda extends Articolo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Float capacita;

    public Float getCapacita() {
        return capacita;
    }

    public void setCapacita(Float capacita) {
        this.capacita = capacita;
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

