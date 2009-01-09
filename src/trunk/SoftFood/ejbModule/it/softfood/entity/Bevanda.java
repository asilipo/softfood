package it.softfood.entity;

import java.io.Serializable;

import javax.persistence.Column;
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
	
	@Column(name = "nome", nullable = false)
	private String nome;
	@Column(name = "descrizione", nullable = true)
	private String descrizione;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}

