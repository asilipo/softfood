package it.softfood.handler;

import it.softfood.entity.Ingrediente;
import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.Ordinazione;
import it.softfood.entity.Tavolo;
import it.softfood.entity.User;
import it.softfood.entity.Variante;
import it.softfood.enumeration.TipoPietanza;
import it.softfood.exception.TavoloOccupatoException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public interface IOrdinazioneFacade  extends Remote{

	public Ordinazione inserisciOrdinazione(User user, Ordinazione ordinazione) throws TavoloOccupatoException, RemoteException;

	public Ordinazione modificaOrdinazione(User user, Ordinazione nuovaOrdinazione, Ordinazione vecchiaOrdinazione) throws RemoteException;

	public Ordinazione selezionaOrdinazionePerId(User user, Long id) throws RemoteException;

	public ArrayList<Ordinazione> selezionaOrdinazioni(User user) throws RemoteException;

	public ArrayList<LineaOrdinazione> selezionaOrdinazioniGiornaliere(User user) throws RemoteException;

	public ArrayList<LineaOrdinazione> selezionaOrdinazioniGiornaliereNoData(User user) throws RemoteException;
		
	public ArrayList<Ordinazione> selezionaOrdinazioniGiornalierePerTavolo(User user, Tavolo tavolo, Boolean terminato) throws RemoteException;

	public Ordinazione selezionaOrdinazioneGiornalieraPerTavolo(User user, String riferimentoTavolo, Boolean terminato) throws RemoteException;

	public Ordinazione selezionaOrdinazionePerTavolo(User user, String riferimentoTavolo, Boolean terminato) throws RemoteException;
		
	public boolean rimuoviOrdinazione(User user, Long id, Boolean ripristinaPietanze) throws RemoteException;

	public LineaOrdinazione inserisciLineaOrdinazione(User user, LineaOrdinazione lineaOrdinazione) throws RemoteException;

	public LineaOrdinazione modificaLineaOrdinazione(User user, LineaOrdinazione nuovaLineaOrdinazione, LineaOrdinazione vecchiaLineaOrdinazione) throws RemoteException;

	public LineaOrdinazione selezionaLineaOrdinazionePerId(User user, Long id)throws RemoteException;

	public ArrayList<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazione(User user, Ordinazione ordinazione) throws RemoteException;

	public ArrayList<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazioneTipoPietanza(User user, Ordinazione ordinazione, TipoPietanza tipoPietanza) throws RemoteException;

	public boolean rimuoviLineaOrdinazione(User user, Long id) throws RemoteException;

	public Variante inserisciVariante(User user, Variante variante) throws RemoteException;

	public ArrayList<Ingrediente> selezionaIngredientiPerVariante(User user) throws RemoteException;

	public Variante modificaVariante(User user, Variante nuovaVariante, Variante vecchiaVariante) throws RemoteException;

	public Variante selezionaVariantePerId(User user, Long id) throws RemoteException;

	public Ingrediente selezionaIngredientePerNome(User user, String ingrediente) throws RemoteException;

	public ArrayList<Variante> selezionaVariantiPerIngrediente(User user, Ingrediente ingrediente) throws RemoteException;

	public ArrayList<Variante> selezionaVariantiPerLineaOrdinazione(User user, LineaOrdinazione lineaOrdinazione) throws RemoteException;

	public boolean rimuoviVariante(User user, Long id) throws RemoteException;
	
	public void setLineaEvasa(User user, LineaOrdinazione linea) throws RemoteException;

}