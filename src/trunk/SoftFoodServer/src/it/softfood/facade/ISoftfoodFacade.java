package it.softfood.facade;

   

import it.softfood.entity.Tavolo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


 
public interface ISoftfoodFacade extends Remote{
 
	public List<Tavolo> selezionaTavoliLiberi() throws RemoteException; 
	
}

