package it.softfood.handler;

import it.softfood.entity.Tavolo;
import java.rmi.Remote;     
import java.rmi.RemoteException;
import java.util.List;

public interface ITavoloFacade extends Remote {

	public abstract Tavolo inserisciTavolo(Tavolo tavolo)throws RemoteException;

	public abstract boolean liberaTavolo(Tavolo tavolo)throws RemoteException;

	public abstract boolean occupaTavolo(Tavolo tavolo)throws RemoteException;

	public abstract Tavolo selezionaTavolo(Long id)throws RemoteException;

	public abstract List<Tavolo> selezionaTavoliLiberi()throws RemoteException;

	public abstract List<Tavolo> selezionaTavoliOccupati()throws RemoteException;

	public abstract List<Tavolo> selezionaTavoliNonAttivi()throws RemoteException;

	public abstract boolean rimuoviTavolo(Long id)throws RemoteException;

	public abstract Long occupaTavoli(List<String> riferimenti)throws RemoteException;

}