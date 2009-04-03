package it.softfood.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Ingrediente implements java.io.Serializable {

	private Long id;
	private String tipoIngrediente;
	private String descrizione;
	private String nome;
	private Date scadenza;
	private String unitaMisura;
	private boolean variante;
	private Set<Variante> variantes = new HashSet<Variante>(0);
	private Set<IngredientePietanza> ingredientePietanzas = new HashSet<IngredientePietanza>(
			0);
	private Set<IngredienteMagazzino> ingredienteMagazzinos = new HashSet<IngredienteMagazzino>(
			0);

	public Ingrediente() {
	}

	public Ingrediente(Long id, String tipoIngrediente, String nome,
			Date scadenza, boolean variante) {
		this.id = id;
		this.tipoIngrediente = tipoIngrediente;
		this.nome = nome;
		this.scadenza = scadenza;
		this.variante = variante;
	}

	public Ingrediente(Long id, String tipoIngrediente, String descrizione,
			String nome, Date scadenza, String unitaMisura, boolean variante,
			Set<Variante> variantes,
			Set<IngredientePietanza> ingredientePietanzas,
			Set<IngredienteMagazzino> ingredienteMagazzinos) {
		this.id = id;
		this.tipoIngrediente = tipoIngrediente;
		this.descrizione = descrizione;
		this.nome = nome;
		this.scadenza = scadenza;
		this.unitaMisura = unitaMisura;
		this.variante = variante;
		this.variantes = variantes;
		this.ingredientePietanzas = ingredientePietanzas;
		this.ingredienteMagazzinos = ingredienteMagazzinos;
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

	public Set<Variante> getVariantes() {
		return this.variantes;
	}

	public void setVariantes(Set<Variante> variantes) {
		this.variantes = variantes;
	}

	
	public Set<IngredientePietanza> getIngredientePietanzas() {
		return this.ingredientePietanzas;
	}

	public void setIngredientePietanzas(
			Set<IngredientePietanza> ingredientePietanzas) {
		this.ingredientePietanzas = ingredientePietanzas;
	}

	
	public Set<IngredienteMagazzino> getIngredienteMagazzinos() {
		return this.ingredienteMagazzinos;
	}

	public void setIngredienteMagazzinos(
			Set<IngredienteMagazzino> ingredienteMagazzinos) {
		this.ingredienteMagazzinos = ingredienteMagazzinos;
	}
        
        @Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}

}
