package it.softfood.handler;

import it.softfood.entity.Tavolo;
import it.softfood.entity.User;
import it.softfood.exception.TavoloOccupatoException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public interface ITavoloFacade extends Remote{

	public Tavolo inserisciTavolo(User user, Tavolo tavolo) throws RemoteException;

	public abstract boolean liberaTavolo(User user, Tavolo tavolo) throws RemoteException;

	public abstract boolean occupaTavolo(User user, Tavolo tavolo) throws RemoteException;

	public abstract Tavolo selezionaTavolo(User user, Long id) throws RemoteException;

	public abstract List<Tavolo> selezionaTavoliLiberi(User user) throws RemoteException;

	public abstract List<Tavolo> selezionaTavoliOccupati(User user) throws RemoteException;

	public abstract List<Tavolo> selezionaTavoliNonAttivi(User user) throws RemoteException;

	public abstract boolean rimuoviTavolo(User user, Long id) throws RemoteException;

	public abstract Long occupaTavoli(User user, ArrayList<String> riferimenti) throws TavoloOccupatoException, RemoteException;

}