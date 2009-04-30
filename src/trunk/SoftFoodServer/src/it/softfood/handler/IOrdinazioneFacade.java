package it.softfood.handler;

import it.softfood.entity.Ingrediente;
import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.Ordinazione;
import it.softfood.entity.Tavolo;
import it.softfood.entity.User;
import it.softfood.entity.Variante;
import it.softfood.enumeration.TipoPietanza;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public interface IOrdinazioneFacade  extends Remote{

	public Ordinazione inserisciOrdinazione(User role,Ordinazione ordinazione)throws RemoteException;

	public Ordinazione modificaOrdinazione(User role,Ordinazione nuovaOrdinazione, Ordinazione vecchiaOrdinazione) throws RemoteException;

	public Ordinazione selezionaOrdinazionePerId(User role,Long id) throws RemoteException;

	public ArrayList<Ordinazione> selezionaOrdinazioni(User role) throws RemoteException;

	public ArrayList<LineaOrdinazione> selezionaOrdinazioniGiornaliere(User role) throws RemoteException;

	public ArrayList<Ordinazione> selezionaOrdinazioniGiornalierePerTavolo(User role, Tavolo tavolo, Boolean terminato) throws RemoteException;

	public Ordinazione selezionaOrdinazioneGiornalieraPerTavolo(User role,String riferimentoTavolo, Boolean terminato) throws RemoteException;

	public boolean rimuoviOrdinazione(User role,Long id, Boolean ripristinaPietanze) throws RemoteException;

	public LineaOrdinazione inserisciLineaOrdinazione(User role,LineaOrdinazione lineaOrdinazione) throws RemoteException;

	public LineaOrdinazione modificaLineaOrdinazione(User role,LineaOrdinazione nuovaLineaOrdinazione, LineaOrdinazione vecchiaLineaOrdinazione) throws RemoteException;

	public LineaOrdinazione selezionaLineaOrdinazionePerId(User role,Long id)throws RemoteException;

	public ArrayList<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazione(User role,Ordinazione ordinazione) throws RemoteException;

	public ArrayList<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazioneTipoPietanza(User role,Ordinazione ordinazione, TipoPietanza tipoPietanza) throws RemoteException;

	public boolean rimuoviLineaOrdinazione(User role,Long id) throws RemoteException;

	public Variante inserisciVariante(User role,Variante variante) throws RemoteException;

	public ArrayList<Ingrediente> selezionaIngredientiPerVariante(User role) throws RemoteException;

	public Variante modificaVariante(User role,Variante nuovaVariante, Variante vecchiaVariante) throws RemoteException;

	public Variante selezionaVariantePerId(User role,Long id) throws RemoteException;

	public Ingrediente selezionaIngredientePerNome(User role,String ingrediente) throws RemoteException;

	public ArrayList<Variante> selezionaVariantiPerIngrediente(User role,Ingrediente ingrediente) throws RemoteException;

	public ArrayList<Variante> selezionaVariantiPerLineaOrdinazione(User role,LineaOrdinazione lineaOrdinazione) throws RemoteException;

	public boolean rimuoviVariante(User role,Long id) throws RemoteException;
	
	public void setLineaEvasa(User role,LineaOrdinazione linea) throws RemoteException;

}