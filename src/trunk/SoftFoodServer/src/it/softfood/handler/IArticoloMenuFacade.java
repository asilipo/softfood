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

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public interface IArticoloMenuFacade extends Remote {

	public HashSet<IngredientePietanza> inserisciIngredientiPietanze(User user, HashSet<IngredientePietanza> ingredientiPietanza) throws RemoteException;
	    
	public boolean updateIndredientiPietanza(User user, HashSet<IngredientePietanza> ingredientiPietanza) throws RemoteException;
		
	public boolean updatePietanza(User user, Pietanza pietanza) throws RemoteException;
	 
	public boolean updateBevanda(User user, Bevanda bevanda) throws RemoteException;
	
	public boolean updateIngrediente(User user, Ingrediente ingrediente) throws RemoteException;
	
	public boolean updateBevandaMagazzino(User user, BevandaMagazzino bevandaMagazzino) throws RemoteException;

	public boolean updateIngredienteMagazzino(User user, IngredienteMagazzino ingredienteMagazzino) throws RemoteException;
	
	public BevandaMagazzino inserisciBevandaMagazzino(User user, Long id, Integer quantita) throws RemoteException;
	
	public IngredienteMagazzino inserisciIngredienteMagazzino(User user, Long id, Integer quantita) throws RemoteException;
	
	public IngredientePietanza inserisciIngredientePietanza(User user, IngredientePietanza ingrediente) throws RemoteException;
	
	public Ingrediente inserisciIngrediente(User user, Ingrediente ingrediente) throws RemoteException;
	
    public Bevanda inserisciBevandaMenu(User user, Bevanda bevanda) throws RemoteException;
    
    public Pietanza inserisciPietanzaMenu(User user, Pietanza pietanza) throws RemoteException;
    
	public Articolo selezionaArticoloMenuPerId(User user, Long id) throws RemoteException;

	public Ingrediente selezionaIngredientePerNome(User user, String nome) throws RemoteException;
	
	public ArrayList<Pietanza> selezionaPietanzePerTipo(User user, TipoPietanza tipoPietanza) throws RemoteException;

	public ArrayList<Pietanza> selezionaPietanze(User user) throws RemoteException;

	public ArrayList<Pietanza> selezionaPietanzeDisponibili(User user) throws RemoteException;

	public ArrayList<Pietanza> selezionaPietanzeDisponibiliPerTipo(User user, TipoPietanza tipoPietanza) throws RemoteException;

	public HashMap<Pietanza, Integer> selezionaDisponibilitaPietanze(User user) throws RemoteException;

	public Integer selezionaDisponibilitaPietanza(User user, Long id) throws RemoteException;

	public Integer selezionaDisponibilitaBevanda(User user, Long id)throws RemoteException;

	public HashMap<Pietanza, Integer> selezionaDisponibilitaPietanzePerTipo(User user, TipoPietanza tipoPietanza) throws RemoteException;

	public ArrayList<Bevanda> selezionaBevande(User user) throws RemoteException;

	public ArrayList<Bevanda> selezionaBevandeDisponibili(User user) throws RemoteException;

	public boolean rimuoviBevandaMenu(User user, Long id) throws RemoteException;

	public boolean rimuoviPietanzaMenu(User user, Long id) throws RemoteException;
	
	public boolean rimuoviIngrediente(User user,Long id) throws RemoteException;

	public ArrayList<Ingrediente> selezionaIngredientiPietanza(User user, Long id) throws RemoteException;

	public ArrayList<Ingrediente> selezionaIngredienti(User user) throws RemoteException;
	
}