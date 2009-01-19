package it.softfood.facade.ordinazione;

import javax.ejb.Local;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Local
public interface OrdinazioneFacadeLocal {

	it.softfood.entity.Ordinazione inserisciOrdinazione(it.softfood.entity.Ordinazione ordinazione);
	
	it.softfood.entity.Ordinazione selezionaOrdinazionePerId(Long id);

    it.softfood.entity.Ordinazione modificaOrdinazione(it.softfood.entity.Ordinazione nuovaOrdinazione, it.softfood.entity.Ordinazione vecchiaOrdinazione);
	
	java.util.List<it.softfood.entity.Ordinazione> selezionaOrdinazioni();
	
	java.util.List<it.softfood.entity.Ordinazione> selezionaOrdinazioniPerData(java.util.Date data);
	
	java.util.List<it.softfood.entity.Ordinazione> selezionaOrdinazioniGiornalierePerTavolo(it.softfood.entity.Tavolo tavolo, Boolean terminato);
	
	boolean rimuoviOrdinazione(Long id);
	
	it.softfood.entity.LineaOrdinazione inserisciLineaOrdinazione(it.softfood.entity.LineaOrdinazione lineaOrdinazione);
	
	it.softfood.entity.LineaOrdinazione modificaLineaOrdinazione(
			it.softfood.entity.LineaOrdinazione nuovaLineaOrdinazione, it.softfood.entity.LineaOrdinazione vecchiaLineaOrdinazione);

    it.softfood.entity.LineaOrdinazione selezionaLineaOrdinazionePerId(java.lang.Long id);

    java.util.List<it.softfood.entity.LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazione(it.softfood.entity.Ordinazione ordinazione);

    boolean rimuoviLineaOrdinazione(java.lang.Long id);

    it.softfood.entity.Variante inserisciVariante(it.softfood.entity.Variante variante);

    it.softfood.entity.Variante modificaVariante(it.softfood.entity.Variante nuovaVariante, it.softfood.entity.Variante vecchiaVariante);

    it.softfood.entity.Variante selezionaVariantePerId(java.lang.Long id);

    java.util.List<it.softfood.entity.Variante> selezionaVariantiPerIngrediente(it.softfood.entity.Ingrediente ingrediente);

    java.util.List<it.softfood.entity.Variante> selezionaVariantiPerLineaOrdinazione(it.softfood.entity.LineaOrdinazione lineaOrdinazione);

    boolean rimuoviVariante(java.lang.Long id);

}
