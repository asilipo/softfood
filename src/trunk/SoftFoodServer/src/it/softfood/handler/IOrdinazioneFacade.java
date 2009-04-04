package it.softfood.handler;

import it.softfood.entity.Ingrediente;
import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.Ordinazione;
import it.softfood.entity.Tavolo;
import it.softfood.entity.Variante;
import it.softfood.enumeration.TipoPietanza;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

public interface IOrdinazioneFacade  extends Remote{

	public Ordinazione inserisciOrdinazione(Ordinazione ordinazione)throws RemoteException;

	public Ordinazione modificaOrdinazione(Ordinazione nuovaOrdinazione, Ordinazione vecchiaOrdinazione) throws RemoteException;

	public Ordinazione selezionaOrdinazionePerId(Long id) throws RemoteException;

	public ArrayList<Ordinazione> selezionaOrdinazioni() throws RemoteException;

	public ArrayList<Ordinazione> selezionaOrdinazioniPerData(Date data) throws RemoteException;

	public ArrayList<Ordinazione> selezionaOrdinazioniGiornalierePerTavolo(Tavolo tavolo, Boolean terminato) throws RemoteException;

	public Ordinazione selezionaOrdinazioneGiornalieraPerTavolo(String riferimentoTavolo, Boolean terminato) throws RemoteException;

	public boolean rimuoviOrdinazione(Long id, Boolean ripristinaPietanze) throws RemoteException;

	public LineaOrdinazione inserisciLineaOrdinazione(LineaOrdinazione lineaOrdinazione) throws RemoteException;

	public LineaOrdinazione modificaLineaOrdinazione(LineaOrdinazione nuovaLineaOrdinazione, LineaOrdinazione vecchiaLineaOrdinazione) throws RemoteException;

	public LineaOrdinazione selezionaLineaOrdinazionePerId(Long id)throws RemoteException;

	public ArrayList<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazione(Ordinazione ordinazione) throws RemoteException;

	public ArrayList<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazione(Ordinazione ordinazione, TipoPietanza tipoPietanza) throws RemoteException;

	public boolean rimuoviLineaOrdinazione(Long id) throws RemoteException;

	public Variante inserisciVariante(Variante variante) throws RemoteException;

	public ArrayList<Ingrediente> selezionaIngredientiPerVariante() throws RemoteException;

	public Variante modificaVariante(Variante nuovaVariante, Variante vecchiaVariante) throws RemoteException;

	public Variante selezionaVariantePerId(Long id) throws RemoteException;

	public Ingrediente selezionaIngredientePerNome(String ingrediente) throws RemoteException;

	public ArrayList<Variante> selezionaVariantiPerIngrediente(Ingrediente ingrediente) throws RemoteException;

	public ArrayList<Variante> selezionaVariantiPerLineaOrdinazione(LineaOrdinazione lineaOrdinazione) throws RemoteException;

	public boolean rimuoviVariante(Long id) throws RemoteException;

}