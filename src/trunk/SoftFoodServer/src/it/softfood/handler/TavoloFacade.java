package it.softfood.handler;

import it.softfood.entity.Tavolo;

import it.softfood.session.RistoranteSession;
import it.softfood.session.TavoloSession;


import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */


public class TavoloFacade implements ITavoloFacade {
	
	private static ITavoloFacade singleton; 

	private TavoloSession tavoloSession=TavoloSession.getInstance();

	private RistoranteSession ristoranteSession=RistoranteSession.getInstance();
	
	public TavoloFacade() {
	}
	
	public synchronized static ITavoloFacade getInstance() {
		if (singleton == null) {
			singleton = new TavoloFacade();
		}
		return singleton;
	}

	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#inserisciTavolo(it.softfood.entity.Tavolo)
	 */
	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#inserisciTavolo(it.softfood.entity.Tavolo)
	 */
	public Tavolo inserisciTavolo(Tavolo tavolo) {
		if (tavolo != null)
			return tavoloSession.inserisciTavolo(tavolo);
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#liberaTavolo(it.softfood.entity.Tavolo)
	 */
	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#liberaTavolo(it.softfood.entity.Tavolo)
	 */
	public boolean liberaTavolo(Tavolo tavolo) {
		if (tavolo != null)
			return tavoloSession.modificaStatoTavolo(tavolo, false);
		
		return false;
	}
	
	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#occupaTavolo(it.softfood.entity.Tavolo)
	 */
	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#occupaTavolo(it.softfood.entity.Tavolo)
	 */
	public boolean occupaTavolo(Tavolo tavolo) {
		if (tavolo != null)
			return tavoloSession.modificaStatoTavolo(tavolo, true);	
		
		return false;
	}
	
	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#selezionaTavolo(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#selezionaTavolo(java.lang.Long)
	 */
	public Tavolo selezionaTavolo(Long id) {
		if (id != null)
			return tavoloSession.selezionaTavoloPerId(id);
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#selezionaTavoliLiberi()
	 */
	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#selezionaTavoliLiberi()
	 */
	public List<Tavolo> selezionaTavoliLiberi() {
		return tavoloSession.selezionaTavoliLiberi();
	}

    /* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#selezionaTavoliOccupati()
	 */
    /* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#selezionaTavoliOccupati()
	 */
    public List<Tavolo> selezionaTavoliOccupati() {
		return tavoloSession.selezionaTavoliOccupati();
	}

    /* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#selezionaTavoliNonAttivi()
	 */
    /* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#selezionaTavoliNonAttivi()
	 */
    public List<Tavolo> selezionaTavoliNonAttivi() {
        return tavoloSession.selezionaTavoliNonAttivi();
    }
	
	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#rimuoviTavolo(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#rimuoviTavolo(java.lang.Long)
	 */
	public boolean rimuoviTavolo(Long id) {
		if (id != null)
			return tavoloSession.rimuoviTavolo(id);
		
		return false;
	}
    
    /* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#occupaTavoli(java.util.List)
	 */
    /* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#occupaTavoli(java.util.List)
	 */
    public Long occupaTavoli(List<String> riferimenti) {
        try {
            if (riferimenti != null) {
                if (riferimenti.size() == 1) {
                    String riferimento = riferimenti.get(0);
                    if (riferimento != null) {
                        Tavolo tavolo = tavoloSession.selezionaTavoloPerRiferimento(riferimento);
//                        tavolo = tavoloSession.merge(tavolo);
                        if (!tavolo.isOccupato()) {
                            tavolo.setOccupato(true);
                            tavoloSession.update(tavolo);
                            return tavolo.getId();
                        } else {
                        	tavolo.setOccupato(false);
                            return null;
                        }
                    } else {
                        throw new IllegalStateException();
//                        return null;
                    }
                } else if (riferimenti.size() > 1) {
                    String riferimentoTavoli = null;
                    Integer numeroPosti = 0;
                    for (String riferimento : riferimenti) {
                        if (riferimento != null) {
                            Tavolo tavolo = tavoloSession.selezionaTavoloPerRiferimento(riferimento);
//                            tavolo = tavoloSession.merge(tavolo);
                            if (!tavolo.isOccupato()) {
                                tavolo.setAttivo(false);
                                tavoloSession.update(tavolo);
                                if (riferimentoTavoli == null)
                                    riferimentoTavoli = tavolo.getRiferimento();
                                else
                                    riferimentoTavoli = riferimentoTavoli + "+" + tavolo.getRiferimento();
                                numeroPosti = numeroPosti + tavolo.getNumeroPosti();
                            }
                            else {
                            	tavolo.setAttivo(true);
                            	tavoloSession.update(tavolo);
                                return null;
                            }
                            
                        } else {
                            throw new IllegalStateException();
                        }
                    }

                    Tavolo nuovoTavolo = new Tavolo();
                    nuovoTavolo.setAttivo(true);
                    nuovoTavolo.setNumeroPosti(numeroPosti);
                    nuovoTavolo.setOccupato(true);
                    nuovoTavolo.setRiferimento(riferimentoTavoli);
                    nuovoTavolo.setRistorante(ristoranteSession.selezionaRistorantePerRagioneSociale("La taverna"));
                    nuovoTavolo=(tavoloSession.inserisciTavolo(nuovoTavolo));
                    return nuovoTavolo.getId();
                }
            }
        } catch (SecurityException ex) {
//            ejbCtx.setRollbackOnly();
            Logger.getLogger(TavoloFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
//            ejbCtx.setRollbackOnly();
            Logger.getLogger(TavoloFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

}
