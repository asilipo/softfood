package it.softfood.facade.tavolo;

import it.softfood.entity.Tavolo;
import it.softfood.session.ristorante.RistoranteSessionBeanRemote;
import it.softfood.session.tavolo.TavoloSessionBeanRemote;

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
public class TavoloFacade implements TavoloFacadeRemote, TavoloFacadeLocal {

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
//        EntityTransaction tx = em.getTransaction();
        try {
            if (riferimenti != null) {
                if (riferimenti.size() == 1) {
//                    tx.begin();

                    String riferimento = riferimenti.get(0);
                    if (riferimento != null) {
                        Tavolo tavolo = tavoloSessionBean.selezionaTavoloPerRiferimento(riferimento);
                        tavolo = em.merge(tavolo);
                        if (!tavolo.getOccupato()) {
                            tavolo.setOccupato(true);
//                            tx.commit();
                            return tavolo.getId();
                        } else {
                            return null;
                        }
                    } else {
                        return null;
                    }
                } else if (riferimenti.size() > 1) {
//                    tx.begin();
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
                                    riferimentoTavoli = riferimentoTavoli + " + " + tavolo.getRiferimento();
                                numeroPosti = numeroPosti + tavolo.getNumeroPosti();
                            }
                            else
                                return null;
                        } else {
                            return null;
                        }
                    }

                    Tavolo nuovoTavolo = new Tavolo();
                    nuovoTavolo.setAttivo(true);
                    nuovoTavolo.setNumeroPosti(numeroPosti);
                    nuovoTavolo.setOccupato(true);
                    nuovoTavolo.setRiferimento(riferimentoTavoli);
                    nuovoTavolo.setRistorante(ristoranteSessionBeanRemote.selezionaRistorantePerRagioneSociale("La taverna"));

//                    tx.commit();

                    return (tavoloSessionBean.inserisciTavolo(nuovoTavolo)).getId();
                }
            }
        } catch (RuntimeException e) {
//            tx.rollback();
        }

        return null;
    }

}
