package it.softfood.facade;

import it.softfood.entity.Ingrediente;
import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.Ordinazione;
import it.softfood.entity.User;
import it.softfood.entity.Variante;
import it.softfood.handler.OrdinazioneFacade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class POSOrdinazioneFacade {

	public ArrayList<LineaOrdinazione> selezionaOrdinazioni(User role) {
		System.out.println("ENTRO");
		OrdinazioneFacade ordinazionefacade=OrdinazioneFacade.getInstance();
		Date data=new Date(System.currentTimeMillis());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String a=sdf.format(data);
		System.out.println("INIZIALIZZO");
		try {
			data=sdf.parse(a);
		} catch (ParseException e1) {
			System.err.println("ERRORE 1");
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("PARSO");
		ArrayList<Ordinazione> ord = null;
		ArrayList<LineaOrdinazione> non_evasi = new ArrayList<LineaOrdinazione>();
		Set<LineaOrdinazione> linea=new HashSet<LineaOrdinazione>();
		
		try {
			ord = ordinazionefacade.selezionaOrdinazioniGiornaliere(role);
			System.out.println("ORDINI PRESENTI "+ord.size());
			for(Ordinazione o : ord){
				linea=o.getLineaOrdinaziones();
				for(LineaOrdinazione lin : linea)
					if(!lin.getEvaso())
						non_evasi.add((LineaOrdinazione)lin);
			}
			
		} catch (Exception e) {
			System.err.println("ERRORE 2");
			e.printStackTrace();
		}
		return non_evasi;
	}

	public LineaOrdinazione getLinea(User role,Long id){
		OrdinazioneFacade ordinazionefacade=OrdinazioneFacade.getInstance();
		LineaOrdinazione linea=ordinazionefacade.selezionaLineaOrdinazionePerId(role, id);
		System.out.println("LINEA POS"+linea);
		return linea;
	}
	
	public ArrayList<Variante> getVariante(User role,LineaOrdinazione lin){
		OrdinazioneFacade ordinazionefacade=OrdinazioneFacade.getInstance();
		ArrayList<Variante> linea=ordinazionefacade.selezionaVariantiPerLineaOrdinazione(role, lin);
		return linea;
	}
}
