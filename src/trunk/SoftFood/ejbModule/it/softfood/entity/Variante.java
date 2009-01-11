package it.softfood.entity;

import it.softfood.enumeration.TipoVariante;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "variante")
@SequenceGenerator(name = "sequenza_variante", sequenceName = "seq_id_variante")
@NamedQueries({
	@NamedQuery(name = "Variante.selezionaVariantiPerIngrediente", query = "SELECT v FROM Variante v WHERE v.ingrediente = :ingrediente"),
	@NamedQuery(name = "Variante.selezionaVariantiPerLineaOrdinazione", query = "SELECT v FROM Variante v WHERE v.lineaOrdinazione = :linea_ordinazione")
})
public class Variante implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenza_variante")
	private Long id;
	@Column(name = "tipo_variazione")
	private TipoVariante tipoVariazione;
	@ManyToOne()
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "linea_ordinazione", nullable = false)
    private LineaOrdinazione lineaOrdinazione;
	@ManyToOne()
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "ingrediente", nullable = false)
    private Ingrediente ingrediente;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public TipoVariante getTipoVariazione() {
		return tipoVariazione;
	}
	
	public void setTipoVariazione(TipoVariante tipoVariazione) {
		this.tipoVariazione = tipoVariazione;
	}
	
	public LineaOrdinazione getLineaOrdinazione() {
		return lineaOrdinazione;
	}
	
	public void setLineaOrdinazione(LineaOrdinazione lineaOrdinazione) {
		this.lineaOrdinazione = lineaOrdinazione;
	}
	
	public Ingrediente getIngrediente() {
		return ingrediente;
	}
	
	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
}
