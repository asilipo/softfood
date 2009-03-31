package it.softfood.entity;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Staff implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Ristorante ristorante;
	private String descrizione;
	private int tipoStaff;

	public Staff() {
	}

	public Staff(Long id, Ristorante ristorante, int tipoStaff) {
		this.id = id;
		this.ristorante = ristorante;
		this.tipoStaff = tipoStaff;
	}

	public Staff(Long id, Ristorante ristorante, String descrizione,
			int tipoStaff) {
		this.id = id;
		this.ristorante = ristorante;
		this.descrizione = descrizione;
		this.tipoStaff = tipoStaff;
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

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getTipoStaff() {
		return this.tipoStaff;
	}

	public void setTipoStaff(int tipoStaff) {
		this.tipoStaff = tipoStaff;
	}
        
	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

}
