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

public class POSArticoloMenuFacade {
	
	public ArrayList<Ingrediente> selezionaIngredientiPietanza(User role, Long id){
		ArticoloMenuFacade articolo=ArticoloMenuFacade.getInstance();
		ArrayList<Ingrediente> ingr=articolo.selezionaIngredientiPietanza(role, id);
		return ingr;
	}


}
