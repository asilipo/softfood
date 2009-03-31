package it.softfood.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Ingrediente implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String tipoIngrediente;
	private String descrizione;
	private String nome;
	private Date scadenza;
	private String unitaMisura;
	private boolean variante;
	private List<Variante> varianti = new ArrayList<Variante>(0);
	private List<IngredientePietanza> ingredientiPietanza = new ArrayList<IngredientePietanza>(0);
	private List<IngredienteMagazzino> ingredientiMagazzino = new ArrayList<IngredienteMagazzino>(0);

	public Ingrediente() {
	}

	public Ingrediente(Long id, String tipoIngrediente, String nome, Date scadenza, boolean variante) {
		this.id = id;
		this.tipoIngrediente = tipoIngrediente;
		this.nome = nome;
		this.scadenza = scadenza;
		this.variante = variante;
	}

	public Ingrediente(Long id, String tipoIngrediente, String descrizione, String nome, 
			Date scadenza, String unitaMisura, boolean variante, List<Variante> varianti, 
				List<IngredientePietanza> ingredientiPietanza,	List<IngredienteMagazzino> ingredientiMagazzino) {
		this.id = id;
		this.tipoIngrediente = tipoIngrediente;
		this.descrizione = descrizione;
		this.nome = nome;
		this.scadenza = scadenza;
		this.unitaMisura = unitaMisura;
		this.variante = variante;
		this.varianti = varianti;
		this.ingredientiPietanza = ingredientiPietanza;
		this.ingredientiMagazzino = ingredientiMagazzino;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoIngrediente() {
		return this.tipoIngrediente;
	}

	public void setTipoIngrediente(String tipoIngrediente) {
		this.tipoIngrediente = tipoIngrediente;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getScadenza() {
		return this.scadenza;
	}

	public void setScadenza(Date scadenza) {
		this.scadenza = scadenza;
	}

	public String getUnitaMisura() {
		return this.unitaMisura;
	}

	public void setUnitaMisura(String unitaMisura) {
		this.unitaMisura = unitaMisura;
	}

	public boolean isVariante() {
		return this.variante;
	}

	public void setVariante(boolean variante) {
		this.variante = variante;
	}

	public List<Variante> getVarianti() {
		return this.varianti;
	}

	public void setVarianti(List<Variante> varianti) {
		this.varianti = varianti;
	}

	public List<IngredientePietanza> getIngredientiPietanza() {
		return this.ingredientiPietanza;
	}

	public void setIngredientiPietanza(List<IngredientePietanza> ingredientiPietanza) {
		this.ingredientiPietanza = ingredientiPietanza;
	}

	public List<IngredienteMagazzino> getIngredientiMagazzino() {
		return this.ingredientiMagazzino;
	}

	public void setIngredienteMagazzinos(List<IngredienteMagazzino> ingredientiMagazzino) {
		this.ingredientiMagazzino = ingredientiMagazzino;
	}
        
    @Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

}
