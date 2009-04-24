package it.softfood.handler;

import it.softfood.entity.Articolo;
import it.softfood.entity.Bevanda;
import it.softfood.entity.Ingrediente;
import it.softfood.entity.Pietanza;
import it.softfood.entity.User;
import it.softfood.enumeration.TipoPietanza;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public interface IArticoloMenuFacade extends Remote{

	public Articolo inserisciArticoloMenu(User role,Articolo articolo)throws RemoteException;

	public Articolo selezionaArticoloMenuPerId(User role,Long id)throws RemoteException;

	public ArrayList<Pietanza> selezionaPietanzePerTipo(User role,TipoPietanza tipoPietanza)throws RemoteException;

	public ArrayList<Pietanza> selezionaPietanze(User role)throws RemoteException;

	public ArrayList<Pietanza> selezionaPietanzeDisponibili(User role)throws RemoteException;

	public ArrayList<Pietanza> selezionaPietanzeDisponibiliPerTipo(User role,TipoPietanza tipoPietanza)throws RemoteException;

	public HashMap<Pietanza, Integer> selezionaDisponibilitaPietanze(User role)throws RemoteException;

	public Integer selezionaDisponibilitaPietanza(User role,Long id)throws RemoteException;

	public Integer selezionaDisponibilitaBevanda(User role,Long id)throws RemoteException;

	public HashMap<Pietanza, Integer> selezionaDisponibilitaPietanzePerTipo(User role,TipoPietanza tipoPietanza)throws RemoteException;

	public ArrayList<Bevanda> selezionaBevande(User role)throws RemoteException;

	public ArrayList<Bevanda> selezionaBevandeDisponibili(User role)throws RemoteException;

	public boolean rimuoviArticoloMenu(User role,Long id)throws RemoteException;

	public ArrayList<Ingrediente> selezionaIngredientiPietanza(User role, Long id)throws RemoteException;

}