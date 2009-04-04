package it.softfood.handler;

import it.softfood.entity.Articolo;
import it.softfood.entity.Bevanda;
import it.softfood.entity.Ingrediente;
import it.softfood.entity.Pietanza;
import it.softfood.enumeration.TipoPietanza;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public interface IArticoloMenuFacade extends Remote{

	public Articolo inserisciArticoloMenu(Articolo articolo)throws RemoteException;

	public Articolo selezionaArticoloMenuPerId(Long id)throws RemoteException;

	public ArrayList<Pietanza> selezionaPietanzePerTipo(TipoPietanza tipoPietanza)throws RemoteException;

	public ArrayList<Pietanza> selezionaPietanze()throws RemoteException;

	public ArrayList<Pietanza> selezionaPietanzeDisponibili()throws RemoteException;

	public ArrayList<Pietanza> selezionaPietanzeDisponibiliPerTipo(TipoPietanza tipoPietanza)throws RemoteException;

	public HashMap<Pietanza, Integer> selezionaDisponibilitaPietanze()throws RemoteException;

	public Integer selezionaDisponibilitaPietanza(Long id)throws RemoteException;

	public Integer selezionaDisponibilitaBevanda(Long id)throws RemoteException;

	public HashMap<Pietanza, Integer> selezionaDisponibilitaPietanzePerTipo(TipoPietanza tipoPietanza)throws RemoteException;

	public ArrayList<Bevanda> selezionaBevande()throws RemoteException;

	public ArrayList<Bevanda> selezionaBevandeDisponibili()throws RemoteException;

	public boolean rimuoviArticoloMenu(Long id)throws RemoteException;

	public ArrayList<Ingrediente> selezionaIngredientiPietanza(Long id)throws RemoteException;

}