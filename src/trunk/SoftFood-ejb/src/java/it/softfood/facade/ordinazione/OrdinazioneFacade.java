package it.softfood.facade.ordinazione;

import it.softfood.entity.Articolo;
import it.softfood.entity.Bevanda;
import it.softfood.entity.BevandaMagazzino;
import it.softfood.entity.Ingrediente;
import it.softfood.entity.IngredienteMagazzino;
import it.softfood.entity.IngredientePietanza;
import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.Ordinazione;
import it.softfood.entity.Pietanza;
import it.softfood.entity.Tavolo;
import it.softfood.entity.Variante;
import it.softfood.enumeration.TipoPietanza;
import it.softfood.session.bevandamagazzino.BevandaMagazzinoSessionBeanRemote;
import it.softfood.session.ingredientemagazzino.IngredienteMagazzinoSessionBeanRemote;
import it.softfood.session.ingredientepietanza.IngredientePietanzaSessionBeanRemote;
import it.softfood.session.lineaordinazione.LineaOrdinazioneSessionBeanRemote;
import it.softfood.session.ordinazione.OrdinazioneSessionBeanRemote;

import it.softfood.session.tavolo.TavoloSessionBeanRemote;
import it.softfood.session.variante.VarianteSessionBeanRemote;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import java.util.StringTokenizer;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

@Stateless
public class OrdinazioneFacade implements OrdinazioneFacadeRemote, OrdinazioneFacadeLocal {

    @Resource
    private EJBContext ejbContext;
	@PersistenceContext
    private EntityManager em;
	@EJB(beanName = "OrdinazioneSessionBean")
	private OrdinazioneSessionBeanRemote ordinazioneSessionBean;
	@EJB(beanName = "LineaOrdinazioneSessionBean")
	private LineaOrdinazioneSessionBeanRemote lineaOrdinazioneSessionBean;
    @EJB(beanName = "VarianteSessionBean")
	private VarianteSessionBeanRemote varianteSessionBean;
    @EJB(beanName = "IngredientePietanzaSessionBean")
	private IngredientePietanzaSessionBeanRemote ingredientePietanzaSessionBeanRemote;
    @EJB(beanName = "IngredienteMagazzinoSessionBean")
	private IngredienteMagazzinoSessionBeanRemote ingredienteMagazzinoSessionBeanRemote;
    @EJB(beanName = "BevandaMagazzinoSessionBean")
	private BevandaMagazzinoSessionBeanRemote bevandaMagazzinoSessionBeanRemote;
    @EJB(beanName = "TavoloSessionBean")
	private TavoloSessionBeanRemote tavoloSessionBeanRemote;

	public Ordinazione inserisciOrdinazione(Ordinazione ordinazione) {
		if (ordinazione != null) {
            Tavolo tavolo = tavoloSessionBeanRemote.selezionaTavoloPerId(ordinazione.getTavolo().getId());

            if (tavolo == null || tavolo.getNumeroPosti() < ordinazione.getCoperti()) {
                try {
                    if (tavolo.getRiferimento().contains("+")) {
                        String riferimento = tavolo.getRiferimento();
                        StringTokenizer st = new StringTokenizer(riferimento, "+");
                        while(st.hasMoreTokens()) {
                            String temp = st.nextToken();
                            Tavolo tavoloDaAttivare = tavoloSessionBeanRemote.selezionaTavoloPerRiferimento(temp);
                            tavoloDaAttivare = em.merge(tavoloDaAttivare);
                            tavoloDaAttivare.setAttivo(true);
                            em.flush();
                        }
                        tavoloSessionBeanRemote.rimuoviTavolo(tavolo.getId());
                    } else {
                        tavoloSessionBeanRemote.modificaStatoTavolo(tavolo, false);
                    }
                } catch (Exception e) {
                    ejbContext.setRollbackOnly();
                }

                return null;
            }

            try {
                ordinazione.setData(new Date(System.currentTimeMillis()));
                ordinazione.setSconto(0d);
                ordinazione.setTotale(0d);
                ordinazione.setTerminato(false);
                return ordinazioneSessionBean.inserisciOrdinazione(ordinazione);
            } catch (Exception e) {
                ejbContext.setRollbackOnly();
            }
        }

		return null;
	}

