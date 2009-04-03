package it.softfood.entity;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Variante implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private LineaOrdinazione lineaOrdinazione;
	private Ingrediente ingrediente;
	private Integer tipoVariazione;

	public Variante() {
	}

	public Variante(Long id, LineaOrdinazione lineaOrdinazione,
			Ingrediente ingrediente) {
		this.id = id;
		this.lineaOrdinazione = lineaOrdinazione;
		this.ingrediente = ingrediente;
	}

	public Variante(Long id, LineaOrdinazione lineaOrdinazione,
			Ingrediente ingrediente, Integer tipoVariazione) {
		this.id = id;
		this.lineaOrdinazione = lineaOrdinazione;
		this.ingrediente = ingrediente;
		this.tipoVariazione = tipoVariazione;
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public LineaOrdinazione getLineaOrdinazione() {
		return this.lineaOrdinazione;
	}

	public void setLineaOrdinazione(LineaOrdinazione lineaOrdinazione) {
		this.lineaOrdinazione = lineaOrdinazione;
	}
	
	public Ingrediente getIngrediente() {
		return this.ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}
	
	public Integer getTipoVariazione() {
		return this.tipoVariazione;
	}

	public void setTipoVariazione(Integer tipoVariazione) {
		this.tipoVariazione = tipoVariazione;
	}
	
    @Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

}
