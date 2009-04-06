package it.softfood.handler;

import it.softfood.entity.Tavolo;
import it.softfood.entity.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface ITavoloFacade extends Remote{

	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#inserisciTavolo(it.softfood.entity.Tavolo)
	 */
	public abstract Tavolo inserisciTavolo(User role,Tavolo tavolo)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#liberaTavolo(it.softfood.entity.Tavolo)
	 */
	public abstract boolean liberaTavolo(User role,Tavolo tavolo)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#occupaTavolo(it.softfood.entity.Tavolo)
	 */
	public abstract boolean occupaTavolo(User role,Tavolo tavolo)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#selezionaTavolo(java.lang.Long)
	 */
	public abstract Tavolo selezionaTavolo(User role,Long id)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#selezionaTavoliLiberi()
	 */
	public abstract List<Tavolo> selezionaTavoliLiberi(User role)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#selezionaTavoliOccupati()
	 */
	public abstract List<Tavolo> selezionaTavoliOccupati(User role)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#selezionaTavoliNonAttivi()
	 */
	public abstract List<Tavolo> selezionaTavoliNonAttivi(User role)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#rimuoviTavolo(java.lang.Long)
	 */
	public abstract boolean rimuoviTavolo(User role,Long id)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#occupaTavoli(java.util.List)
	 */
	public abstract Long occupaTavoli(User role,ArrayList<String> riferimenti)throws RemoteException;

}