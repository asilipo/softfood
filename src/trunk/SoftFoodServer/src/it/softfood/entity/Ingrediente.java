package it.softfood.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
	private Set<Variante> varianti = new HashSet<Variante>(0);
	private Set<IngredientePietanza> ingredientiPietanza = new HashSet<IngredientePietanza>(0);
	private Set<IngredienteMagazzino> ingredientiMagazzino = new HashSet<IngredienteMagazzino>(0);

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
			Date scadenza, String unitaMisura, boolean variante, Set<Variante> varianti, 
			Set<IngredientePietanza> ingredientiPietanza,	Set<IngredienteMagazzino> ingredientiMagazzino) {
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

	public Set<Variante> getVarianti() {
		return this.varianti;
	}

	public void setVarianti(Set<Variante> varianti) {
		this.varianti = varianti;
	}

	public Set<IngredientePietanza> getIngredientiPietanza() {
		return this.ingredientiPietanza;
	}

	public void setIngredientiPietanza(Set<IngredientePietanza> ingredientiPietanza) {
		this.ingredientiPietanza = ingredientiPietanza;
	}

	public Set<IngredienteMagazzino> getIngredientiMagazzino() {
		return this.ingredientiMagazzino;
	}

	public void setIngredienteMagazzinos(Set<IngredienteMagazzino> ingredientiMagazzino) {
		this.ingredientiMagazzino = ingredientiMagazzino;
	}
        
    @Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

}