    private Integer verificaIngredientiPietanza(Pietanza pietanza) {
        ArrayList<IngredientePietanza> ingredientiPietanze = (ArrayList<IngredientePietanza>) ingredientePietanzaSessionBeanRemote.selezionaIngredientiPietanze();
        ArrayList<IngredienteMagazzino> ingredientiMagazzino = (ArrayList<IngredienteMagazzino>) ingredienteMagazzinoSessionBeanRemote.selezionaIngredientiMagazzino();
        Date data = new Date(System.currentTimeMillis());

        int disponibilita = 0;
        for (IngredientePietanza ingredientePietanza : ingredientiPietanze) {

            if (ingredientePietanza.getIngredientePietanzaPK().getPietanza().getId().equals(pietanza.getId())) {
                Ingrediente ingrediente = ingredientePietanza.getIngredientePietanzaPK().getIngrediente();
                System.out.println("Pietanza " + pietanza.getNome() + " ingrediente " + ingrediente.getNome());
                for (IngredienteMagazzino ingredienteMagazzino : ingredientiMagazzino)
                    if (ingredienteMagazzino.getIngredienteLungaConservazione().getId().equals(ingrediente.getId()) && ingredienteMagazzino.getQuantita() >=
                            (ingredientePietanza.getQuantita() + (ingredientePietanza.getQuantita() * disponibilita)) && ingrediente.getScadenza().after(data))
                            disponibilita++;
            }
        }

        return disponibilita;
    }

    public Ordinazione modificaOrdinazione(Ordinazione nuovaOrdinazione, Ordinazione vecchiaOrdinazione) {
		if (nuovaOrdinazione != null && vecchiaOrdinazione != null) {
			Ordinazione ordinazione = em.merge(vecchiaOrdinazione);
            ordinazione.setData(nuovaOrdinazione.getData());
            ordinazione.setSconto(nuovaOrdinazione.getSconto());
            ordinazione.setTotale(nuovaOrdinazione.getTotale());
            ordinazione.setTavolo(nuovaOrdinazione.getTavolo());
			ordinazione.setTerminato(nuovaOrdinazione.getTerminato());

            return ordinazione;
		}

		return null;
	}
	
	public Ordinazione selezionaOrdinazionePerId(Long id) {
		if (id != null) 
			return ordinazioneSessionBean.selezionaOrdinazionePerId(id);
		
		return null;
	}
	
	public List<Ordinazione> selezionaOrdinazioni() {
		return ordinazioneSessionBean.selezionaOrdinazioni();
	}
	
	public List<Ordinazione> selezionaOrdinazioniPerData(Date data) {
		if (data != null) 
			return ordinazioneSessionBean.selezionaOrdinazioniPerData(data);
		
		return null;
	}
	
	public List<Ordinazione> selezionaOrdinazioniGiornalierePerTavolo(Tavolo tavolo, Boolean terminato) {
		if (tavolo != null && terminato != null) 
			return ordinazioneSessionBean.selezionaOrdinazioniGionalierePerTavolo(tavolo, terminato);
		
		return null;
	}

    public Ordinazione selezionaOrdinazioneGiornalieraPerTavolo(Tavolo tavolo, Boolean terminato) {
		if (tavolo != null && terminato != null)
			return ((ArrayList<Ordinazione>)ordinazioneSessionBean.selezionaOrdinazioniGionalierePerTavolo(tavolo, terminato)).get(0);

		return null;
	}

    public Ordinazione selezionaOrdinazioneGiornalieraPerTavolo(String riferimentoTavolo, Boolean terminato) {
		if (riferimentoTavolo != null && terminato != null)
			return ((ArrayList<Ordinazione>)ordinazioneSessionBean.selezionaOrdinazioniGionalierePerTavolo(tavoloSessionBeanRemote.selezionaTavoloPerRiferimento(riferimentoTavolo), terminato)).get(0);

		return null;
	}
	
