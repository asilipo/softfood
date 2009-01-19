package it.softfood.facade.articolomenu;

import javax.ejb.Remote;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Remote
public interface ArticoloMenuFacadeRemote {

    it.softfood.entity.Articolo inserisciArticoloMenu(it.softfood.entity.Articolo articolo);

    it.softfood.entity.Articolo selezionaArticoloMenuPerId(java.lang.Long id);

    java.util.List<it.softfood.entity.Pietanza> selezionaPietanzePerTipo(it.softfood.enumeration.TipoPietanza tipoPietanza);

    java.util.List<it.softfood.entity.Pietanza> selezionaPietanze();

    java.util.List<it.softfood.entity.Bevanda> selezionaBevande();

    java.util.List<it.softfood.entity.Pietanza> selezionaPietanzeDisponibili();

    java.util.HashMap<it.softfood.entity.Pietanza, java.lang.Integer> selezionaDisponibilitaPietanze();

    java.util.List<it.softfood.entity.Pietanza> selezionaPietanzeDisponibiliPerTipo(it.softfood.enumeration.TipoPietanza tipoPietanza);

    java.util.HashMap<it.softfood.entity.Pietanza, java.lang.Integer> selezionaDisponibilitaPietanzePerTipo(it.softfood.enumeration.TipoPietanza tipoPietanza);

    boolean rimuoviArticoloMenu(Long id);
    
}
