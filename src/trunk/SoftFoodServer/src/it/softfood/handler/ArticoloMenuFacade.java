package it.softfood.handler;

import it.softfood.entity.Articolo;
import it.softfood.entity.Bevanda;
import it.softfood.entity.BevandaMagazzino;
import it.softfood.entity.Ingrediente;
import it.softfood.entity.IngredienteMagazzino;
import it.softfood.entity.IngredientePietanza;
import it.softfood.entity.Pietanza;
import it.softfood.enumeration.TipoPietanza;
import it.softfood.session.ArticoloSession;
import it.softfood.session.BevandaMagazzinoSession;
import it.softfood.session.BevandaSession;
import it.softfood.session.IngredienteMagazzinoSession;
import it.softfood.session.IngredientePietanzaSession;
import it.softfood.session.IngredienteSession;
import it.softfood.session.PietanzaSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class ArticoloMenuFacade  {

    
	private static ArticoloMenuFacade singleton;
	
    private ArticoloSession articoloSessionBean=ArticoloSession.getInstance();
    
    private PietanzaSession pietanzaSessionBean=PietanzaSession.getInstance();
 
    private BevandaSession bevandaSessionBean=BevandaSession.getInstance();
    
    private IngredienteSession ingredienteSession=IngredienteSession.getInstance();
    
    private IngredientePietanzaSession ingredientePietanzaSessionBeanRemote=IngredientePietanzaSession.getInstance();
    
    private IngredienteMagazzinoSession ingredienteMagazzinoSessionBeanRemote=IngredienteMagazzinoSession.getInstance();
   
    private BevandaMagazzinoSession bevandaMagazzinoSessionBeanRemote=BevandaMagazzinoSession.getInstance();

    
    public synchronized static ArticoloMenuFacade getInstance() {
		if (singleton == null) {
			singleton = new ArticoloMenuFacade();
		}
		return singleton;
	}
    
    public Articolo inserisciArticoloMenu(Articolo articolo) {
        if (articolo != null) {
            return articoloSessionBean.inserisciArticolo(articolo);
        }

        return null;
    }

    public Articolo selezionaArticoloMenuPerId(Long id) {
        if (id != null) {
            return articoloSessionBean.selezionaArticoloPerId(id);
        }

        return null;
    }

    public ArrayList<Pietanza> selezionaPietanzePerTipo(TipoPietanza tipoPietanza) {
        if (tipoPietanza != null) {
            return (ArrayList<Pietanza>) pietanzaSessionBean.selezionaPietanzePerTipo(tipoPietanza);
        }

        return null;
    }

    public ArrayList<Pietanza> selezionaPietanze() {
        return pietanzaSessionBean.selezionaPietanze();
    }

    public ArrayList<Pietanza> selezionaPietanzeDisponibili() {
        ArrayList<Pietanza> pietanze = (ArrayList<Pietanza>) pietanzaSessionBean.selezionaPietanze();
        ArrayList<Pietanza> pietanzeDisponibili = new ArrayList<Pietanza>();

        for (Pietanza pietanza : pietanze) {
            if (this.verificaIngredientiPietanza(pietanza) > 0) {
                pietanzeDisponibili.add(pietanza);
            }
        }

        return pietanzeDisponibili;
    }

    public ArrayList<Pietanza> selezionaPietanzeDisponibiliPerTipo(TipoPietanza tipoPietanza) {
        ArrayList<Pietanza> pietanze = (ArrayList<Pietanza>) pietanzaSessionBean.selezionaPietanzePerTipo(tipoPietanza);
        ArrayList<Pietanza> pietanzeDisponibili = new ArrayList<Pietanza>();

        if (pietanze != null) {
            for (Pietanza pietanza : pietanze) {
                if (this.verificaIngredientiPietanza(pietanza) > 0) {
                    pietanzeDisponibili.add(pietanza);
                }
            }
        }

        return pietanzeDisponibili;
    }

    public HashMap<Pietanza, Integer> selezionaDisponibilitaPietanze() {
        ArrayList<Pietanza> pietanze = (ArrayList<Pietanza>) pietanzaSessionBean.selezionaPietanze();
        HashMap<Pietanza, Integer> pietanzeDisponibili = new HashMap<Pietanza, Integer>();

        for (Pietanza pietanza : pietanze) {
            pietanzeDisponibili.put(pietanza, this.verificaIngredientiPietanza(pietanza));
        }

        return pietanzeDisponibili;
    }

    public Integer selezionaDisponibilitaPietanza(Long id) {
        if (id != null) {
            Pietanza pietanza = pietanzaSessionBean.selezionaPietanzaPerId(id);
            return this.verificaIngredientiPietanza(pietanza);
        }

        return null;
    }

    public Integer selezionaDisponibilitaBevanda(Long id) {
        if (id != null) {
            Bevanda bevanda = bevandaSessionBean.selezionaBevandaPerId(id);
            ArrayList<BevandaMagazzino> bevandeMagazzino = (ArrayList<BevandaMagazzino>) bevandaMagazzinoSessionBeanRemote.selezionaBevandeMagazzino();

            for (BevandaMagazzino bevandaMagazzino : bevandeMagazzino) {
                if (bevandaMagazzino.getArticolo().getId().equals(id)) {
                    return (bevandaMagazzino.getQuantita()) / (bevanda.getCapacita()).intValue();
                }
            }
        }

        return 0;
    }

    public HashMap<Pietanza, Integer> selezionaDisponibilitaPietanzePerTipo(TipoPietanza tipoPietanza) {
        ArrayList<Pietanza> pietanze = (ArrayList<Pietanza>) pietanzaSessionBean.selezionaPietanzePerTipo(tipoPietanza);
        HashMap<Pietanza, Integer> pietanzeDisponibili = new HashMap<Pietanza, Integer>();

        for (Pietanza pietanza : pietanze) {
            pietanzeDisponibili.put(pietanza, this.verificaIngredientiPietanza(pietanza));
        }

        return pietanzeDisponibili;
    }

    private Integer verificaIngredientiPietanza(Pietanza pietanza) {
        ArrayList<IngredientePietanza> ingredientiPietanze = (ArrayList<IngredientePietanza>) ingredientePietanzaSessionBeanRemote.selezionaIngredientiPietanze();
        ArrayList<IngredienteMagazzino> ingredientiMagazzino = (ArrayList<IngredienteMagazzino>) ingredienteMagazzinoSessionBeanRemote.selezionaIngredientiMagazzino();
        Date data = new Date(System.currentTimeMillis());

        int contatore = 0;
        int disponibilita = 0;
        int disponibilitaMinima = 1000;
        int numeroIngredienti = 0;
        for (IngredientePietanza ingredientePietanza : ingredientiPietanze) {
            if (ingredientePietanza.getId().getPietanza().equals(pietanza.getId())) {
                numeroIngredienti++;
                Ingrediente ingrediente = ingredienteSession.selezionaIngredientePerId(ingredientePietanza.getId().getIngrediente());
                
                for (IngredienteMagazzino ingredienteMagazzino : ingredientiMagazzino) {
                    if (ingredienteMagazzino.getIngrediente().getId().equals(ingrediente.getId())) {
                        contatore++;
                        if (ingredienteMagazzino.getQuantita() >= ingredientePietanza.getQuantita()
                                && ingrediente.getScadenza().after(data)) {
                            try {
                                disponibilita = ingredienteMagazzino.getQuantita() / ingredientePietanza.getQuantita();
                            } catch (NumberFormatException nfe) {
                                disponibilita = 0;
                            }
                        }

                        if (disponibilita < disponibilitaMinima)
                            disponibilitaMinima = disponibilita;
                        disponibilita = 0;
                    }
                }
            }
        }

        if (contatore == numeroIngredienti && contatore > 0)
            return disponibilitaMinima;

        return 0;
    }

    public ArrayList<Bevanda> selezionaBevande() {
        return bevandaSessionBean.selezionaBevande();
    }

    public ArrayList<Bevanda> selezionaBevandeDisponibili() {
        ArrayList<BevandaMagazzino> bevandeMagazzino = (ArrayList<BevandaMagazzino>) bevandaMagazzinoSessionBeanRemote.selezionaBevandeMagazzinoPerQuantita(1);

        if (bevandeMagazzino != null && bevandeMagazzino.size() > 0) {
            ArrayList<Bevanda> bevande = new ArrayList<Bevanda>();
            for (BevandaMagazzino bevandaMagazzino : bevandeMagazzino)
                bevande.add((Bevanda) bevandaMagazzino.getArticolo());

            return bevande;
        }

        return null;
    }

    public boolean rimuoviArticoloMenu(Long id) {
        if (id != null) {
            return articoloSessionBean.rimuoviArticolo(id);
        }

        return false;
    }

    public ArrayList<Ingrediente> selezionaIngredientiPietanza(Long id) {
        if (id != null) {
            ArrayList<Ingrediente> ingredienti = new ArrayList<Ingrediente>();
            ArrayList<IngredientePietanza> ingredientiPietanze = (ArrayList<IngredientePietanza>) ingredientePietanzaSessionBeanRemote.selezionaIngredientiPietanze();

            if (ingredientiPietanze != null) {
                for (IngredientePietanza ingredientePietanza : ingredientiPietanze) {
                    if (ingredientePietanza.getId().getPietanza().equals(id)) {
                        ingredienti.add(ingredienteSession.selezionaIngredientePerId(ingredientePietanza.getId().getIngrediente()));
                    }
                }
            }

            return ingredienti;
        }

        return null;
    }
}
