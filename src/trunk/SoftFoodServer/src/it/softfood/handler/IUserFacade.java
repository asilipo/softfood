package it.softfood.handler;

import java.rmi.Remote;
import java.rmi.RemoteException;

import it.softfood.entity.User;

public interface IUserFacade extends Remote {

	public User selezionaUtentePerUserPassword(String username, String password) throws RemoteException;

	public User inserisciUtente(User role, User u)throws RemoteException;

	public User selezionaUserName(User role, User u)throws RemoteException;

	public User selezionaPassword(User role, User u)throws RemoteException;

	public boolean modificaRuolo(User role, User u, String ruolo)throws RemoteException;

	public boolean eliminaUtente(User role, User r)throws RemoteException;

}