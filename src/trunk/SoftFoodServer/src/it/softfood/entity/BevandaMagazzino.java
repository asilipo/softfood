package it.softfood.entity;

public class BevandaMagazzino implements java.io.Serializable {

	private Long id;
	private Magazzino magazzino;
	private Articolo articolo;
	private int quantita;

	public BevandaMagazzino() {
	}

	public BevandaMagazzino(Long id, Magazzino magazzino, int quantita) {
		this.id = id;
		this.magazzino = magazzino;
		this.quantita = quantita;
	}

	public BevandaMagazzino(Long id, Magazzino magazzino, Articolo articolo,
			int quantita) {
		this.id = id;
		this.magazzino = magazzino;
		this.articolo = articolo;
		this.quantita = quantita;
	}


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Magazzino getMagazzino() {
		return this.magazzino;
	}

	public void setMagazzino(Magazzino magazzino) {
		this.magazzino = magazzino;
	}


	public Articolo getArticolo() {
		return this.articolo;
	}

	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}

	
	public int getQuantita() {
		return this.quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

        @Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}
}
