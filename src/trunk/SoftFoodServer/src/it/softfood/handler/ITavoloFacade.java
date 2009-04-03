package it.softfood.handler;

import it.softfood.entity.Tavolo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ITavoloFacade extends Remote{

	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#inserisciTavolo(it.softfood.entity.Tavolo)
	 */
	public abstract Tavolo inserisciTavolo(Tavolo tavolo)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#liberaTavolo(it.softfood.entity.Tavolo)
	 */
	public abstract boolean liberaTavolo(Tavolo tavolo)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#occupaTavolo(it.softfood.entity.Tavolo)
	 */
	public abstract boolean occupaTavolo(Tavolo tavolo)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#selezionaTavolo(java.lang.Long)
	 */
	public abstract Tavolo selezionaTavolo(Long id)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#selezionaTavoliLiberi()
	 */
	public abstract List<Tavolo> selezionaTavoliLiberi()throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#selezionaTavoliOccupati()
	 */
	public abstract List<Tavolo> selezionaTavoliOccupati()throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#selezionaTavoliNonAttivi()
	 */
	public abstract List<Tavolo> selezionaTavoliNonAttivi()throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#rimuoviTavolo(java.lang.Long)
	 */
	public abstract boolean rimuoviTavolo(Long id)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.ITavoloFacade#occupaTavoli(java.util.List)
	 */
	public abstract Long occupaTavoli(List<String> riferimenti)throws RemoteException;

}