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
import java.lang.Boolean;

public interface IOrdinazioneFacade extends Remote{

	public abstract Ordinazione inserisciOrdinazione(Ordinazione ordinazione)throws RemoteException;

	public abstract Ordinazione modificaOrdinazione(
			Ordinazione nuovaOrdinazione, Ordinazione vecchiaOrdinazione)throws RemoteException;

	public abstract Ordinazione selezionaOrdinazionePerId(Long id)throws RemoteException;

	public abstract ArrayList<Ordinazione> selezionaOrdinazioni()throws RemoteException;

	public abstract ArrayList<Ordinazione> selezionaOrdinazioniPerData(Date data)throws RemoteException;

/*	public abstract ArrayList<Ordinazione> selezionaOrdinazioniGiornalierePerTavolo(
			Tavolo tavolo, Boolean terminato)throws RemoteException;
*/
	public abstract Ordinazione selezionaOrdinazioneGiornalieraPerTavolo(
			Tavolo tavolo, Boolean terminato)throws RemoteException;

	public abstract Ordinazione selezionaOrdinazioneGiornalieraPerTavolo(
			String riferimentoTavolo, Boolean terminato)throws RemoteException;

	public abstract boolean rimuoviOrdinazione(Long id,
			Boolean ripristinaPietanze)throws RemoteException;

	public abstract LineaOrdinazione inserisciLineaOrdinazione(
			LineaOrdinazione lineaOrdinazione)throws RemoteException;

	public abstract LineaOrdinazione modificaLineaOrdinazione(
			LineaOrdinazione nuovaLineaOrdinazione,
			LineaOrdinazione vecchiaLineaOrdinazione)throws RemoteException;

	public abstract LineaOrdinazione selezionaLineaOrdinazionePerId(Long id)throws RemoteException;

	public abstract ArrayList<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazione(
			Ordinazione ordinazione)throws RemoteException;

	public abstract ArrayList<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazione(
			Ordinazione ordinazione, TipoPietanza tipoPietanza)throws RemoteException;

	public abstract boolean rimuoviLineaOrdinazione(Long id)throws RemoteException;

	public abstract Variante inserisciVariante(Variante variante)throws RemoteException;

	public abstract ArrayList<Ingrediente> selezionaIngredientiPerVariante()throws RemoteException;

	public abstract Variante modificaVariante(Variante nuovaVariante,
			Variante vecchiaVariante)throws RemoteException;

	public abstract Variante selezionaVariantePerId(Long id)throws RemoteException;

	public abstract Ingrediente selezionaIngredientePerNome(String ingrediente)throws RemoteException;

	public abstract ArrayList<Variante> selezionaVariantiPerIngrediente(
			Ingrediente ingrediente)throws RemoteException;

	public abstract ArrayList<Variante> selezionaVariantiPerLineaOrdinazione(
			LineaOrdinazione lineaOrdinazione)throws RemoteException;

	public abstract boolean rimuoviVariante(Long id)throws RemoteException;

}