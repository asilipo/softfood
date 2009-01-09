package it.softfood.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Entity
@Table(name = "magazzino_bevanda")
public class MagazzinoBevanda implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @OneToOne()
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "magazzino")
    private Magazzino magazzino;
	@Id
    @OneToOne()
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "bevanda")
    private Bevanda bevanda;
	@Column(name = "quantita", nullable = false)
	private Integer quantita;
	
	public Magazzino getMagazzino() {
		return magazzino;
	}
	
	public void setMagazzino(Magazzino magazzino) {
		this.magazzino = magazzino;
	}
	
	public Bevanda getBevanda() {
		return bevanda;
	}
	
	public void setBevanda(Bevanda bevanda) {
		this.bevanda = bevanda;
	}
	
	public Integer getQuantita() {
		return quantita;
	}
	
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}	
	
}