	public boolean rimuoviOrdinazione(Long id) {
		if (id != null) {
            Ordinazione ordinazione = ordinazioneSessionBean.selezionaOrdinazionePerId(id);
            ArrayList<LineaOrdinazione> lineeOrdinazione = (ArrayList<LineaOrdinazione>) lineaOrdinazioneSessionBean.selezionaLineeOrdinazionePerOrdinazione(ordinazione);

            if (lineeOrdinazione != null) {
                try {
                    for (LineaOrdinazione lineaOrdinazione : lineeOrdinazione)
                        lineaOrdinazioneSessionBean.rimuoviLineaOrdinazione(lineaOrdinazione.getId());

                    Tavolo tavolo = tavoloSessionBeanRemote.selezionaTavoloPerId(ordinazione.getTavolo().getId());
                    
                    if (tavolo.getRiferimento().contains("+")) {
                        String riferimento = tavolo.getRiferimento();
                        StringTokenizer st = new StringTokenizer(riferimento, "+");
                        while(st.hasMoreTokens()) {
                            String temp = st.nextToken();
                            Tavolo tavoloDaAttivare = tavoloSessionBeanRemote.selezionaTavoloPerRiferimento(temp);
                            tavoloDaAttivare = em.merge(tavoloDaAttivare);
                            tavoloDaAttivare.setAttivo(true);
                            em.flush();
                        }
                        
                        tavoloSessionBeanRemote.rimuoviTavolo(tavolo.getId());
                    } else {
                        tavoloSessionBeanRemote.modificaStatoTavolo(tavolo, false);
                    }

                    return ordinazioneSessionBean.rimuoviOrdinazione(id);
                } catch (Exception e) {
                    ejbContext.setRollbackOnly();
                }
            }
        }

		return false;
	}
	
	public LineaOrdinazione inserisciLineaOrdinazione(LineaOrdinazione lineaOrdinazione) {
		if (lineaOrdinazione != null) {
            try {
                lineaOrdinazione.setOrdinazione(em.merge(lineaOrdinazione.getOrdinazione()));
                em.flush();
                lineaOrdinazione = lineaOrdinazioneSessionBean.inserisciLineaOrdinazione(lineaOrdinazione);
                Articolo articolo = lineaOrdinazione.getArticolo();
                if (articolo instanceof Pietanza) {
                    ArrayList<IngredientePietanza> ingredientiPietanze = (ArrayList<IngredientePietanza>) ingredientePietanzaSessionBeanRemote.selezionaIngredientiPietanze();
                    ArrayList<IngredienteMagazzino> ingredientiMagazzino = (ArrayList<IngredienteMagazzino>) ingredienteMagazzinoSessionBeanRemote.selezionaIngredientiMagazzino();
                    if (ingredientiPietanze != null && ingredientiMagazzino != null) {
                        for (IngredientePietanza ingredientePietanza : ingredientiPietanze) {
                            if (ingredientePietanza.getIngredientePietanzaPK().getPietanza().getId().equals(articolo.getId())) {
                                for (IngredienteMagazzino ingredienteMagazzino : ingredientiMagazzino) {
                                    if (ingredienteMagazzino.getIngredienteLungaConservazione().getId().
                                            equals(ingredientePietanza.getIngredientePietanzaPK().getIngrediente().getId())) {
                                        ingredienteMagazzino = em.merge(ingredienteMagazzino);
                                        int quantita = ingredienteMagazzino.getQuantita();
                                        ingredienteMagazzino.setQuantita(quantita - (lineaOrdinazione.getQuantita() * ingredientePietanza.getQuantita()));
                                    }
                                }
                            }
                        }
                    } else {
                        ejbContext.setRollbackOnly();
                    }
                } else {
                    ArrayList<BevandaMagazzino> bevandeMagazzino = (ArrayList<BevandaMagazzino>) bevandaMagazzinoSessionBeanRemote.selezionaBevandeMagazzino();
                    if (bevandeMagazzino != null) {
                        for (BevandaMagazzino bevandaMagazzino : bevandeMagazzino) {
                            if (bevandaMagazzino.getBevanda().getId().equals(articolo.getId())) {
                                bevandaMagazzino = em.merge(bevandaMagazzino);
                                int quantita = bevandaMagazzino.getQuantita();
                                bevandaMagazzino.setQuantita(quantita - (lineaOrdinazione.getQuantita() * ((Bevanda) articolo).getCapacita().intValue()));
                                em.flush();
                            }
                        }
                    } else {
                        ejbContext.setRollbackOnly();
                    }
                }
            } catch (Exception e) {
                ejbContext.setRollbackOnly();
            }
		}
		
		return null;
	}
	
