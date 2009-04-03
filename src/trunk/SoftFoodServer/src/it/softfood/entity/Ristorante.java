package it.softfood.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Ristorante implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String ragioneSociale;
	private Indirizzo indirizzo;
	private String partitaIva;
	private Set<Registro> registros = new HashSet<Registro>(0);
	private Set<Fornitore> fornitores = new HashSet<Fornitore>(0);
	private Set<Staff> staffs = new HashSet<Staff>(0);
	private Set<Tavolo> tavolos = new HashSet<Tavolo>(0);
	private Set<Menu> menus = new HashSet<Menu>(0);

	public Ristorante() {
	}

	public Ristorante(String ragioneSociale, Indirizzo indirizzo, String partitaIva) {
		this.ragioneSociale = ragioneSociale;
		this.indirizzo = indirizzo;
		this.partitaIva = partitaIva;
	}

	public Ristorante(String ragioneSociale, Indirizzo indirizzo, String partitaIva, 
			Set<Registro> registros, Set<Fornitore> fornitores, Set<Staff> staffs, 
				Set<Tavolo> tavolos, Set<Menu> menus) {
		this.ragioneSociale = ragioneSociale;
		this.indirizzo = indirizzo;
		this.partitaIva = partitaIva;
		this.registros = registros;
		this.fornitores = fornitores;
		this.staffs = staffs;
		this.tavolos = tavolos;
		this.menus = menus;
	}

	public String getRagioneSociale() {
		return this.ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public Indirizzo getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(Indirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getPartitaIva() {
		return this.partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	public Set<Registro> getRegistros() {
		return this.registros;
	}

	public void setRegistros(Set<Registro> registros) {
		this.registros = registros;
	}

	public Set<Fornitore> getFornitores() {
		return this.fornitores;
	}

	public void setFornitores(Set<Fornitore> fornitores) {
		this.fornitores = fornitores;
	}

	public Set<Staff> getStaffs() {
		return this.staffs;
	}

	public void setStaffs(Set<Staff> staffs) {
		this.staffs = staffs;
	}

	public Set<Tavolo> getTavolos() {
		return this.tavolos;
	}

	public void setTavolos(Set<Tavolo> tavolos) {
		this.tavolos = tavolos;
	}

	public Set<Menu> getMenus() {
		return this.menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
        
	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

}
