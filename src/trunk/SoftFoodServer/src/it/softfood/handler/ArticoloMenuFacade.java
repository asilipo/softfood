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
    
    public Pietanza inserisciPietanzaMenu(User user, Pietanza pietanza) {
        if (user != null && pietanza != null && pietanza.getNome() != null && pietanza.getTipoPietanza() != null && !pietanza.getNome().equals("")) {
        	
        	pietanza.setTipoArticolo("Pietanza");
    		return pietanzaSession.inserisciPietanza(pietanza);
        }
        return null;
    }
    
    public Bevanda inserisciBevandaMenu(User user, Bevanda bevanda) {
        if (user != null && bevanda != null && bevanda.getNome() != null && bevanda.getCapacita() != null && bevanda.getTipoArticolo() != null && !bevanda.getNome().equals("") && !bevanda.getTipoArticolo().equals("") && bevanda.getCapacita()>=0)
    		return bevandaSession.inserisciBevanda(bevanda);

        return null;
    }
    
    public Ingrediente inserisciIngrediente(User user, Ingrediente ingrediente) {
        if (user != null && ingrediente != null && ingrediente.getNome()!=null && ingrediente.getScadenza()!=null && !ingrediente.getNome().equals("")) {
            return ingredienteSession.inserisciIngrediente(ingrediente);
        }

        return null;
    }
    
    public HashSet<IngredientePietanza> inserisciIngredientiPietanze(User user, HashSet<IngredientePietanza> ingredientiPietanza) {
    	if (user != null) {
	    	HashSet<IngredientePietanza> ip = new HashSet<IngredientePietanza>();
	    	if (ingredientiPietanza != null) {
	        	try {
		        	for (IngredientePietanza ingredientePietanza : ingredientiPietanza)
		        		if (ingredientePietanza != null) {
		        			ingredientePietanza = ingredientePietanzaSession.inserisciIngredientePietanza(ingredientePietanza);
		        			ip.add(ingredientePietanza);
		        		}
	        	} catch (Exception e) {
	        		System.err.println("ArticoloMenuFacade#inserisciIngredientiPietanze");
	        		return null;
	        	}
	        }
	
	        return ip;
    	}
    	
    	return null;
    }
    
    public BevandaMagazzino inserisciBevandaMagazzino(User user, Long id, Integer quantita) {
        if (user != null && id != null && quantita!=null && quantita>=0) {
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
    
    public IngredienteMagazzino inserisciIngredienteMagazzino(User user, Long id, Integer quantita) {
        if (user != null && id != null) {
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

    public Articolo selezionaArticoloMenuPerId(User user, Long id) {
        if (user != null && id != null) {
            return articoloSession.selezionaArticoloPerId(id);
        }

        return null;
    }
    
    public Ingrediente selezionaIngredientePerNome(User user, String nome) {
        if (user != null && nome != null) {
        	ArrayList<Ingrediente> ingredienti = (ArrayList<Ingrediente>) ingredienteSession.selezionaIngredientePerNome(nome);
            if (ingredienti != null && ingredienti.size() > 0)
            	return (Ingrediente) ingredienti.get(0);
        }

        return null;
    }
    
    public IngredientePietanza inserisciIngredientePietanza(User user, IngredientePietanza ingrediente){
    	if(user != null && ingrediente!=null && ingrediente.getArticolo()!=null && ingrediente.getIngrediente()!=null && ingrediente.getQuantita()>=0){
    		ingrediente=ingredientePietanzaSession.inserisciIngredientePietanza(ingrediente);
    		return ingrediente;
    	}
    	
    	return null;
    }

    public ArrayList<Pietanza> selezionaPietanzePerTipo(User user, TipoPietanza tipoPietanza) {
        if (user != null && tipoPietanza != null) {
        	ArrayList<Pietanza> pietanze = (ArrayList<Pietanza>) pietanzaSession.selezionaPietanzePerTipo(tipoPietanza);
        	return pietanze;
        }

        return null;
    }

    public ArrayList<Pietanza> selezionaPietanze(User user) {
    	if (user != null) {
	        ArrayList<Pietanza> pietanze = pietanzaSession.selezionaPietanze();
	        return pietanze;
    	}
    	
    	return null;
    }

    public ArrayList<Pietanza> selezionaPietanzeDisponibili(User user) {
    	if (user != null) {
	        ArrayList<Pietanza> pietanze = (ArrayList<Pietanza>) pietanzaSession.selezionaPietanze();
	        ArrayList<Pietanza> pietanzeDisponibili = new ArrayList<Pietanza>();
	
	        for (Pietanza pietanza : pietanze) {
	            if (this.verificaIngredientiPietanza(user, pietanza) > 0) {
	                pietanzeDisponibili.add(pietanza);
	            }
	        }
	
	        return pietanzeDisponibili;
    	}
    	
    	return null;
    }

    public ArrayList<Pietanza> selezionaPietanzeDisponibiliPerTipo(User user, TipoPietanza tipoPietanza) {
        if (user != null && tipoPietanza != null) {
	    	ArrayList<Pietanza> pietanze = (ArrayList<Pietanza>) pietanzaSession.selezionaPietanzePerTipo(tipoPietanza);
	        ArrayList<Pietanza> pietanzeDisponibili = new ArrayList<Pietanza>();
	        
	        if (pietanze != null) {
	            for (Pietanza pietanza : pietanze) {
	            	int disponibilita = this.verificaIngredientiPietanza(user, (Pietanza)pietanza);
	                if (disponibilita > 0) {
	                    pietanzeDisponibili.add(pietanza);
	                }
	            }
	        }
	
	        return pietanzeDisponibili;
        }
        
        return null;
    }

    public HashMap<Pietanza, Integer> selezionaDisponibilitaPietanze(User user) {
    	if (user != null) {
	        ArrayList<Pietanza> pietanze = (ArrayList<Pietanza>) pietanzaSession.selezionaPietanze();
	        HashMap<Pietanza, Integer> pietanzeDisponibili = new HashMap<Pietanza, Integer>();
	
	        for (Pietanza pietanza : pietanze) {
	            pietanzeDisponibili.put(pietanza, this.verificaIngredientiPietanza(user, pietanza));
	        }
	        
	        return pietanzeDisponibili;
    	}

    	return null;
    }

    public Integer selezionaDisponibilitaPietanza(User user, Long id) {
        if (user != null && id != null) {
            Pietanza pietanza = pietanzaSession.selezionaPietanzaPerId(id);
            return this.verificaIngredientiPietanza(user, pietanza);
        }

        return 0;
    }

    public Integer selezionaDisponibilitaBevanda(User user, Long id) {
        if (user != null && id != null) {
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

    public HashMap<Pietanza, Integer> selezionaDisponibilitaPietanzePerTipo(User user, TipoPietanza tipoPietanza) {
        if (user != null && tipoPietanza != null) {
	    	ArrayList<Pietanza> pietanze = (ArrayList<Pietanza>) pietanzaSession.selezionaPietanzePerTipo(tipoPietanza);
	        
	        HashMap<Pietanza, Integer> pietanzeDisponibili = new HashMap<Pietanza, Integer>();
	
	        for (Pietanza pietanza : pietanze) {
	            pietanzeDisponibili.put(pietanza, this.verificaIngredientiPietanza(user, pietanza));
	        }
	
	        return pietanzeDisponibili;
    	}
    	
    	return null;
    }

    public Integer verificaIngredientiPietanza(User user, Pietanza pietanza) {
    	if (user != null && pietanza != null) {
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
    	}
    	
        return 0;
    }

    public ArrayList<Bevanda> selezionaBevande(User user) {
    	if (user != null) {
    		ArrayList<Bevanda> bevande = bevandaSession.selezionaBevande();
        	return bevande;
    	}
    	
    	return null;
    }
    
/*    public boolean updateIndredientiPietanza(User user, Long idPietanza, HashSet<IngredientePietanza> ingredientiPietanza) {
        if (user != null && ingredientiPietanza != null && ingredientiPietanza.size() > 0) {
        	
        	try {
        		for (IngredientePietanza ingredientePietanza : ingredientiPietanza)
        			ingredientePietanzaSession.update(ingredientePietanza);
        		
        		return true;
        	} catch (Exception e) {
        		e.printStackTrace();
        		System.err.println("ArticoloMenuFacade#updateIndredientiPietanza");
        		return false;
        	}
        }
        	
        return false;
    }*/
    
    public boolean updateIndredientiPietanza(User user, Long idPietanza, HashSet<IngredientePietanza> ingredientiPietanza) {
        if (user != null && ingredientiPietanza != null && ingredientiPietanza.size() > 0) {
        	ArrayList<Ingrediente> ingredienti = new ArrayList<Ingrediente>();
            ArrayList<IngredientePietanza> ingredientiPietanze = (ArrayList<IngredientePietanza>) ingredientePietanzaSession.selezionaIngredientiPietanze();

            if (ingredientiPietanze != null) {
                for (IngredientePietanza ingredientePietanza : ingredientiPietanze) {
                    if (ingredientePietanza.getId().getPietanza().equals(idPietanza)) {
                        ingredientePietanzaSession.rimuoviIngredientePietanza(ingredientePietanza.getId());
                    }
                }
            }

        	try {
        		for (IngredientePietanza ingredientePietanza : ingredientiPietanza)
        			ingredientePietanzaSession.inserisciIngredientePietanza(ingredientePietanza);
        		
        		return true;
        	} catch (Exception e) {
        		e.printStackTrace();
        		System.err.println("ArticoloMenuFacade#updateIndredientiPietanza");
        		return false;
        	}
        }
        	
        return false;
    }
    
    public boolean updateBevanda(User user, Bevanda bevanda) {
        if (user != null && bevanda != null && bevanda.getNome()!=null && bevanda.getCapacita()!=null && bevanda.getTipoArticolo()!=null && !bevanda.getNome().equals("") && !bevanda.getTipoArticolo().equals("") && bevanda.getCapacita()>=0) {
    		bevandaSession.update(bevanda);
        	return true;
        }
        	
        return false;
    }
    
    public boolean updatePietanza(User user, Pietanza pietanza) {
        if (user != null && pietanza != null && pietanza.getNome()!=null && pietanza.getTipoPietanza()!=null && !pietanza.getNome().equals("")) {
    		pietanzaSession.update(pietanza);
        	return true;
        }
        	
        return false;
    }
    
    public boolean updateIngrediente(User user, Ingrediente ingrediente) {
        if (user != null && ingrediente != null && ingrediente.getNome()!=null && ingrediente.getScadenza()!=null && !ingrediente.getNome().equals("")) {
    		ingredienteSession.update(ingrediente);
        	return true;
        }
        	
        return false;
    }
    
    
    public boolean updateIngredienteMagazzino(User user, IngredienteMagazzino ingredienteMagazzino) {
        if (user != null && ingredienteMagazzino != null) {
        	ingredienteMagazzinoSession.update(ingredienteMagazzino);
        	return true;
        }
        	
        return false;
    }
    
    public boolean updateIngredientePietanza(User user, IngredientePietanza ingredientePietanza) {
        if (user != null && ingredientePietanza != null) {
        	ingredientePietanzaSession.update(ingredientePietanza);
        	return true;
        }
        	
        return false;
    }

    public boolean updateBevandaMagazzino(User user, BevandaMagazzino bevandaMagazzino) {
        if (user != null && bevandaMagazzino != null) {
        	bevandaMagazzinoSession.update(bevandaMagazzino);
        	return true;
        }
        	
        return false;
    }
    
    public ArrayList<Bevanda> selezionaBevandeDisponibili(User user) {
        ArrayList<BevandaMagazzino> bevandeMagazzino = (ArrayList<BevandaMagazzino>) bevandaMagazzinoSession.selezionaBevandeMagazzinoPerQuantita(1);

        if (user != null) {
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
        }
        
        return null;
    }

    public boolean rimuoviPietanzaMenu(User user, Long id) {
    	if (user != null) {
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
    	
    	return false;
    }
    
    public boolean rimuoviIngredienteMagazzino(User user, Long id) {
    	if (user != null) {
    		return ingredienteMagazzinoSession.rimuoviIngredienteMagazzino(id);
    	}
    	
    	return false;
    }
    
    public boolean rimuoviIngrediente(User user, Long id) {
    	if (user != null) {
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
    	
    	return false;
    }
    
    public boolean rimuoviBevandaMenu(User user, Long id) {
    	if (user != null) {
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
    	
    	return false;
    }

    public ArrayList<Ingrediente> selezionaIngredientiPietanza(User user, Long id) {
        if (user != null && id != null) {
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
    
    public ArrayList<Ingrediente> selezionaIngredienti(User user) {
    	if (user != null) {
	    	ArrayList<Ingrediente> ingredienti = (ArrayList<Ingrediente>) ingredienteSession.selezionaIngredienti();
	    	return ingredienti;
    	}
    	
    	return null;
    }
    
}
