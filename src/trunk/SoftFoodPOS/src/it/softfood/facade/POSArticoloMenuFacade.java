package it.softfood.facade;

import it.softfood.entity.Articolo;
import it.softfood.entity.Bevanda;
import it.softfood.entity.BevandaMagazzino;
import it.softfood.entity.Ingrediente;
import it.softfood.entity.IngredienteMagazzino;
import it.softfood.entity.IngredientePietanza;
import it.softfood.entity.Pietanza;
import it.softfood.entity.User;
import it.softfood.exception.ViolazioneVincoliRimozioneBevandaException;
import it.softfood.handler.ArticoloMenuFacade;
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

	// private ArticoloMenuFacade articolo=ArticoloMenuFacade.getInstance();

	public ArrayList<Ingrediente> selezionaIngredientiPietanza(User role, Long id) {
		ArrayList<Ingrediente> ingr = null;
		try {
			ingr = articolo.selezionaIngredientiPietanza(role, id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ingr;
	}
	
	public Ingrediente selezionaIngredientePerNome(User role,
			String nome) {
		Ingrediente ingr = null;
		try {
			ingr = articolo.selezionaIngredientePerNome(role, nome);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ingr;
	}

	public ArrayList<Pietanza> selezionaPietanze(User role) {
		ArrayList<Pietanza> pietanze = null;
		try {
			pietanze = articolo.selezionaPietanze(role);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pietanze;
	}

	public ArrayList<Bevanda> selezionaBevande(User role) {
		ArrayList<Bevanda> bevande = null;
		try {
			bevande = articolo.selezionaBevande(role);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return bevande;
	}

	public ArrayList<Ingrediente> selezionaIngredienti(User role) {
		ArrayList<Ingrediente> ingrediente = null;
		try {
			ingrediente = articolo.selezionaIngredienti(role);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ingrediente;
	}

	public boolean rimuoviBevandaMenu(User role, Long id) throws ViolazioneVincoliRimozioneBevandaException {
		try {
			return articolo.rimuoviBevandaMenu(role, id);
		} catch (Exception e) {
			return false;
		} 
	}
	
	public void rimuoviIngrediente(User role, Long id) {
		try {
			articolo.rimuoviIngrediente(role, id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void rimuoviPietanzaMenu(User role, Long id) {
		try {
			articolo.rimuoviPietanzaMenu(role, id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateBevanda(User role, Bevanda bevanda) {
		try {
			articolo.updateBevanda(role, bevanda);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void updateIngrediente(User role, Ingrediente ingrediente) {
		try {
			articolo.updateIngrediente(role, ingrediente);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateBevandaMagazzino(User role, BevandaMagazzino bevanda) {
		try {
			articolo.updateBevandaMagazzino(role, bevanda);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void updateIndredientiPietanza(User role, HashSet<IngredientePietanza> ingredientiPietanza) {
		try {
			articolo.updateIndredientiPietanza(role, ingredientiPietanza);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateIngredienteMagazzino(User role,
			IngredienteMagazzino ingredienteMagazzino) {
		try {
			articolo.updateIngredienteMagazzino(role, ingredienteMagazzino);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void updatePietanza(User role,
			Pietanza pietanza) {
		try {
			articolo.updatePietanza(role, pietanza);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public HashSet<IngredientePietanza> inserisciIngredientiPietanze(User role, HashSet<IngredientePietanza> ingredientiPietanza) {

		try {
			ingredientiPietanza = articolo.inserisciIngredientiPietanze(role, ingredientiPietanza);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ingredientiPietanza;

	}

	public Bevanda inserisciBevandaMenu(User role, Bevanda art) {

		try {
			art = articolo.inserisciBevandaMenu(role, art);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return art;

	}
	
	public IngredientePietanza inserisciIngredientePietanza(User role, IngredientePietanza ingrediente) {

		try {
			ingrediente = articolo.inserisciIngredientePietanza(role, ingrediente);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ingrediente;

	}
	
	public Pietanza inserisciPietanzaMenu(User role, Pietanza art) {

		try {
			art = articolo.inserisciPietanzaMenu(role, art);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return art;

	}

	public Ingrediente inserisciIngrediente(User role, Ingrediente ingrediente) {

		try {
			ingrediente = articolo.inserisciIngrediente(role, ingrediente);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ingrediente;

	}

	public BevandaMagazzino inserisciBevandaMagazzino(User role, Long id,
			Integer quantita) {
		BevandaMagazzino bevanda = null;
		try {
			bevanda = articolo.inserisciBevandaMagazzino(role, id, quantita);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bevanda;
	}

	public IngredienteMagazzino inserisciIngredienteMagazzino(User role,
			Long id, Integer quantita) {
		IngredienteMagazzino ingrediente = null;
		try {
			ingrediente = articolo.inserisciIngredienteMagazzino(role, id,
					quantita);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ingrediente;
	}

}
