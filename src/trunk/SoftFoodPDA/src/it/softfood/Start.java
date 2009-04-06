package it.softfood;

import java.util.ArrayList;

import it.softfood.entity.Ordinazione;
import it.softfood.entity.Pietanza;
import it.softfood.entity.Tavolo;
import it.softfood.enumeration.TipoPietanza;
import it.softfood.facade.PDAArticoloMenuFacade;
import it.softfood.facade.PDAOrdinazioneFacade;
import it.softfood.facade.PDATavoloFacade;

public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PDATavoloFacade pda=new PDATavoloFacade();
		PDAOrdinazioneFacade pdaOrd=new PDAOrdinazioneFacade();
		PDAArticoloMenuFacade articolo = new PDAArticoloMenuFacade();
//		Tavolo tav=pda.selezionaTavolo(new Long(0));
//		Ordinazione ordine = pdaOrd.selezionaOrdinazioneGiornalieraPerTavolo(tav.getRiferimento(), new Boolean("false"));
//		
//		System.out.println("TERMINATO OK! "+ordine.getId());
		
		ArrayList<Pietanza> array= articolo.selezionaPietanzeDisponibiliPerTipo(TipoPietanza.DOLCE);
		
		System.out.println(array.size());
		

	}

}
