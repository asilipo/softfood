package it.softfood.handler;

import java.rmi.Remote;
import java.rmi.RemoteException;

import it.softfood.entity.Ristorante;

public interface IRistoranteFacade extends Remote{

	public abstract Ristorante inserisciRistorante(Ristorante ristorante)throws RemoteException;

	public abstract Ristorante modificaRistorante(Ristorante nuovoRistorante,
			Ristorante vecchioRistorante)throws RemoteException;

	public abstract Ristorante selezionaRistorantePerRagioneSociale(
			String ragioneSociale)throws RemoteException;

	public abstract Ristorante selezionaRistorantePerPartitaIva(
			String partitaIva)throws RemoteException;

	public abstract boolean rimuoviRistorante(String ragioneSociale)throws RemoteException;

}