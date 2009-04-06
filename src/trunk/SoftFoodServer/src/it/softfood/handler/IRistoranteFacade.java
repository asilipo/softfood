package it.softfood.handler;

import it.softfood.entity.Ristorante;
import it.softfood.entity.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRistoranteFacade extends Remote{

	public abstract Ristorante inserisciRistorante(User role,Ristorante ristorante)throws RemoteException;

	public abstract Ristorante modificaRistorante(User role,Ristorante nuovoRistorante,
			Ristorante vecchioRistorante)throws RemoteException;

	public abstract Ristorante selezionaRistorantePerRagioneSociale(User role,
			String ragioneSociale)throws RemoteException;

	public abstract Ristorante selezionaRistorantePerPartitaIva(User role,
			String partitaIva)throws RemoteException;

	public abstract boolean rimuoviRistorante(User role,String ragioneSociale)throws RemoteException;

}