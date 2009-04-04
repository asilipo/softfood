package it.softfood.facade;

import it.softfood.entity.Articolo;
import it.softfood.entity.Bevanda;
import it.softfood.entity.Ingrediente;
import it.softfood.entity.Pietanza;
import it.softfood.enumeration.TipoPietanza;
import it.softfood.handler.ArticoloMenuFacade;

import java.util.ArrayList;

public class PDAArticoloMenuFacade {

	public ArrayList<Pietanza> selezionaPietanzeDisponibiliPerTipo(
			TipoPietanza tipo_pietanza) {
		ArticoloMenuFacade articolo=ArticoloMenuFacade.getInstance();
		ArrayList<Pietanza> pietanze=(ArrayList<Pietanza>) articolo.selezionaPietanzeDisponibiliPerTipo(tipo_pietanza);
		return pietanze;
	}

	public ArrayList<Ingrediente> selezionaIngredientiPietanza(Long id) {
		ArticoloMenuFacade articolo=ArticoloMenuFacade.getInstance();
		ArrayList<Ingrediente> ingredienti= (ArrayList<Ingrediente>) articolo.selezionaIngredientiPietanza(id);
		return ingredienti;
	}

	public int selezionaDisponibilitaBevanda(Long id) {
		ArticoloMenuFacade articolo=ArticoloMenuFacade.getInstance();
		int disponibilita=articolo.selezionaDisponibilitaBevanda(id);
		return disponibilita;
	}

	public int selezionaDisponibilitaPietanza(Long id) {
		ArticoloMenuFacade articolo=ArticoloMenuFacade.getInstance();
		int disponibilita=articolo.selezionaDisponibilitaPietanza(id);
		return disponibilita;
	}

	public Articolo selezionaArticoloMenuPerId(Long id) {
		ArticoloMenuFacade articolo=ArticoloMenuFacade.getInstance();
		Articolo art=articolo.selezionaArticoloMenuPerId(id);
		return art;
	}

	public ArrayList<Bevanda> selezionaBevandeDisponibili() {
		ArticoloMenuFacade articolo=ArticoloMenuFacade.getInstance();
		ArrayList<Bevanda> bevande= (ArrayList<Bevanda>) articolo.selezionaBevandeDisponibili();
		return bevande;
	}

}
