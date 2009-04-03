package it.softfood;

import java.util.ArrayList;
import java.util.List;

import it.softfood.entity.Ordinazione;
import it.softfood.entity.Ristorante;
import it.softfood.entity.Tavolo;
import it.softfood.facade.SoftfoodFacade;
import it.softfood.handler.ITavoloFacade;
import it.softfood.handler.OrdinazioneFacade;
import it.softfood.handler.RistoranteFacade;
import it.softfood.handler.TavoloFacade;
import it.softfood.session.OrdinazioneSession;

public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
//		TavoloSession tavolo=new TavoloSession();
//		RistoranteSession ristorante= RistoranteSession.getInstance();
//		List<Tavolo> tav=tavolo.selezionaTavoliLiberi();
//		Tavolo tav1=tavolo.selezionaTavoloPerId(new Long(1));
//		System.out.println("Oggetto tavolo : "+tav);
//		System.out.println("TAVOLI LIBERI: "+tav.size());
//		System.out.println("TAVOLI SELEZIONATO: "+tav1.getRiferimento());
//		
//		
//		Ristorante rist= ristorante.selezionaRistorantePerRagioneSociale("La taverna");
//		System.out.println("ristoranteeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee " +rist.getPartitaIva());
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
//		TavoloFacade facade=TavoloFacade.getInstance();
		//List<Tavolo> l=	facade.selezionaTavoliLiberi();
		//System.out.println("sddddddddddddddddddddddddd"+l);
//		TavoloFacade facade=new TavoloFacade();
//        Tavolo tavo=facade.selezionaTavolo(new Long(2));
//        Tavolo tav1=facade.selezionaTavolo(new Long(19));
//        ArrayList<String> list=new ArrayList<String>();
//        list.add(tav.getRiferimento());
//        System.out.println("AGGIUNTO TAVOLO "+tav.getRiferimento());
//        list.add(tav1.getRiferimento());
//        System.out.println("AGGIUNTO TAVOLO "+tav1.getRiferimento());
//        Long id=facade.occupaTavoli(list);
//        facade.rimuoviTavolo(id);
		
		ITavoloFacade tav=TavoloFacade.getInstance();
		OrdinazioneFacade ord=OrdinazioneFacade.getInstance();
		RistoranteFacade rist=RistoranteFacade.getInstance();
		
	
//		Ordinazione ordine=new Ordinazione();
//		ordine.setTavolo(tavo);
//		
//		System.out.println("ID NUOVO ORDINE: "+ord.selezionaOrdinazioneGiornalieraPerTavolo(tavo, false).getId());
        


	}
	

}

