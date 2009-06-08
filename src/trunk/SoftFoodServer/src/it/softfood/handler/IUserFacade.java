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

	public User selezionaUtente(String username, String password, Ruolo ruolo) throws RemoteException;
	
	public User inserisciUtente(User user, User nuovoUser) throws RemoteException;

	public User selezionaUserName(User user, User userUsername) throws RemoteException;

	public User selezionaPassword(User user, String username) throws RemoteException;

	public boolean modificaRuolo(User user, User userDaModificare, Ruolo ruolo) throws RemoteException;

	public boolean eliminaUtente(User user, User userDaEliminare) throws RemoteException;
	
	public User login(Ruolo ruolo, String password) throws RemoteException;
	
	public User loginUser(User user) throws RemoteException;
	
	public void logout(User user) throws RemoteException;

}