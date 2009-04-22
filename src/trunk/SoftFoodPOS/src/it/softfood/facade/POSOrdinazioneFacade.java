package it.softfood.facade;

import it.softfood.entity.Ingrediente;
import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.Ordinazione;
import it.softfood.entity.User;
import it.softfood.entity.Variante;
import it.softfood.enumeration.TipoPietanza;
import it.softfood.handler.OrdinazioneFacade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class POSOrdinazioneFacade {

	public ArrayList<LineaOrdinazione> selezionaOrdinazionePerData(User role) {
		OrdinazioneFacade ordineFacade=OrdinazioneFacade.getInstance();
		Date data=new Date(System.currentTimeMillis());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String a=sdf.format(data);
		try {
			data=sdf.parse(a);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<Ordinazione> ord = null;
		ArrayList<LineaOrdinazione> non_evasi = new ArrayList<LineaOrdinazione>();
		Set<LineaOrdinazione> linea=null;
		try {
			ord = ordineFacade.selezionaOrdinazioniPerData(role, data);
			for(Ordinazione o : ord){
				linea=o.getLineaOrdinaziones();
				for(LineaOrdinazione lin : linea)
					if(!lin.getEvaso())
						non_evasi.add(lin);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return non_evasi;
	}

}
