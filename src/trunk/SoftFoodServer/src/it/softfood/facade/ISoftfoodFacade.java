package it.softfood.facade;

   

import it.softfood.entity.Tavolo;

import java.io.Serializable;
import java.rmi.Remote;     
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Vector;


 
public interface ISoftfoodFacade extends Remote{
 
	public List<Tavolo> selezionaTavoliLiberi() throws RemoteException; 
	
}

