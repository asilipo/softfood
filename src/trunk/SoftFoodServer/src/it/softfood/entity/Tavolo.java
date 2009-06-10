package it.softfood.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Tavolo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Ristorante ristorante;
	private boolean attivo;
	private Integer numeroPosti;
	private boolean occupato;
	private String riferimento;
	private Set<Ordinazione> ordinaziones = new HashSet<Ordinazione>(0);

	public Tavolo() {
	}

	public Tavolo(Long id, Ristorante ristorante, boolean attivo,
			int numeroPosti, boolean occupato, String riferimento) {
		this.id = id;
		this.ristorante = ristorante;
		this.attivo = attivo;
		this.numeroPosti = numeroPosti;
		this.occupato = occupato;
		this.riferimento = riferimento;
	}

	public Tavolo(Long id, Ristorante ristorante, boolean attivo,
			int numeroPosti, boolean occupato, String riferimento,
				Set<Ordinazione> ordinaziones) {
		this.id = id;
		this.ristorante = ristorante;
		this.attivo = attivo;
		this.numeroPosti = numeroPosti;
		this.occupato = occupato;
		this.riferimento = riferimento;
		this.ordinaziones = ordinaziones;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Ristorante getRistorante() {
		return this.ristorante;
	}

	public void setRistorante(Ristorante ristorante) {
		this.ristorante = ristorante;
	}

	public boolean isAttivo() {
		return this.attivo;
	}

	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}

	public Integer getNumeroPosti() {
		return this.numeroPosti;
	}

	public void setNumeroPosti(Integer numeroPosti) {
		this.numeroPosti = numeroPosti;
	}

	public boolean isOccupato() {
		return this.occupato;
	}

	public void setOccupato(boolean occupato) {
		this.occupato = occupato;
	}

	public String getRiferimento() {
		return this.riferimento;
	}

	public void setRiferimento(String riferimento) {
		this.riferimento = riferimento;
	}

	public Set<Ordinazione> getOrdinaziones() {
		return this.ordinaziones;
	}

	public void setOrdinaziones(Set<Ordinazione> ordinaziones) {
		this.ordinaziones = ordinaziones;
	}
       
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (attivo ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((numeroPosti == null) ? 0 : numeroPosti.hashCode());
		result = prime * result + (occupato ? 1231 : 1237);
		result = prime * result
				+ ((riferimento == null) ? 0 : riferimento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tavolo other = (Tavolo) obj;
		if (attivo != other.attivo)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numeroPosti == null) {
			if (other.numeroPosti != null)
				return false;
		} else if (!numeroPosti.equals(other.numeroPosti))
			return false;
		if (occupato != other.occupato)
			return false;
		if (riferimento == null) {
			if (other.riferimento != null)
				return false;
		} else if (!riferimento.equals(other.riferimento))
			return false;
		return true;
	}

}