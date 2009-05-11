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
import it.softfood.session.MagazzinoSession;
import it.softfood.session.PietanzaSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class ArticoloMenuFacade  {

	private static ArticoloMenuFacade singleton;
	
    private ArticoloSession articoloSession = ArticoloSession.getInstance();
    
    private MagazzinoSession magazzinoSession = MagazzinoSession.getInstance();
    
    private PietanzaSession pietanzaSession = PietanzaSession.getInstance();
 
    private BevandaSession bevandaSession = BevandaSession.getInstance();
    
    private BevandaMagazzinoSession bevandaMagazzinoSession = BevandaMagazzinoSession.getInstance();
    
    private IngredienteSession ingredienteSession = IngredienteSession.getInstance();
    
    private IngredientePietanzaSession ingredientePietanzaSession = IngredientePietanzaSession.getInstance();
    
    private IngredienteMagazzinoSession ingredienteMagazzinoSession = IngredienteMagazzinoSession.getInstance();
    
    public synchronized static ArticoloMenuFacade getInstance() {
		if (singleton == null) {
			singleton = new ArticoloMenuFacade();
		}
		return singleton;
	}
    
    public Pietanza inserisciPietanzaMenu(User role, Pietanza pietanza) {
        if (pietanza != null)
    		return pietanzaSession.inserisciPietanza(pietanza);

        return null;
    }
    
    public Bevanda inserisciBevandaMenu(User role, Bevanda bevanda) {
        if (bevanda != null)
    		return bevandaSession.inserisciBevanda(bevanda);

        return null;
    }
    
    public Ingrediente inserisciIngrediente(User role, Ingrediente ingrediente) {
        if (ingrediente != null) {
            return ingredienteSession.inserisciIngrediente(ingrediente);
        }

        return null;
    }
    
    public HashSet<IngredientePietanza> inserisciIngredientiPietanze(User role, HashSet<IngredientePietanza> ingredientiPietanza) {
    	HashSet<IngredientePietanza> ip = new HashSet<IngredientePietanza>();
    	if (ingredientiPietanza != null) {
        	try {
	        	for (IngredientePietanza ingredientePietanza : ingredientiPietanza)
	        		if (ingredientePietanza != null) {
	        			ingredientePietanza = ingredientePietanzaSession.inserisciIngredientePietanza(ingredientePietanza);
	        			ip.add(ingredientePietanza);
	        		}
        	} catch (Exception e) {
        		e.printStackTrace();
        		System.out.println("ArticoloMenuFacade#inserisciIngredientiPietanze");
        		return null;
        	}
        }

        return ip;
    }
    
    public BevandaMagazzino inserisciBevandaMagazzino(User role, Long id, Integer quantita) {
        if (id != null) {
        	BevandaMagazzino bevandaMagazzino = new BevandaMagazzino();
        	bevandaMagazzino.setArticolo(articoloSession.selezionaArticoloPerId(id));
        	bevandaMagazzino.setMagazzino(magazzinoSession.selezionaMagazzinoPerId(0L));
        	if (quantita != null)
        		bevandaMagazzino.setQuantita(quantita);
        	else
        		bevandaMagazzino.setQuantita(0);
        	
            return bevandaMagazzinoSession.inserisciBevandaMagazzino(bevandaMagazzino);
        }

        return null;
    }
    
    public IngredienteMagazzino inserisciIngredienteMagazzino(User role, Long id, Integer quantita) {
        if (id != null) {
        	IngredienteMagazzino ingredienteMagazzino = new IngredienteMagazzino();
        	ingredienteMagazzino.setIngrediente(ingredienteSession.selezionaIngredientePerId(id));
        	ingredienteMagazzino.setMagazzino(magazzinoSession.selezionaMagazzinoPerId(0L));
        	if (quantita != null)
        		ingredienteMagazzino.setQuantita(quantita);
        	else
        		ingredienteMagazzino.setQuantita(0);
        	
            return ingredienteMagazzinoSession.inserisciIngredienteMagazzino(ingredienteMagazzino);
        }

        return null;
    }

    public Articolo selezionaArticoloMenuPerId(User role,Long id) {
        if (id != null) {
            return articoloSession.selezionaArticoloPerId(id);
        }

        return null;
    }
    
    public Ingrediente selezionaIngredientePerNome(User role,String nome) {
        if (nome != null) {
            return (Ingrediente) ingredienteSession.selezionaIngredientePerNome(nome).get(0);
        }

        return null;
    }
    
    public IngredientePietanza inserisciIngredientePietanza(User role, IngredientePietanza ingrediente){
    	if(ingrediente!=null){
    		ingrediente=ingredientePietanzaSession.inserisciIngredientePietanza(ingrediente);
    		return ingrediente;
    	}
    	return null;
    }

    public ArrayList<Pietanza> selezionaPietanzePerTipo(User role,TipoPietanza tipoPietanza) {
        if (tipoPietanza != null) {
        	ArrayList<Pietanza> pietanze = (ArrayList<Pietanza>) pietanzaSession.selezionaPietanzePerTipo(tipoPietanza);
        	return pietanze;
        }

        return null;
    }

    public ArrayList<Pietanza> selezionaPietanze(User role) {
        ArrayList<Pietanza> pietanze = pietanzaSession.selezionaPietanze();
        return pietanze;
    }

    public ArrayList<Pietanza> selezionaPietanzeDisponibili(User role) {
        ArrayList<Pietanza> pietanze = (ArrayList<Pietanza>) pietanzaSession.selezionaPietanze();
        ArrayList<Pietanza> pietanzeDisponibili = new ArrayList<Pietanza>();

        for (Pietanza pietanza : pietanze) {
            if (this.verificaIngredientiPietanza(role,pietanza) > 0) {
                pietanzeDisponibili.add(pietanza);
            }
        }

        return pietanzeDisponibili;
    }

    public ArrayList<Pietanza> selezionaPietanzeDisponibiliPerTipo(User role,TipoPietanza tipoPietanza) {
        ArrayList<Pietanza> pietanze = (ArrayList<Pietanza>) pietanzaSession.selezionaPietanzePerTipo(tipoPietanza);
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
        ArrayList<Pietanza> pietanze = (ArrayList<Pietanza>) pietanzaSession.selezionaPietanze();
        HashMap<Pietanza, Integer> pietanzeDisponibili = new HashMap<Pietanza, Integer>();

        for (Pietanza pietanza : pietanze) {
            pietanzeDisponibili.put(pietanza, this.verificaIngredientiPietanza(role,pietanza));
        }

        return pietanzeDisponibili;
    }

    public Integer selezionaDisponibilitaPietanza(User role,Long id) {
        if (id != null) {
            Pietanza pietanza = pietanzaSession.selezionaPietanzaPerId(id);
            return this.verificaIngredientiPietanza(role,pietanza);
        }

        return null;
    }

    public Integer selezionaDisponibilitaBevanda(User role,Long id) {
        if (id != null) {
            Bevanda bevanda = bevandaSession.selezionaBevandaPerId(id);
            ArrayList<BevandaMagazzino> bevandeMagazzino = (ArrayList<BevandaMagazzino>) bevandaMagazzinoSession.selezionaBevandeMagazzino();

            for (BevandaMagazzino bevandaMagazzino : bevandeMagazzino) {
                if (bevandaMagazzino.getArticolo().getId().equals(id)) {
                    return (bevandaMagazzino.getQuantita()) / (bevanda.getCapacita()).intValue();
                }
            }
        }

        return 0;
    }

    public HashMap<Pietanza, Integer> selezionaDisponibilitaPietanzePerTipo(User role,TipoPietanza tipoPietanza) {
        ArrayList<Pietanza> pietanze = (ArrayList<Pietanza>) pietanzaSession.selezionaPietanzePerTipo(tipoPietanza);
        
        HashMap<Pietanza, Integer> pietanzeDisponibili = new HashMap<Pietanza, Integer>();

        for (Pietanza pietanza : pietanze) {
            pietanzeDisponibili.put(pietanza, this.verificaIngredientiPietanza(role,pietanza));
        }

        return pietanzeDisponibili;
    }

    public Integer verificaIngredientiPietanza(User role,Pietanza pietanza) {
        ArrayList<IngredientePietanza> ingredientiPietanze = (ArrayList<IngredientePietanza>) ingredientePietanzaSession.selezionaIngredientiPietanze();
        ArrayList<IngredienteMagazzino> ingredientiMagazzino = (ArrayList<IngredienteMagazzino>) ingredienteMagazzinoSession.selezionaIngredientiMagazzino();
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
                            } catch (Exception nfe) {
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

    public ArrayList<Bevanda> selezionaBevande(User role) {
        ArrayList<Bevanda> bevande = bevandaSession.selezionaBevande();
        return bevande;
    }
    
    public boolean updateIndredientiPietanza(User role, HashSet<IngredientePietanza> ingredientiPietanza) {
        if (ingredientiPietanza != null) {
        	try {
        		for (IngredientePietanza ingredientePietanza : ingredientiPietanza)
        			ingredientePietanzaSession.update(ingredientePietanza);
        		
        		return true;
        	} catch (Exception e) {
        		System.err.println("ArticoloMenuFacade#updateIndredientiPietanza");
        		return false;
        	}
        }
        	
        return false;
    }
    
    public boolean updateBevanda(User role, Bevanda bevanda) {
        if (bevanda != null) {
    		bevandaSession.update(bevanda);
        		
        	return true;
        }
        	
        return false;
    }
    
    public boolean updatePietanza(User role, Pietanza pietanza) {
        if (pietanza != null) {
    		pietanzaSession.update(pietanza);
        		
        	return true;
        }
        	
        return false;
    }
    
    public boolean updateIngrediente(User role, Ingrediente ingrediente) {
        if (ingrediente != null) {
    		ingredienteSession.update(ingrediente);
        	return true;
        }
        	
        return false;
    }
    
    
    public boolean updateIngredienteMagazzino(User role, IngredienteMagazzino ingredienteMagazzino) {
        if (ingredienteMagazzino != null) {
        	ingredienteMagazzinoSession.update(ingredienteMagazzino);
        		
        	return true;
        }
        	
        return false;
    }
    
    public boolean updateIngredientePietanza(User role, IngredientePietanza ingredientePietanza) {
        if (ingredientePietanza != null) {
        	ingredientePietanzaSession.update(ingredientePietanza);
        		
        	return true;
        }
        	
        return false;
    }

    public boolean updateBevandaMagazzino(User role, BevandaMagazzino bevandaMagazzino) {
        if (bevandaMagazzino != null) {
        	bevandaMagazzinoSession.update(bevandaMagazzino);
        		
        	return true;
        }
        	
        return false;
    }
    
    public ArrayList<Bevanda> selezionaBevandeDisponibili(User role) {
        ArrayList<BevandaMagazzino> bevandeMagazzino = (ArrayList<BevandaMagazzino>) bevandaMagazzinoSession.selezionaBevandeMagazzinoPerQuantita(1);

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

    public boolean rimuoviPietanzaMenu(User role,Long id) {
    	try {
	        if (id != null) {
	        	Pietanza pietanza = pietanzaSession.selezionaPietanzaPerId(id);
	        	Set<IngredientePietanza> ingredientiPietanza = pietanza.getIngredientePietanzas();
	            if (ingredientiPietanza != null && ingredientiPietanza.size() > 0) {
	            	for (IngredientePietanza ingredientePietanza : ingredientiPietanza){
	            		ingredientePietanzaSession.rimuoviIngredientePietanza(ingredientePietanza.getId());
	            	}
	            }
	        	return pietanzaSession.rimuoviPietanza(id);
	        }
	
	        return false;
    	} catch (Exception e) {
    		return false;
    	}
    }
    
    public boolean rimuoviIngrediente(User role,Long id) {
    	try {
	        if (id != null) {
	        	Ingrediente ingrediente = ingredienteSession.selezionaIngredientePerId(id);
	        	Set<IngredienteMagazzino> ingredientiMagazzino = ingrediente.getIngredienteMagazzinos();
	            if (ingredientiMagazzino != null && ingredientiMagazzino.size() > 0) {
	            	for (IngredienteMagazzino ingredienteMagazzino : ingredientiMagazzino) 
	            		ingredienteMagazzinoSession.rimuoviIngredienteMagazzino(ingredienteMagazzino.getId());
	            }
	            	
	        	return ingredienteSession.rimuoviIngrediente(id);
	        }
	        return false;
    	} catch (Exception e) {
    		return false;
    	}
    }
    
    public boolean rimuoviBevandaMenu(User role, Long id) {
    	try {
	        if (id != null) {
	        	Bevanda bevanda = bevandaSession.selezionaBevandaPerId(id);
	        	Set<BevandaMagazzino> bevandeMagazzino = bevanda.getBevandaMagazzinos();
	        	if (bevandeMagazzino != null && bevandeMagazzino.size() > 0) {
		        	BevandaMagazzino bevandaMagazzino = bevandaMagazzinoSession.selezionaBevandaMagazzinoPerId(((BevandaMagazzino)bevandeMagazzino.toArray()[0]).getId());
		        	if (bevandaMagazzino != null)
		        		bevandaMagazzinoSession.rimuoviBevandaMagazzino(bevandaMagazzino.getId());
	        	}
	        	return bevandaSession.rimuoviBevanda(id);
	        }

	        return false;
    	} catch (Exception e) {
    		return false;
    	} 
    }

    public ArrayList<Ingrediente> selezionaIngredientiPietanza(User role,Long id) {
        if (id != null) {
            ArrayList<Ingrediente> ingredienti = new ArrayList<Ingrediente>();
            ArrayList<IngredientePietanza> ingredientiPietanze = (ArrayList<IngredientePietanza>) ingredientePietanzaSession.selezionaIngredientiPietanze();

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
    
    public ArrayList<Ingrediente> selezionaIngredienti(User role) {
    	ArrayList<Ingrediente> ingredienti = (ArrayList<Ingrediente>) ingredienteSession.selezionaIngredienti();
    	return ingredienti;
    }
    
}
