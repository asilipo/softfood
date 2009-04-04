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

	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#inserisciOrdinazione(it.softfood.entity.Ordinazione)
	 */
	public Ordinazione inserisciOrdinazione(Ordinazione ordinazione)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#modificaOrdinazione(it.softfood.entity.Ordinazione, it.softfood.entity.Ordinazione)
	 */
	public Ordinazione modificaOrdinazione(Ordinazione nuovaOrdinazione,
			Ordinazione vecchiaOrdinazione)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#selezionaOrdinazionePerId(java.lang.Long)
	 */
	public Ordinazione selezionaOrdinazionePerId(Long id)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#selezionaOrdinazioni()
	 */
	public ArrayList<Ordinazione> selezionaOrdinazioni()throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#selezionaOrdinazioniPerData(java.util.Date)
	 */
	public ArrayList<Ordinazione> selezionaOrdinazioniPerData(Date data)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#selezionaOrdinazioniGiornalierePerTavolo(it.softfood.entity.Tavolo, java.lang.Boolean)
	 */
	public ArrayList<Ordinazione> selezionaOrdinazioniGiornalierePerTavolo(
			Tavolo tavolo, Boolean terminato)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#selezionaOrdinazioneGiornalieraPerTavolo(java.lang.String, java.lang.Boolean)
	 */
	public Ordinazione selezionaOrdinazioneGiornalieraPerTavolo(
			String riferimentoTavolo, Boolean terminato)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#rimuoviOrdinazione(java.lang.Long, java.lang.Boolean)
	 */
	public boolean rimuoviOrdinazione(Long id, Boolean ripristinaPietanze)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#inserisciLineaOrdinazione(it.softfood.entity.LineaOrdinazione)
	 */
	public LineaOrdinazione inserisciLineaOrdinazione(
			LineaOrdinazione lineaOrdinazione)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#modificaLineaOrdinazione(it.softfood.entity.LineaOrdinazione, it.softfood.entity.LineaOrdinazione)
	 */
	public LineaOrdinazione modificaLineaOrdinazione(
			LineaOrdinazione nuovaLineaOrdinazione,
			LineaOrdinazione vecchiaLineaOrdinazione)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#selezionaLineaOrdinazionePerId(java.lang.Long)
	 */
	public LineaOrdinazione selezionaLineaOrdinazionePerId(Long id)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#selezionaLineeOrdinazionePerOrdinazione(it.softfood.entity.Ordinazione)
	 */
	public ArrayList<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazione(
			Ordinazione ordinazione)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#selezionaLineeOrdinazionePerOrdinazione(it.softfood.entity.Ordinazione, it.softfood.enumeration.TipoPietanza)
	 */
	public ArrayList<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazione(
			Ordinazione ordinazione, TipoPietanza tipoPietanza)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#rimuoviLineaOrdinazione(java.lang.Long)
	 */
	public boolean rimuoviLineaOrdinazione(Long id)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#inserisciVariante(it.softfood.entity.Variante)
	 */
	public Variante inserisciVariante(Variante variante)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#selezionaIngredientiPerVariante()
	 */
	public ArrayList<Ingrediente> selezionaIngredientiPerVariante()throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#modificaVariante(it.softfood.entity.Variante, it.softfood.entity.Variante)
	 */
	public Variante modificaVariante(Variante nuovaVariante,
			Variante vecchiaVariante)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#selezionaVariantePerId(java.lang.Long)
	 */
	public Variante selezionaVariantePerId(Long id)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#selezionaIngredientePerNome(java.lang.String)
	 */
	public Ingrediente selezionaIngredientePerNome(String ingrediente)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#selezionaVariantiPerIngrediente(it.softfood.entity.Ingrediente)
	 */
	public ArrayList<Variante> selezionaVariantiPerIngrediente(
			Ingrediente ingrediente)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#selezionaVariantiPerLineaOrdinazione(it.softfood.entity.LineaOrdinazione)
	 */
	public ArrayList<Variante> selezionaVariantiPerLineaOrdinazione(
			LineaOrdinazione lineaOrdinazione)throws RemoteException;

	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#rimuoviVariante(java.lang.Long)
	 */
	public boolean rimuoviVariante(Long id)throws RemoteException;

}