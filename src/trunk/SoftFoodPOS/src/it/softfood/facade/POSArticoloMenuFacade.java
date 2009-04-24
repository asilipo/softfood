package it.softfood.facade;

import it.softfood.entity.Articolo;
import it.softfood.entity.Bevanda;
import it.softfood.entity.BevandaMagazzino;
import it.softfood.entity.Ingrediente;
import it.softfood.entity.Pietanza;
import it.softfood.entity.User;
import it.softfood.handler.ArticoloMenuFacade;
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
//	private ArticoloMenuFacade articolo=ArticoloMenuFacade.getInstance();
	
	public ArrayList<Ingrediente> selezionaIngredientiPietanza(User role, Long id){
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
	
	public void rimuoviBevandaMenu(User role, Long id){
		try {
			articolo.rimuoviBevandaMenu(role, id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void rimuoviPietanzaMenu(User role, Long id){
		try {
			articolo.rimuoviPietanzaMenu(role, id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateBevanda(User role, Bevanda bevanda){
		try {
			articolo.updateBevanda(role, bevanda);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void updateBevandaMagazzino(User role, BevandaMagazzino bevanda){
		try {
			articolo.updateBevandaMagazzino(role, bevanda);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	public Articolo inserisciBevandaMenu(User role, Bevanda art){
		
		try {
			art=articolo.inserisciBevandaMenu(role, art);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return art;
	
	}
	
	public BevandaMagazzino inserisciBevandaMagazzino(User role, Long id, Integer quantita){
		BevandaMagazzino bevanda=null;
		try {
			bevanda=articolo.inserisciBevandaMagazzino(role, id, quantita);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bevanda;
	}

}
