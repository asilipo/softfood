package it.softfood.facade.ordinazione;

import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.Ordinazione;
import it.softfood.entity.Tavolo;
import it.softfood.session.lineaordinazione.LineaOrdinazioneSessionBeanRemote;
import it.softfood.session.ordinazione.OrdinazioneSessionBeanRemote;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Stateless
public class OrdinazioneFacade implements OrdinazioneFacadeRemote, OrdinazioneFacadeLocal {

	@PersistenceContext
    private EntityManager em;
	@EJB(beanName = "OrdinazioneSessionBean")
	private OrdinazioneSessionBeanRemote ordinazioneSessionBean;
	@EJB(beanName = "LineaOrdinazioneSessionBean")
	private LineaOrdinazioneSessionBeanRemote lineaOrdinazioneSessionBean;
	
	public Ordinazione inserisciOrdinazione(Ordinazione ordinazione) {
		if (ordinazione != null)
			return ordinazioneSessionBean.inserisciOrdinazione(ordinazione);
		
		return null;
	}
	
	public Ordinazione selezionaOrdinazione(Long id) {
		if (id != null) 
			return ordinazioneSessionBean.selezionaOrdinazionePerId(id);
		
		return null;
	}
	
	public List<Ordinazione> selezionaOrdinazioni() {
		return ordinazioneSessionBean.selezionaOrdinazioni();
	}
	
	public List<Ordinazione> selezionaOrdinazioniPerData(Date data) {
		if (data != null) 
			return ordinazioneSessionBean.selezionaOrdinazioniPerData(data);
		
		return null;
	}
	
	public List<Ordinazione> selezionaOrdinazioniGiornalierePerTavolo(Tavolo tavolo, Boolean terminato) {
		if (tavolo != null && terminato != null) 
			return ordinazioneSessionBean.selezionaOrdinazioniGionalierePerTavolo(tavolo, terminato);
		
		return null;
	}
	
	public boolean rimuoviOrdinazione(Long id) {
		if (id != null)
			return ordinazioneSessionBean.rimuoviOrdinazione(id);
		
		return false;
	}
	
	public LineaOrdinazione inserisciLineaOrdinazione(LineaOrdinazione lineaOrdinazione) {
		if (lineaOrdinazione != null) {
			lineaOrdinazione.setOrdinazione(em.merge(lineaOrdinazione.getOrdinazione()));
			
			return lineaOrdinazioneSessionBean.inserisciLineaOrdinazione(lineaOrdinazione);
		}
		
		return null;
	}
	
	public LineaOrdinazione modificaLineaOrdinazione(
			LineaOrdinazione nuovaLineaOrdinazione, LineaOrdinazione vecchiaLineaOrdinazione) {
		return null;
	}
	
}
