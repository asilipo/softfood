package it.softfood;

import java.util.List;

import it.softfood.entity.Tavolo;
import it.softfood.facade.SoftfoodFacade;
import it.softfood.handler.TavoloFacade;

public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
//		TavoloSession tavolo=new TavoloSession();
//		RistoranteSession ristorante=new RistoranteSession();
//		List<Tavolo> tav=tavolo.selezionaTavoliLiberi();
//		Tavolo tav1=tavolo.selezionaTavoloPerId(new Long(1));
//		System.out.println("Oggetto tavolo : "+tav);
//		System.out.println("TAVOLI LIBERI: "+tav.size());
//		System.out.println("TAVOLI SELEZIONATO: "+tav1.getRiferimento());
//		
//		
//		Ristorante rist=ristorante.selezionaRistorantePerRagioneSociale("La taverna");
////		Ristorante rist=ristorante.selezionaRistorantePerPartitaIva("12032874912");
//		System.out.println("RISTORANTE: "+rist);
//		
//		Tavolo t=new Tavolo();
//		t.setRistorante(rist);
//		t.setRiferimento("NUOVO TAVOLO");
//		t.setId(new Long(21));
//		t.setNumeroPosti(4);
//		t.setOccupato(false);
//		t.setAttivo(true);
//		
//		t=tavolo.inserisciTavolo(t);
//		
//		tav1=tavolo.selezionaTavoloPerId(t.getId());
//		
//		tavolo.modificaStatoTavolo(t, false);
		
//		SoftfoodFacade facade=SoftfoodFacade.getInstance();
		TavoloFacade facade=TavoloFacade.getInstance();
		//List<Tavolo> l=	facade.selezionaTavoliLiberi();
		//System.out.println("sddddddddddddddddddddddddd"+l);

	}
	

}