	public LineaOrdinazione modificaLineaOrdinazione(LineaOrdinazione nuovaLineaOrdinazione,
            LineaOrdinazione vecchiaLineaOrdinazione) {
        if (nuovaLineaOrdinazione != null && vecchiaLineaOrdinazione != null) {
			LineaOrdinazione lineaOrdinazione = em.merge(vecchiaLineaOrdinazione);
            lineaOrdinazione.setArticolo(nuovaLineaOrdinazione.getArticolo());
            lineaOrdinazione.setOrdinazione(nuovaLineaOrdinazione.getOrdinazione());
            lineaOrdinazione.setQuantita(nuovaLineaOrdinazione.getQuantita());

            return lineaOrdinazione;
		}

		return null;
	}

    public LineaOrdinazione selezionaLineaOrdinazionePerId(Long id) {
        if (id != null)
			return lineaOrdinazioneSessionBean.selezionaLineaOrdinazionePerId(id);

        return null;
    }
    
    public List<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazione(Ordinazione ordinazione) {
        if (ordinazione != null)
			return lineaOrdinazioneSessionBean.selezionaLineeOrdinazionePerOrdinazione(ordinazione);

        return null;
    }

    public List<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazione(Ordinazione ordinazione,
            TipoPietanza tipoPietanza) {
        ArrayList<LineaOrdinazione> lineeOrdinazione = new ArrayList<LineaOrdinazione>();
        if (ordinazione != null) 
			 lineeOrdinazione = (ArrayList<LineaOrdinazione>) lineaOrdinazioneSessionBean.selezionaLineeOrdinazionePerOrdinazione(ordinazione);
        
        ArrayList<LineaOrdinazione> lineeOrdinazioneArticoli = new ArrayList<LineaOrdinazione> ();
        
        for (LineaOrdinazione lineaOrdinazione : lineeOrdinazione) {
            Articolo articolo = lineaOrdinazione.getArticolo();
            if (articolo instanceof Bevanda) 
                lineeOrdinazioneArticoli.add(lineaOrdinazione);
            else 
                if (((Pietanza)articolo).getTipo().equals(tipoPietanza))
                    lineeOrdinazioneArticoli.add(lineaOrdinazione);
            
        }

        return lineeOrdinazioneArticoli;
    }

    public boolean rimuoviLineaOrdinazione(Long id) {
        if (id != null)
			return lineaOrdinazioneSessionBean.rimuoviLineaOrdinazione(id);

		return false;
    }

    public Variante inserisciVariante(Variante variante) {
        if (variante != null)
            return varianteSessionBean.inserisciVariante(variante);

        return null;
    }

    public Variante modificaVariante(Variante nuovaVariante, Variante vecchiaVariante) {
        if (nuovaVariante != null && vecchiaVariante != null) {
			Variante variante = em.merge(vecchiaVariante);
            variante.setIngrediente(vecchiaVariante.getIngrediente());
            variante.setLineaOrdinazione(vecchiaVariante.getLineaOrdinazione());
            variante.setTipoVariazione(vecchiaVariante.getTipoVariazione());

            return variante;
		}

		return null;
	}

    public Variante selezionaVariantePerId(Long id) {
        if (id != null)
			return varianteSessionBean.selezionaVariantePerId(id);

        return null;
    }

    public List<Variante> selezionaVariantiPerIngrediente(Ingrediente ingrediente) {
        return null;
    }

    public List<Variante> selezionaVariantiPerLineaOrdinazione(LineaOrdinazione lineaOrdinazione) {
        return null;
    }

    public boolean rimuoviVariante(Long id) {
        if (id != null)
			return varianteSessionBean.rimuoviVariante(id);

		return false;
    }

}
