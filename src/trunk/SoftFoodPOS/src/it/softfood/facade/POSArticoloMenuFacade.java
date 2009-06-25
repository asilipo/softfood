package it.softfood.facade;

import it.softfood.entity.Bevanda;
import it.softfood.entity.BevandaMagazzino;
import it.softfood.entity.Ingrediente;
import it.softfood.entity.IngredienteMagazzino;
import it.softfood.entity.IngredientePietanza;
import it.softfood.entity.Pietanza;
import it.softfood.entity.User;
import it.softfood.handler.IArticoloMenuFacade;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class POSArticoloMenuFacade {

	private IArticoloMenuFacade articolo;

	public ArrayList<Ingrediente> selezionaIngredientiPietanza(User role, Long id) {
		ArrayList<Ingrediente> ingr = null;
		try {
			ingr = articolo.selezionaIngredientiPietanza(role, id);
		} catch (RemoteException e) {
			System.err.println("POSArticoloMenuFacade#selezionaIngredientiPietanza");
		}
		
		return ingr;
	}
	
	public Ingrediente selezionaIngredientePerNome(User role,
			String nome) {
		Ingrediente ingr = null;
		try {
			ingr = articolo.selezionaIngredientePerNome(role, nome);
		} catch (RemoteException e) {
			System.err.println("POSArticoloMenuFacade#selezionaIngredientePerNome");
		}
		
		return ingr;
	}

	public ArrayList<Pietanza> selezionaPietanze(User role) {
		ArrayList<Pietanza> pietanze = null;
		try {
			pietanze = articolo.selezionaPietanze(role);
		} catch (RemoteException e) {
			System.err.println("POSArticoloMenuFacade#selezionaPietanze");
		}
		
		return pietanze;
	}

	public ArrayList<Bevanda> selezionaBevande(User role) {
		ArrayList<Bevanda> bevande = null;
		try {
			bevande = articolo.selezionaBevande(role);
		} catch (RemoteException e) {
			System.err.println("POSArticoloMenuFacade#selezionaBevande");
		} 
		
		return bevande;
	}

	public ArrayList<Ingrediente> selezionaIngredienti(User role) {
		ArrayList<Ingrediente> ingrediente = null;
		try {
			ingrediente = articolo.selezionaIngredienti(role);
		} catch (RemoteException e) {
			System.err.println("POSArticoloMenuFacade#selezionaIngredienti");
		}
		
		return ingrediente;
	}

	public boolean rimuoviBevandaMenu(User role, Long id) {
		try {
			return articolo.rimuoviBevandaMenu(role, id);
		} catch (Exception e) {
			System.err.println("POSArticoloMenuFacade#rimuoviBevandaMenu");
			return false;
		} 
	}
	
	public boolean rimuoviIngrediente(User role, Long id) {
		try {
			return articolo.rimuoviIngrediente(role, id);
		} catch (RemoteException e) {
			System.err.println("POSArticoloMenuFacade#rimuoviIngrediente");
			return false;
		}
	}

	public boolean rimuoviPietanzaMenu(User role, Long id) {
		try {
			return articolo.rimuoviPietanzaMenu(role, id);
		} catch (RemoteException e) {
			System.err.println("POSArticoloMenuFacade#rimuoviPietanzaMenu");
			return false;
		}
	}

	public void updateBevanda(User role, Bevanda bevanda) {
		try {
			articolo.updateBevanda(role, bevanda);
		} catch (RemoteException e) {
			System.err.println("POSArticoloMenuFacade#updateBevanda");
		}
	}
	
	public void updateIngrediente(User role, Ingrediente ingrediente) {
		try {
			articolo.updateIngrediente(role, ingrediente);
		} catch (RemoteException e) {
			System.err.println("POSArticoloMenuFacade#updateIngrediente");
		}

	}

	public void updateBevandaMagazzino(User role, BevandaMagazzino bevanda) {
		try {
			articolo.updateBevandaMagazzino(role, bevanda);
		} catch (RemoteException e) {
			System.err.println("POSArticoloMenuFacade#updateBevandaMagazzino");
		}
	}
	
	public void updateIndredientiPietanza(User role, Long idPietanza, HashSet<IngredientePietanza> ingredientiPietanza) {
		try {
			articolo.updateIndredientiPietanza(role, idPietanza, ingredientiPietanza);
		} catch (RemoteException e) {
			System.err.println("POSArticoloMenuFacade#updateIndredientiPietanza");
		}

	}

	public void updateIngredienteMagazzino(User role, IngredienteMagazzino ingredienteMagazzino) {
		try {
			articolo.updateIngredienteMagazzino(role, ingredienteMagazzino);
		} catch (RemoteException e) {
			System.err.println("POSArticoloMenuFacade#updateIngredienteMagazzino");
		}
	}
	
	public void updatePietanza(User role, Pietanza pietanza) {
		try {
			articolo.updatePietanza(role, pietanza);
		} catch (RemoteException e) {
			System.err.println("POSArticoloMenuFacade#updatePietanza");
		}
	}
	
	public HashSet<IngredientePietanza> inserisciIngredientiPietanze(User role, HashSet<IngredientePietanza> ingredientiPietanza) {
		try {
			ingredientiPietanza = articolo.inserisciIngredientiPietanze(role, ingredientiPietanza);
		} catch (RemoteException e) {
			System.err.println("POSArticoloMenuFacade#inserisciIngredientiPietanze");
		}

		return ingredientiPietanza;
	}

	public Bevanda inserisciBevandaMenu(User role, Bevanda art) {
		try {
			art = articolo.inserisciBevandaMenu(role, art);
		} catch (RemoteException e) {
			System.err.println("POSArticoloMenuFacade#inserisciBevandaMenu");
		}

		return art;
	}
	
	public IngredientePietanza inserisciIngredientePietanza(User role, IngredientePietanza ingrediente) {
		try {
			ingrediente = articolo.inserisciIngredientePietanza(role, ingrediente);
		} catch (RemoteException e) {
			System.err.println("POSArticoloMenuFacade#inserisciIngredientePietanza");
		}

		return ingrediente;
	}
	
	public Pietanza inserisciPietanzaMenu(User role, Pietanza art) {
		try {
			art = articolo.inserisciPietanzaMenu(role, art);
		} catch (RemoteException e) {
			System.err.println("POSArticoloMenuFacade#inserisciPietanzaMenu");
		}

		return art;
	}

	public Ingrediente inserisciIngrediente(User role, Ingrediente ingrediente) {
		try {
			ingrediente = articolo.inserisciIngrediente(role, ingrediente);
		} catch (RemoteException e) {
			System.err.println("POSArticoloMenuFacade#inserisciIngrediente");
		}

		return ingrediente;
	}

	public BevandaMagazzino inserisciBevandaMagazzino(User role, Long bevandaId, Long id, Integer quantita) {
    	System.out.println("bevanda id s" + bevandaId);
    	System.out.println("bevanda magazzino id s" + id);
		BevandaMagazzino bevandaMagazzino = null;
		try {
			bevandaMagazzino = articolo.inserisciBevandaMagazzino(role, bevandaId, id, quantita);
		} catch (RemoteException e) {
			System.err.println("POSArticoloMenuFacade#inserisciBevandaMagazzino");
		}
		
		return bevandaMagazzino;
	}

	public IngredienteMagazzino inserisciIngredienteMagazzino(User role, Long id, Integer quantita) {
		IngredienteMagazzino ingrediente = null;
		try {
			ingrediente = articolo.inserisciIngredienteMagazzino(role, id, quantita);
		} catch (RemoteException e) {
			System.err.println("POSArticoloMenuFacade#inserisciIngredienteMagazzino");
		}
		
		return ingrediente;
	}

}
