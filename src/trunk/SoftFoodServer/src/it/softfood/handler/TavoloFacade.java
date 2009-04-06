package it.softfood.handler;

import it.softfood.entity.Tavolo;
import it.softfood.entity.User;
import it.softfood.session.RistoranteSession;
import it.softfood.session.TavoloSession;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class TavoloFacade implements ITavoloFacade {
	
	private static TavoloFacade singleton; 
	private TavoloSession tavoloSession = TavoloSession.getInstance();
	private RistoranteSession ristoranteSession = RistoranteSession.getInstance();
	
	public TavoloFacade() {
	}
	
	public synchronized static TavoloFacade getInstance() {
		if (singleton == null) {
			singleton = new TavoloFacade();
		}
		
		return singleton;
	}

	public Tavolo inserisciTavolo(User role,Tavolo tavolo) {
		if (tavolo != null)
			return tavoloSession.inserisciTavolo(tavolo);
		
		return null;
	}
	
	public boolean liberaTavolo(User role,Tavolo tavolo) {
		if (tavolo != null)
			return tavoloSession.modificaStatoTavolo(tavolo, false);
		
		return false;
	}
	
	public boolean occupaTavolo(User role,Tavolo tavolo) {
		if (tavolo != null)
			return tavoloSession.modificaStatoTavolo(tavolo, true);	
		
		return false;
	}
	
	public Tavolo selezionaTavolo(User role,Long id) {
		if (id != null)
			return tavoloSession.selezionaTavoloPerId(id);
		
		return null;
	}
	
	public List<Tavolo> selezionaTavoliLiberi(User role) {
		return tavoloSession.selezionaTavoliLiberi();
	}

    public List<Tavolo> selezionaTavoliOccupati(User role) {
		return tavoloSession.selezionaTavoliOccupati();
	}

    public List<Tavolo> selezionaTavoliNonAttivi(User role) {
        return tavoloSession.selezionaTavoliNonAttivi();
    }
    
	public boolean rimuoviTavolo(User role,Long id) {
		if (id != null)
			return tavoloSession.rimuoviTavolo(id);
		
		return false;
	}
    
    public Long occupaTavoli(User role,ArrayList<String> riferimenti) {
        try {
            if (riferimenti != null) {
                if (riferimenti.size() == 1) {
                    String riferimento = riferimenti.get(0);
                    if (riferimento != null) {
                        Tavolo tavolo = tavoloSession.selezionaTavoloPerRiferimento(riferimento, true);
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
                    }
                } else if (riferimenti.size() > 1) {
                    String riferimentoTavoli = null;
                    Integer numeroPosti = 0;
                    for (String riferimento : riferimenti) {
                        if (riferimento != null) {
                            Tavolo tavolo = tavoloSession.selezionaTavoloPerRiferimento(riferimento, true);
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
