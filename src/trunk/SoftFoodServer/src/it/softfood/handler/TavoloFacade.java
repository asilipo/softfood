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

	public Tavolo inserisciTavolo(User user, Tavolo tavolo) {
		if (user != null && tavolo != null)
			return tavoloSession.inserisciTavolo(tavolo);
		
		return null;
	}
	
	public boolean liberaTavolo(User user, Tavolo tavolo) {
		if (user != null && tavolo != null)
			return tavoloSession.modificaStatoTavolo(tavolo, false);
		
		return false;
	}
	
	public boolean occupaTavolo(User user, Tavolo tavolo) {
		if (user != null && tavolo != null)
			return tavoloSession.modificaStatoTavolo(tavolo, true);	
		
		return false;
	}
	
	public Tavolo selezionaTavolo(User user, Long id) {
		if (user != null && id != null)
			return tavoloSession.selezionaTavoloPerId(id);
		
		return null;
	}
	
	public List<Tavolo> selezionaTavoliLiberi(User user) {
		if (user != null)
			return tavoloSession.selezionaTavoliLiberi();
		
		return null;
	}

    public List<Tavolo> selezionaTavoliOccupati(User user) {
    	if (user != null)
    		return tavoloSession.selezionaTavoliOccupati();
    	
    	return null;
	}

    public List<Tavolo> selezionaTavoliNonAttivi(User user) {
    	if (user != null)
    		return tavoloSession.selezionaTavoliNonAttivi();
    	
    	return null;
    }
    
	public boolean rimuoviTavolo(User user, Long id) {
		if (user != null && id != null)
			return tavoloSession.rimuoviTavolo(id);
		
		return false;
	}
    
    public Long occupaTavoli(User user, ArrayList<String> riferimenti) {
        try {
            if (user != null && riferimenti != null) {
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
            Logger.getLogger(TavoloFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(TavoloFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

}
