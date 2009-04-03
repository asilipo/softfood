package it.softfood.handler;

import it.softfood.entity.Tavolo;
import java.rmi.Remote;     
import java.rmi.RemoteException;
import java.util.List;

public interface ITavoloFacade extends Remote {

	public Tavolo inserisciTavolo(Tavolo tavolo) throws RemoteException;

	public boolean liberaTavolo(Tavolo tavolo) throws RemoteException;

	public boolean occupaTavolo(Tavolo tavolo) throws RemoteException;

	public Tavolo selezionaTavolo(Long id) throws RemoteException;

	public List<Tavolo> selezionaTavoliLiberi() throws RemoteException;

	public List<Tavolo> selezionaTavoliOccupati() throws RemoteException;

	public List<Tavolo> selezionaTavoliNonAttivi() throws RemoteException;

	public boolean rimuoviTavolo(Long id) throws RemoteException;

	public Long occupaTavoli(List<String> riferimenti) throws RemoteException;

}