package it.softfood.entity;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Entrata implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Ordinazione ordinazione;
	private Registro registro;

	public Entrata() {
	}

	public Entrata(Long id, Ordinazione ordinazione, Registro registro) {
		this.id = id;
		this.ordinazione = ordinazione;
		this.registro = registro;
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Ordinazione getOrdinazione() {
		return this.ordinazione;
	}

	public void setOrdinazione(Ordinazione ordinazione) {
		this.ordinazione = ordinazione;
	}
	
	public Registro getRegistro() {
		return this.registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}
        
    @Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

}
