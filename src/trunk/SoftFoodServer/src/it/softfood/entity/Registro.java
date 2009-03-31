package it.softfood.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Registro implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Ristorante ristorante;
	private int annoRiferimento;
	private String descrizione;
	private List<Entrata> entrate = new ArrayList<Entrata>(0);

	public Registro() {
	}

	public Registro(Long id, Ristorante ristorante, int annoRiferimento) {
		this.id = id;
		this.ristorante = ristorante;
		this.annoRiferimento = annoRiferimento;
	}

	public Registro(Long id, Ristorante ristorante, int annoRiferimento,
			String descrizione, List<Entrata> entrate) {
		this.id = id;
		this.ristorante = ristorante;
		this.annoRiferimento = annoRiferimento;
		this.descrizione = descrizione;
		this.entrate = entrate;
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

	public int getAnnoRiferimento() {
		return this.annoRiferimento;
	}

	public void setAnnoRiferimento(int annoRiferimento) {
		this.annoRiferimento = annoRiferimento;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Entrata> getEntrate() {
		return this.entrate;
	}

	public void setEntrate(List<Entrata> entrate) {
		this.entrate = entrate;
	}
        
	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}


}
