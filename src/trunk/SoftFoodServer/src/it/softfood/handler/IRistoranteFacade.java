package it.softfood.handler;

import it.softfood.entity.Ristorante;
import it.softfood.entity.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public interface IRistoranteFacade extends Remote{

	public Ristorante inserisciRistorante(User user, Ristorante ristorante) throws RemoteException;

	public Ristorante modificaRistorante(User user, Ristorante nuovoRistorante, Ristorante vecchioRistorante) throws RemoteException;

	public Ristorante selezionaRistorantePerRagioneSociale(User user, String ragioneSociale) throws RemoteException;

	public Ristorante selezionaRistorantePerPartitaIva(User user, String partitaIva) throws RemoteException;

	public boolean rimuoviRistorante(User user, String ragioneSociale) throws RemoteException;

}