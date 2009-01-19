package it.softfood.facade.articolomenu;

import it.softfood.entity.Articolo;
import it.softfood.entity.Bevanda;
import it.softfood.entity.Ingrediente;
import it.softfood.entity.IngredienteLungaConservazione;
import it.softfood.entity.IngredienteMagazzino;
import it.softfood.entity.IngredientePietanza;
import it.softfood.entity.Pietanza;
import it.softfood.enumeration.TipoPietanza;
import it.softfood.session.articolo.ArticoloSessionBeanRemote;
import it.softfood.session.bevanda.BevandaSessionBeanRemote;
import it.softfood.session.ingredientemagazzino.IngredienteMagazzinoSessionBeanRemote;
import it.softfood.session.ingredientepietanza.IngredientePietanzaSessionBeanRemote;
import it.softfood.session.pietanza.PietanzaSessionBeanRemote;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Stateless
public class ArticoloMenuFacade implements ArticoloMenuFacadeRemote, ArticoloMenuFacadeLocal {
    
    @PersistenceContext
    private EntityManager em;
	@EJB(beanName = "ArticoloSessionBean")
	private ArticoloSessionBeanRemote articoloSessionBean;
    @EJB(beanName = "PietanzaSessionBean")
	private PietanzaSessionBeanRemote pietanzaSessionBean;
    @EJB(beanName = "BevandaSessionBean")
	private BevandaSessionBeanRemote bevandaSessionBean;
    @EJB(beanName = "IngredientePietanzaSessionBean")
	private IngredientePietanzaSessionBeanRemote ingredientePietanzaSessionBeanRemote;
    @EJB(beanName = "IngredienteMagazzinoSessionBean")
	private IngredienteMagazzinoSessionBeanRemote ingredienteMagazzinoSessionBeanRemote;
    
    public Articolo inserisciArticoloMenu(Articolo articolo) {
        if (articolo != null)
            return articoloSessionBean.inserisciArticolo(articolo);

        return null;
    }

    public Articolo selezionaArticoloMenuPerId(Long id) {
        if (id != null)
			return articoloSessionBean.selezionaArticoloPerId(id);

		return null;
    }

    public List<Pietanza> selezionaPietanzePerTipo(TipoPietanza tipoPietanza) {
        if (tipoPietanza != null)
            return pietanzaSessionBean.selezionaPietanzePerTipo(tipoPietanza);
        
        return null;
    }

    public List<Pietanza> selezionaPietanze() {
        return pietanzaSessionBean.selezionaPietanze();
    }

    public List<Pietanza> selezionaPietanzeDisponibili() {
        LinkedList<Pietanza> pietanze = (LinkedList<Pietanza>) pietanzaSessionBean.selezionaPietanze();
        LinkedList<Pietanza> pietanzeDisponibili = null;

        for (Pietanza pietanza : pietanze) {
            if (this.verificaIngredientiPietanza(pietanza) > 0)
                pietanzeDisponibili.add(pietanza);
        }
        
        return pietanzeDisponibili;
    }

    public List<Pietanza> selezionaPietanzeDisponibiliPerTipo(TipoPietanza tipoPietanza) {
        LinkedList<Pietanza> pietanze = (LinkedList<Pietanza>) pietanzaSessionBean.selezionaPietanzePerTipo(tipoPietanza);
        LinkedList<Pietanza> pietanzeDisponibili = null;

        for (Pietanza pietanza : pietanze) {
            if (this.verificaIngredientiPietanza(pietanza) > 0)
                pietanzeDisponibili.add(pietanza);
        }

        return pietanzeDisponibili;
    }

    public HashMap<Pietanza, Integer> selezionaDisponibilitaPietanze() {
        LinkedList<Pietanza> pietanze = (LinkedList<Pietanza>) pietanzaSessionBean.selezionaPietanze();
        HashMap<Pietanza, Integer> pietanzeDisponibili = null;

        for (Pietanza pietanza : pietanze)
            pietanzeDisponibili.put(pietanza, this.verificaIngredientiPietanza(pietanza));

        return pietanzeDisponibili;
    }

    public HashMap<Pietanza, Integer> selezionaDisponibilitaPietanzePerTipo(TipoPietanza tipoPietanza) {
        LinkedList<Pietanza> pietanze = (LinkedList<Pietanza>) pietanzaSessionBean.selezionaPietanzePerTipo(tipoPietanza);
        HashMap<Pietanza, Integer> pietanzeDisponibili = null;

        for (Pietanza pietanza : pietanze)
            pietanzeDisponibili.put(pietanza, this.verificaIngredientiPietanza(pietanza));

        return pietanzeDisponibili;
    }

    private Integer verificaIngredientiPietanza(Pietanza pietanza) {
        LinkedList<IngredientePietanza> ingredientiPietanze = (LinkedList<IngredientePietanza>) ingredientePietanzaSessionBeanRemote.selezionaIngredientiPietanze();
        LinkedList<IngredienteMagazzino> ingredientiMagazzino = (LinkedList<IngredienteMagazzino>) ingredienteMagazzinoSessionBeanRemote.selezionaIngredientiMagazzino();
        Date data = new Date(System.currentTimeMillis());

        int disponibilita = 0;
        for (IngredientePietanza ingredientePietanza : ingredientiPietanze) {
            if (ingredientePietanza.getIngredientePietanzaPK().getPietanza().equals(pietanza)) {
                Ingrediente ingrediente = ingredientePietanza.getIngredientePietanzaPK().getIngrediente();

                for (IngredienteMagazzino ingredienteMagazzino : ingredientiMagazzino)
                    if (ingredienteMagazzino.getIngredienteLungaConservazione().equals(ingrediente) && ingredienteMagazzino.getQuantita() >=
                            ingredientePietanza.getQuantita() && ingrediente.getScadenza().after(data))
                            disponibilita++;
            }
        }

        return disponibilita;
    }

    public List<Bevanda> selezionaBevande() {
        return bevandaSessionBean.selezionaBevande();
    }
    
    public boolean rimuoviArticoloMenu(Long id) {
        if (id != null)
			return articoloSessionBean.rimuoviBevanda(id);
        
		return false;
    }

}
