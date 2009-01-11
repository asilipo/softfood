package it.softfood.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
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
@SequenceGenerator(name = "sequenza_listino", sequenceName = "seq_id_listino")
@NamedQueries({
	@NamedQuery(name = "Listino.selezionaArticoli", query = "SELECT l FROM Listino l")
})
public class Listino implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenza_listino")
	private Long id;
	@Column(name = "descrizione", nullable = true)
	private String descrizione;
	@Column(name = "prezzo", nullable = false)
	private Double prezzo;
	@OneToMany()
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "listino", nullable = true)
    private Collection<Articolo> articoli;
	@OneToOne
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "menu", nullable = false)
	private Menu menu;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Double getPrezzo() {
		return prezzo;
	}
	
	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Collection<Articolo> getArticoli() {
		return articoli;
	}

	public void setArticoli(Collection<Articolo> articoli) {
		this.articoli = articoli;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
}
