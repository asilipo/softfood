package it.softfood.handler;

import it.softfood.entity.Articolo;
import it.softfood.entity.Bevanda;
import it.softfood.entity.BevandaMagazzino;
import it.softfood.entity.Ingrediente;
import it.softfood.entity.IngredienteMagazzino;
import it.softfood.entity.IngredientePietanza;
import it.softfood.entity.Pietanza;
import it.softfood.entity.User;
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
    
    public Articolo inserisciArticoloMenu(User role,Articolo articolo) {
        if (articolo != null) {
            return articoloSessionBean.inserisciArticolo(articolo);
        }

        return null;
    }

    public Articolo selezionaArticoloMenuPerId(User role,Long id) {
        if (id != null) {
            return articoloSessionBean.selezionaArticoloPerId(id);
        }

        return null;
    }

    public ArrayList<Pietanza> selezionaPietanzePerTipo(User role,TipoPietanza tipoPietanza) {
        if (tipoPietanza != null) {
        	ArrayList<Pietanza> pietanze = (ArrayList<Pietanza>) pietanzaSessionBean.selezionaPietanzePerTipo(tipoPietanza);
        	return pietanze;
        }

        return null;
    }

    public ArrayList<Pietanza> selezionaPietanze(User role) {
        return pietanzaSessionBean.selezionaPietanze();
    }

    public ArrayList<Pietanza> selezionaPietanzeDisponibili(User role) {
        ArrayList<Pietanza> pietanze = (ArrayList<Pietanza>) pietanzaSessionBean.selezionaPietanze();
        ArrayList<Pietanza> pietanzeDisponibili = new ArrayList<Pietanza>();

        for (Pietanza pietanza : pietanze) {
            if (this.verificaIngredientiPietanza(role,pietanza) > 0) {
                pietanzeDisponibili.add(pietanza);
            }
        }

        return pietanzeDisponibili;
    }

    public ArrayList<Pietanza> selezionaPietanzeDisponibiliPerTipo(User role,TipoPietanza tipoPietanza) {
        ArrayList<Pietanza> pietanze = (ArrayList<Pietanza>) pietanzaSessionBean.selezionaPietanzePerTipo(tipoPietanza);
        ArrayList<Pietanza> pietanzeDisponibili = new ArrayList<Pietanza>();
        
        if (pietanze != null) {
            for (Pietanza pietanza : pietanze) {
            	int disponibilita = this.verificaIngredientiPietanza(role,(Pietanza)pietanza);
                if (disponibilita > 0) {
                    pietanzeDisponibili.add(pietanza);
                }
            }
        }

        return pietanzeDisponibili;
    }

    public HashMap<Pietanza, Integer> selezionaDisponibilitaPietanze(User role) {
        ArrayList<Pietanza> pietanze = (ArrayList<Pietanza>) pietanzaSessionBean.selezionaPietanze();
        HashMap<Pietanza, Integer> pietanzeDisponibili = new HashMap<Pietanza, Integer>();

        for (Pietanza pietanza : pietanze) {
            pietanzeDisponibili.put(pietanza, this.verificaIngredientiPietanza(role,pietanza));
        }

        return pietanzeDisponibili;
    }

    public Integer selezionaDisponibilitaPietanza(User role,Long id) {
        if (id != null) {
            Pietanza pietanza = pietanzaSessionBean.selezionaPietanzaPerId(id);
            return this.verificaIngredientiPietanza(role,pietanza);
        }

        return null;
    }

    public Integer selezionaDisponibilitaBevanda(User role,Long id) {
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

    public HashMap<Pietanza, Integer> selezionaDisponibilitaPietanzePerTipo(User role,TipoPietanza tipoPietanza) {
        ArrayList<Pietanza> pietanze = (ArrayList<Pietanza>) pietanzaSessionBean.selezionaPietanzePerTipo(tipoPietanza);
        
        HashMap<Pietanza, Integer> pietanzeDisponibili = new HashMap<Pietanza, Integer>();

        for (Pietanza pietanza : pietanze) {
            pietanzeDisponibili.put(pietanza, this.verificaIngredientiPietanza(role,pietanza));
        }

        return pietanzeDisponibili;
    }

    public Integer verificaIngredientiPietanza(User role,Pietanza pietanza) {
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
                        
                        if (ingredienteMagazzino.getQuantita() >= ingredientePietanza.getQuantita() && ingrediente.getScadenza().after(data)) {
                            try {
                                disponibilita = ingredienteMagazzino.getQuantita() / ingredientePietanza.getQuantita();
                            } catch (NumberFormatException nfe) {
                                disponibilita = 0;
                            }
                        }

                        if (disponibilita < disponibilitaMinima)
                            disponibilitaMinima = disponibilita;
                        disponibilita = 0;
                        System.out.println("disponibilita minima " + disponibilitaMinima);
                        
                    }
                }
            }
        }
		
        if (contatore == numeroIngredienti && contatore > 0)
            return disponibilitaMinima;

        return 0;
    }

    public ArrayList<Bevanda> selezionaBevande(User role) {
        return bevandaSessionBean.selezionaBevande();
    }

    public ArrayList<Bevanda> selezionaBevandeDisponibili(User role) {
        ArrayList<BevandaMagazzino> bevandeMagazzino = (ArrayList<BevandaMagazzino>) bevandaMagazzinoSessionBeanRemote.selezionaBevandeMagazzinoPerQuantita(1);

        if (bevandeMagazzino != null && bevandeMagazzino.size() > 0) {
            ArrayList<Bevanda> bevande = new ArrayList<Bevanda>();
			for (BevandaMagazzino bevandaMagazzino : bevandeMagazzino) {
            	Articolo articolo = bevandaMagazzino.getArticolo();
            	Bevanda bevanda = new Bevanda();
            	bevanda.setCapacita(articolo.getCapacita());
            	bevanda.setDescrizione(articolo.getDescrizione());
            	bevanda.setId(articolo.getId());
            	bevanda.setListino(articolo.getListino());
            	bevanda.setNome(articolo.getNome());
            	bevanda.setTipoArticolo(articolo.getTipoArticolo());
            	bevanda.setBevandaMagazzinos(articolo.getBevandaMagazzinos());
            	bevanda.setLineaOrdinaziones(articolo.getLineaOrdinaziones());
            	
                bevande.add(bevanda);
			}
			
            return bevande;
        }

        return null;
    }

    public boolean rimuoviArticoloMenu(User role,Long id) {
        if (id != null) {
            return articoloSessionBean.rimuoviArticolo(id);
        }

        return false;
    }

    public ArrayList<Ingrediente> selezionaIngredientiPietanza(User role,Long id) {
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
