package it.softfood.facade;

import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.Ordinazione;
import it.softfood.session.lineaordinazione.LineaOrdinazioneSessionBeanLocal;
import it.softfood.session.ordinazione.OrdinazioneSessionBeanLocal;

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
	@EJB(beanName = "OrdinazioneSessionBean", mappedName = "it.sofdfood.session.ordinazione.OrdinazioneSessionBean")
	private OrdinazioneSessionBeanLocal ordinazioneSessionBean;
	@EJB(beanName = "LineaOrdinazioneSessionBean", mappedName = "it.sofdfood.session.ordinazione.LineaOrdinazioneSessionBean")
	private LineaOrdinazioneSessionBeanLocal lineaOrdinazioneSessionBean;
	
	public boolean inserisciOrdinazione(Ordinazione ordinazione) {
		if (ordinazione != null)
			if (ordinazioneSessionBean.inserisciOrdinazione(ordinazione) != null) 
				return true;
		
		return false;
	}
	
	public boolean aggiungiLineaOrdinazione(LineaOrdinazione lineaOrdinazione) {
		if (lineaOrdinazione != null) {
			if (lineaOrdinazioneSessionBean.inserisciLineaOrdinazione(lineaOrdinazione) != null) 
				return true;
		}
	
		return false;
	}
	
}
