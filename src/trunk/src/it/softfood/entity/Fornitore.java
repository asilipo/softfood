package it.softfood.entity;


public class Fornitore implements java.io.Serializable {

	private String ragioneSociale;
	private Ristorante ristorante;
	private Indirizzo indirizzo;
	private String descrizione;
	private String partitaIva;

	public Fornitore() {
	}

	public Fornitore(String ragioneSociale, Ristorante ristorante,
			Indirizzo indirizzo, String partitaIva) {
		this.ragioneSociale = ragioneSociale;
		this.ristorante = ristorante;
		this.indirizzo = indirizzo;
		this.partitaIva = partitaIva;
	}

	public Fornitore(String ragioneSociale, Ristorante ristorante,
			Indirizzo indirizzo, String descrizione, String partitaIva) {
		this.ragioneSociale = ragioneSociale;
		this.ristorante = ristorante;
		this.indirizzo = indirizzo;
		this.descrizione = descrizione;
		this.partitaIva = partitaIva;
	}


	public String getRagioneSociale() {
		return this.ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}


	public Ristorante getRistorante() {
		return this.ristorante;
	}

	public void setRistorante(Ristorante ristorante) {
		this.ristorante = ristorante;
	}


	public Indirizzo getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(Indirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}


	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public String getPartitaIva() {
		return this.partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}
        
        @Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}

}
