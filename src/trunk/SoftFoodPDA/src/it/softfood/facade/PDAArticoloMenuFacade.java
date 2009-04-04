package it.softfood.facade;

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

}
