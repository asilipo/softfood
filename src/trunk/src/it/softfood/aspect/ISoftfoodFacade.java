package it.softfood.aspect;

   

import it.softfood.entity.Tavolo;

import java.rmi.Remote;     
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Vector;


 
public interface ISoftfoodFacade extends Remote{
 
	public List<Tavolo> selezionaTavoliLiberi() throws RemoteException; 
	
}

