package it.softfood.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Articolo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Listino listino;
	private String tipoArticolo;
	private String descrizione;
	private String nome;
	private Float capacita;
	private Integer tipoPietanza;
	private Set<IngredientePietanza> ingredientePietanzas = new HashSet<IngredientePietanza>(0);
	private Set<BevandaMagazzino> bevandaMagazzinos = new HashSet<BevandaMagazzino>(0);
	private Set<LineaOrdinazione> lineaOrdinaziones = new HashSet<LineaOrdinazione>(0);

	public Articolo() {
	}

	public Articolo(Long id, String tipoArticolo, String nome) {
		this.id = id;
		this.tipoArticolo = tipoArticolo;
		this.nome = nome;
	}

	public Articolo(Long id, Listino listino, String tipoArticolo, String descrizione, 
			String nome, Float capacita, Integer tipoPietanza, Set<IngredientePietanza> ingredientePietanzas,
				Set<BevandaMagazzino> bevandaMagazzinos, Set<LineaOrdinazione> lineaOrdinaziones) {
		this.id = id;
		this.listino = listino;
		this.tipoArticolo = tipoArticolo;
		this.descrizione = descrizione;
		this.nome = nome;
		this.capacita = capacita;
		this.tipoPietanza = tipoPietanza;
		this.ingredientePietanzas = ingredientePietanzas;
		this.bevandaMagazzinos = bevandaMagazzinos;
		this.lineaOrdinaziones = lineaOrdinaziones;
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Listino getListino() {
		return this.listino;
	}

	public void setListino(Listino listino) {
		this.listino = listino;
	}
	
	public String getTipoArticolo() {
		return this.tipoArticolo;
	}

	public void setTipoArticolo(String tipoArticolo) {
		this.tipoArticolo = tipoArticolo;
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
	
	public Float getCapacita() {
		return this.capacita;
	}

	public void setCapacita(Float capacita) {
		this.capacita = capacita;
	}
	
	public Integer getTipoPietanza() {
		return this.tipoPietanza;
	}

	public void setTipoPietanza(Integer tipoPietanza) {
		this.tipoPietanza = tipoPietanza;
	}
	
	public Set<IngredientePietanza> getIngredientePietanzas() {
		return this.ingredientePietanzas;
	}

	public void setIngredientePietanzas(
			Set<IngredientePietanza> ingredientePietanzas) {
		this.ingredientePietanzas = ingredientePietanzas;
	}
	
	public Set<BevandaMagazzino> getBevandaMagazzinos() {
		return this.bevandaMagazzinos;
	}

	public void setBevandaMagazzinos(Set<BevandaMagazzino> bevandaMagazzinos) {
		this.bevandaMagazzinos = bevandaMagazzinos;
	}
	
	public Set<LineaOrdinazione> getLineaOrdinaziones() {
		return this.lineaOrdinaziones;
	}

	public void setLineaOrdinaziones(Set<LineaOrdinazione> lineaOrdinaziones) {
		this.lineaOrdinaziones = lineaOrdinaziones;
	}
        
    @Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

}
