package it.softfood.handler;

import it.softfood.entity.User;
import it.softfood.enumeration.Ruolo;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public interface IUserFacade extends Remote {

	public User selezionaUtentePerUserPassword(String username, String password) throws RemoteException;

	public User inserisciUtente(User user) throws RemoteException;

	public User selezionaUserName(User user) throws RemoteException;

	public User selezionaPassword(User user) throws RemoteException;

	public boolean modificaRuolo(User user, Ruolo ruolo) throws RemoteException;

	public boolean eliminaUtente(User user) throws RemoteException;
	
	public User login(String username, String password) throws RemoteException;
	
	public void logout(User user)throws RemoteException;

}