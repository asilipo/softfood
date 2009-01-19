package it.softfood.entity;

import it.softfood.enumeration.TipoStaff;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
@Table(name = "staff")
@SequenceGenerator(name = "sequenza_staff", sequenceName = "seq_id_staff")
@NamedQueries({
    @NamedQuery(name = "Staff.selezionaStaffPerTipo", query = "SELECT s FROM Staff s WHERE s.tipoStaff = :tipo_staff")
})
public class Staff implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenza_staff")
	private Long id;
	@Column(name = "tipo_staff", nullable = false, unique = true)
	private TipoStaff tipoStaff;
	@Column(name = "descrizione" , nullable = true)
	private String descrizione;
    @OneToOne()
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "ristorante", nullable = false)
	private Ristorante ristorante;

	public Staff() {}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public TipoStaff getTipo() {
		return tipoStaff;
	}
	
	public void setTipo(TipoStaff tipoStaff) {
		this.tipoStaff = tipoStaff;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public Ristorante getRistorante() {
		return ristorante;
	}
	
	public void setRistorante(Ristorante ristorante) {
		this.ristorante = ristorante;
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}
