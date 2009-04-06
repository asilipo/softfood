package it.softfood.facade;

import it.softfood.entity.Articolo;
import it.softfood.entity.Bevanda;
import it.softfood.entity.Ingrediente;
import it.softfood.entity.Pietanza;
import it.softfood.entity.User;
import it.softfood.enumeration.TipoPietanza;
import it.softfood.handler.ArticoloMenuFacade;

import java.util.ArrayList;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class PDAArticoloMenuFacade {

	public ArrayList<Pietanza> selezionaPietanzeDisponibiliPerTipo(User role,TipoPietanza tipo_pietanza) {
		System.out.println("PDAArticoloMenuFacade#selezionaPietanzeDisponibiliPerTipo " + tipo_pietanza);
		ArticoloMenuFacade articolo = ArticoloMenuFacade.getInstance();

		ArrayList<Pietanza> pietanze = (ArrayList<Pietanza>) articolo.selezionaPietanzeDisponibiliPerTipo(role,tipo_pietanza);
		return pietanze;
	}

	public ArrayList<Ingrediente> selezionaIngredientiPietanza(User role,Long id) {
		ArticoloMenuFacade articolo=ArticoloMenuFacade.getInstance();
		ArrayList<Ingrediente> ingredienti= (ArrayList<Ingrediente>) articolo.selezionaIngredientiPietanza(role,id);
		return ingredienti;
	}

	public int selezionaDisponibilitaBevanda(User role,Long id) {
		ArticoloMenuFacade articolo=ArticoloMenuFacade.getInstance();
		int disponibilita=articolo.selezionaDisponibilitaBevanda(role,id);
		return disponibilita;
	}

	public int selezionaDisponibilitaPietanza(User role,Long id) {
		ArticoloMenuFacade articolo=ArticoloMenuFacade.getInstance();
		int disponibilita=articolo.selezionaDisponibilitaPietanza(role,id);
		return disponibilita;
	}

	public Articolo selezionaArticoloMenuPerId(User role,Long id) {
		ArticoloMenuFacade articolo=ArticoloMenuFacade.getInstance();
		Articolo art=articolo.selezionaArticoloMenuPerId(role,id);
		return art;
	}

	public ArrayList<Bevanda> selezionaBevandeDisponibili(User role) {
		ArticoloMenuFacade articolo=ArticoloMenuFacade.getInstance();
		ArrayList<Bevanda> bevande= (ArrayList<Bevanda>) articolo.selezionaBevandeDisponibili(role);
		return bevande;
	}

}
