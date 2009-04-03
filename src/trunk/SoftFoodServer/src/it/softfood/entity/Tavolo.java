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
	private int numeroPosti;
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

	public int getNumeroPosti() {
		return this.numeroPosti;
	}

	public void setNumeroPosti(int numeroPosti) {
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
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

}