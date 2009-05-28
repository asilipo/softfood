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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fornitores == null) ? 0 : fornitores.hashCode());
		result = prime * result
				+ ((indirizzo == null) ? 0 : indirizzo.hashCode());
		result = prime * result + ((menus == null) ? 0 : menus.hashCode());
		result = prime * result
				+ ((partitaIva == null) ? 0 : partitaIva.hashCode());
		result = prime * result
				+ ((ragioneSociale == null) ? 0 : ragioneSociale.hashCode());
		result = prime * result
				+ ((registros == null) ? 0 : registros.hashCode());
		result = prime * result + ((staffs == null) ? 0 : staffs.hashCode());
		result = prime * result + ((tavolos == null) ? 0 : tavolos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ristorante other = (Ristorante) obj;
		if (fornitores == null) {
			if (other.fornitores != null)
				return false;
		} else if (!fornitores.equals(other.fornitores))
			return false;
		/*if (indirizzo == null) {
			if (other.indirizzo != null)
				return false;
		} else if (!indirizzo.equals(other.indirizzo))
			return false;*/
		if (menus == null) {
			if (other.menus != null)
				return false;
		} else if (!menus.equals(other.menus))
			return false;
		if (partitaIva == null) {
			if (other.partitaIva != null)
				return false;
		} else if (!partitaIva.equals(other.partitaIva))
			return false;
		if (ragioneSociale == null) {
			if (other.ragioneSociale != null)
				return false;
		} else if (!ragioneSociale.equals(other.ragioneSociale))
			return false;
		if (registros == null) {
			if (other.registros != null)
				return false;
		} else if (!registros.equals(other.registros))
			return false;
		if (staffs == null) {
			if (other.staffs != null)
				return false;
		} else if (!staffs.equals(other.staffs))
			return false;
		if (tavolos == null) {
			if (other.tavolos != null)
				return false;
		} else if (!tavolos.equals(other.tavolos))
			return false;
		return true;
	}

}
