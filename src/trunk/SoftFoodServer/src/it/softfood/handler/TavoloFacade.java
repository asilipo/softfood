package it.softfood.facade.tavolo;

import it.softfood.entity.Tavolo;
import it.softfood.session.ristorante.RistoranteSessionBeanRemote;
import it.softfood.session.tavolo.TavoloSessionBeanRemote;

import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Stateless
public class TavoloFacade implements TavoloFacadeRemote, TavoloFacadeLocal {

    @Resource
    private EJBContext ejbCtx;
    @PersistenceContext
    private EntityManager em;
	@EJB(beanName = "TavoloSessionBean")
	private TavoloSessionBeanRemote tavoloSessionBean;
	@EJB(beanName = "RistoranteSessionBean")
	private RistoranteSessionBeanRemote ristoranteSessionBeanRemote;

	public Tavolo inserisciTavolo(Tavolo tavolo) {
		if (tavolo != null)
			return tavoloSessionBean.inserisciTavolo(tavolo);
		
		return null;
	}
	
	public boolean liberaTavolo(Tavolo tavolo) {
		if (tavolo != null)
			return tavoloSessionBean.modificaStatoTavolo(tavolo, false);
		
		return false;
	}
	
	public boolean occupaTavolo(Tavolo tavolo) {
		if (tavolo != null)
			return tavoloSessionBean.modificaStatoTavolo(tavolo, true);	
		
		return false;
	}
	
	public Tavolo selezionaTavolo(Long id) {
		if (id != null)
			return tavoloSessionBean.selezionaTavoloPerId(id);
		
		return null;
	}
	
	public List<Tavolo> selezionaTavoliLiberi() {
		return tavoloSessionBean.selezionaTavoliLiberi();
	}

    public List<Tavolo> selezionaTavoliOccupati() {
		return tavoloSessionBean.selezionaTavoliOccupati();
	}

    public List<Tavolo> selezionaTavoliNonAttivi() {
        return tavoloSessionBean.selezionaTavoliNonAttivi();
    }
	
	public boolean rimuoviTavolo(Long id) {
		if (id != null)
			return tavoloSessionBean.rimuoviTavolo(id);
		
		return false;
	}
    
    public Long occupaTavoli(List<String> riferimenti) {
        try {
            if (riferimenti != null) {
                if (riferimenti.size() == 1) {
                    String riferimento = riferimenti.get(0);
                    if (riferimento != null) {
                        Tavolo tavolo = tavoloSessionBean.selezionaTavoloPerRiferimento(riferimento);
                        tavolo = em.merge(tavolo);
                        if (!tavolo.getOccupato()) {
                            tavolo.setOccupato(true);
                            return tavolo.getId();
                        } else {
                            ejbCtx.setRollbackOnly();
                            return null;
                        }
                    } else {
                        ejbCtx.setRollbackOnly();
                        return null;
                    }
                } else if (riferimenti.size() > 1) {
                    String riferimentoTavoli = null;
                    Integer numeroPosti = 0;
                    for (String riferimento : riferimenti) {
                        if (riferimento != null) {
                            Tavolo tavolo = tavoloSessionBean.selezionaTavoloPerRiferimento(riferimento);
                            tavolo = em.merge(tavolo);
                            if (!tavolo.getOccupato()) {
                                tavolo.setAttivo(false);
                                if (riferimentoTavoli == null)
                                    riferimentoTavoli = tavolo.getRiferimento();
                                else
                                    riferimentoTavoli = riferimentoTavoli + "+" + tavolo.getRiferimento();
                                numeroPosti = numeroPosti + tavolo.getNumeroPosti();
                            }
                            else {
                                ejbCtx.setRollbackOnly();
                                return null;
                            }
                        } else {
                            ejbCtx.setRollbackOnly();
                            return null;
                        }
                    }

                    Tavolo nuovoTavolo = new Tavolo();
                    nuovoTavolo.setAttivo(true);
                    nuovoTavolo.setNumeroPosti(numeroPosti);
                    nuovoTavolo.setOccupato(true);
                    nuovoTavolo.setRiferimento(riferimentoTavoli);
                    nuovoTavolo.setRistorante(ristoranteSessionBeanRemote.selezionaRistorantePerRagioneSociale("La taverna"));

                    return (tavoloSessionBean.inserisciTavolo(nuovoTavolo)).getId();
                }
            }
        } catch (SecurityException ex) {
            ejbCtx.setRollbackOnly();
            Logger.getLogger(TavoloFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            ejbCtx.setRollbackOnly();
            Logger.getLogger(TavoloFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

}
