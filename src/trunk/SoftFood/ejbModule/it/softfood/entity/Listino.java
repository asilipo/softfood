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
@Table(name = "listino")
public class Listino implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@OneToOne()
	@LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "articolo", nullable = false)
	private Articolo articolo;
	@Id
	@OneToOne()
	@LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "ristorante", nullable = false)
	private Menu ristorante;
	@Column(name = "prezzo", nullable = false)
	private Double prezzo;
	
	public Articolo getArticolo() {
		return articolo;
	}
	
	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}
	
	public Menu getRistorante() {
		return ristorante;
	}
	
	public void setRistorante(Menu ristorante) {
		this.ristorante = ristorante;
	}
	
	public Double getPrezzo() {
		return prezzo;
	}
	
	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
}
