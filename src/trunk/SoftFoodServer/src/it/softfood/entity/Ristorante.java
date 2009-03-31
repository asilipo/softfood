package it.softfood.entity;

import java.util.ArrayList;
import java.util.List;

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
	private List<Registro> registri = new ArrayList<Registro>(0);
	private List<Fornitore> fornitori = new ArrayList<Fornitore>(0);
	private List<Staff> staff = new ArrayList<Staff>(0);
	private List<Tavolo> tavoli = new ArrayList<Tavolo>(0);
	private List<Menu> menu = new ArrayList<Menu>(0);

	public Ristorante() {
	}

	public Ristorante(String ragioneSociale, Indirizzo indirizzo,
			String partitaIva) {
		this.ragioneSociale = ragioneSociale;
		this.indirizzo = indirizzo;
		this.partitaIva = partitaIva;
	}

	public Ristorante(String ragioneSociale, Indirizzo indirizzo, String partitaIva, 
			List<Registro> registri, List<Fornitore> fornitori, List<Staff> staff, 
				List<Tavolo> tavoli, List<Menu> menu) {
		this.ragioneSociale = ragioneSociale;
		this.indirizzo = indirizzo;
		this.partitaIva = partitaIva;
		this.registri = registri;
		this.fornitori = fornitori;
		this.staff = staff;
		this.tavoli = tavoli;
		this.menu = menu;
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

	public List<Registro> getRegistri() {
		return this.registri;
	}

	public void setRegistri(List<Registro> registri) {
		this.registri = registri;
	}

	public List<Fornitore> getFornitori() {
		return this.fornitori;
	}

	public void setFornitori(List<Fornitore> fornitori) {
		this.fornitori = fornitori;
	}

	public List<Staff> getStaff() {
		return this.staff;
	}

	public void setStaff(List<Staff> staff) {
		this.staff = staff;
	}

	public List<Tavolo> getTavoli() {
		return this.tavoli;
	}

	public void setTavoli(List<Tavolo> tavoli) {
		this.tavoli = tavoli;
	}

	public List<Menu> getMenu() {
		return this.menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}
        
	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

}
