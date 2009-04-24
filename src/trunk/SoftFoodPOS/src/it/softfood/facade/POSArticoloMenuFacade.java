package it.softfood.facade;

import it.softfood.entity.Bevanda;
import it.softfood.entity.Ingrediente;
import it.softfood.entity.Pietanza;
import it.softfood.entity.User;
import it.softfood.handler.IArticoloMenuFacade;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class POSArticoloMenuFacade {
	
	private IArticoloMenuFacade articolo;
	
	public ArrayList<Ingrediente> selezionaIngredientiPietanza(User role, Long id){
//		ArticoloMenuFacade articolo=ArticoloMenuFacade.getInstance();
		ArrayList<Ingrediente> ingr = null;
		try {
			ingr = articolo.selezionaIngredientiPietanza(role, id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ingr;
	}
	
	public ArrayList<Pietanza> selezionaPietanze(User role){
		ArrayList<Pietanza> pietanze=null;
		try {
			pietanze=articolo.selezionaPietanze(role);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pietanze;
	}
	
	public ArrayList<Bevanda> selezionaBevande(User role){
		ArrayList<Bevanda> bevande=null;
		try {
			bevande=articolo.selezionaBevande(role);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bevande;
	}
	
	public ArrayList<Ingrediente> selezionaIngredienti(User role){
		ArrayList<Ingrediente> ingrediente=null;
		try {
			ingrediente=articolo.selezionaIngredienti(role);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ingrediente;
	}
	
	public void rimuoviArticoloMenu(User role, Long id){
		try {
			articolo.rimuoviArticoloMenu(role, id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}


}
