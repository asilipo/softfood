package it.softfood.handler;

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
import it.softfood.session.BevandaMagazzinoSession;
import it.softfood.session.IngredienteMagazzinoSession;
import it.softfood.session.IngredientePietanzaSession;
import it.softfood.session.IngredienteSession;
import it.softfood.session.LineaOrdinazioneSession;
import it.softfood.session.OrdinazioneSession;
import it.softfood.session.TavoloSession;
import it.softfood.session.VarianteSession;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import java.util.StringTokenizer;
import javax.annotation.Resource;


/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */


public class OrdinazioneFacade {

	private static OrdinazioneFacade singleton;
	private TavoloSession tavoloSession=TavoloSession.getInstance();
	private OrdinazioneSession ordinazioneSession=OrdinazioneSession.getInstance();
	private LineaOrdinazioneSession lineaOrdinazioneSession=LineaOrdinazioneSession.getInstance();
	private IngredientePietanzaSession ingredientePietanzaSession=IngredientePietanzaSession.getInstance();
	private IngredienteMagazzinoSession ingredienteMagazzinoSession=IngredienteMagazzinoSession.getInstance();
	private IngredienteSession ingredienteSession=IngredienteSession.getInstance();
	private VarianteSession varianteSession=VarianteSession.getInstance();
	private BevandaMagazzinoSession bevandaMagazzinoSession=BevandaMagazzinoSession.getInstance();
	
	
public OrdinazioneFacade(){
		
		
	}
	
	public synchronized static OrdinazioneFacade getInstance() {
		if (singleton == null) {
			singleton = new OrdinazioneFacade();
		}
		return singleton;
	}
	
	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#inserisciOrdinazione(it.softfood.entity.Ordinazione)
	 */
	public Ordinazione inserisciOrdinazione(Ordinazione ordinazione) {
		if (ordinazione != null) {
            Tavolo tavolo = tavoloSession.selezionaTavoloPerId(ordinazione.getTavolo().getId());

            if (tavolo == null || tavolo.getNumeroPosti() < ordinazione.getCoperti()) {
                try {
                    if (tavolo.getRiferimento().contains("+")) {
                        String riferimento = tavolo.getRiferimento();
                        StringTokenizer st = new StringTokenizer(riferimento, "+");
                        while(st.hasMoreTokens()) {
                            String temp = st.nextToken();
                            Tavolo tavoloDaAttivare = tavoloSession.selezionaTavoloPerRiferimento(temp);
//                            tavoloDaAttivare = em.merge(tavoloDaAttivare);
                            tavoloDaAttivare.setAttivo(true);
                            tavoloSession.update(tavoloDaAttivare);
//                            tavoloDaAttivare.flush();
                        }
                        tavoloSession.rimuoviTavolo(tavolo.getId());
                    } else {
                        tavoloSession.modificaStatoTavolo(tavolo, false);
                    }
                } catch (Exception e) {
//                    ejbContext.setRollbackOnly();
                	System.out.println("ERROREEEEEEEEEEEEEEEEEEEEEEEE "+e);
                }

                return null;
            }

            try {
                ordinazione.setData(new Date(System.currentTimeMillis()));
                ordinazione.setSconto(0d);
                ordinazione.setTotale(0d);
                ordinazione.setTerminato(false);
                ordinazione=ordinazioneSession.inserisciOrdinazione(ordinazione);
                return ordinazione;
            } catch (Exception e) {
            	System.out.println("ERROREEEEEEEEEEEEEEEEEEEEEEEE "+e);
            }
        }

		return null;
	}

    private Integer verificaIngredientiPietanza(Pietanza pietanza) {
        ArrayList<IngredientePietanza> ingredientiPietanze = (ArrayList<IngredientePietanza>) ingredientePietanzaSession.selezionaIngredientiPietanze();
        ArrayList<IngredienteMagazzino> ingredientiMagazzino = (ArrayList<IngredienteMagazzino>) ingredienteMagazzinoSession.selezionaIngredientiMagazzino();
        Date data = new Date(System.currentTimeMillis());

        int disponibilita = 0;
        for (IngredientePietanza ingredientePietanza : ingredientiPietanze) {

            if (ingredientePietanza.getId().getPietanza().equals(pietanza.getId())) {
                Ingrediente ingrediente = ingredienteSession.selezionaIngredientePerId(ingredientePietanza.getId().getIngrediente());
                for (IngredienteMagazzino ingredienteMagazzino : ingredientiMagazzino)
                    if (ingredienteMagazzino.getIngrediente().getId().equals(ingrediente.getId()) && ingredienteMagazzino.getQuantita() >=
                            (ingredientePietanza.getQuantita() + (ingredientePietanza.getQuantita() * disponibilita)) && ingrediente.getScadenza().after(data))
                            disponibilita++;
            }
        }

        return disponibilita;
    }

    /* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#modificaOrdinazione(it.softfood.entity.Ordinazione, it.softfood.entity.Ordinazione)
	 */
    public Ordinazione modificaOrdinazione(Ordinazione nuovaOrdinazione, Ordinazione vecchiaOrdinazione) {
		if (nuovaOrdinazione != null && vecchiaOrdinazione != null) {
			Ordinazione ordinazione = ordinazioneSession.selezionaOrdinazionePerId(vecchiaOrdinazione.getId());
            ordinazione.setData(nuovaOrdinazione.getData());
            ordinazione.setSconto(nuovaOrdinazione.getSconto());
            ordinazione.setTotale(nuovaOrdinazione.getTotale());
            ordinazione.setTavolo(nuovaOrdinazione.getTavolo());
			ordinazione.setTerminato(nuovaOrdinazione.isTerminato());
			ordinazioneSession.update(ordinazione);
            return ordinazione;
		}

		return null;
	}
	
	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#selezionaOrdinazionePerId(java.lang.Long)
	 */
	public Ordinazione selezionaOrdinazionePerId(Long id) {
		if (id != null) 
			return ordinazioneSession.selezionaOrdinazionePerId(id);
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#selezionaOrdinazioni()
	 */
	public List<Ordinazione> selezionaOrdinazioni() {
		return ordinazioneSession.selezionaOrdinazioni();
	}
	
	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#selezionaOrdinazioniPerData(java.util.Date)
	 */
	public List<Ordinazione> selezionaOrdinazioniPerData(Date data) {
		if (data != null) 
			return ordinazioneSession.selezionaOrdinazioniPerData(data);
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#selezionaOrdinazioniGiornalierePerTavolo(it.softfood.entity.Tavolo, java.lang.Boolean)
	 */
	public List<Ordinazione> selezionaOrdinazioniGiornalierePerTavolo(Tavolo tavolo, Boolean terminato) {
		if (tavolo != null && terminato != null) 
			return ordinazioneSession.selezionaOrdinazioniGionalierePerTavolo(tavolo, terminato);
		
		return null;
	}

    /* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#selezionaOrdinazioneGiornalieraPerTavolo(it.softfood.entity.Tavolo, java.lang.Boolean)
	 */
    public Ordinazione selezionaOrdinazioneGiornalieraPerTavolo(Tavolo tavolo, Boolean terminato) {
		if (tavolo != null && terminato != null)
			return ((ArrayList<Ordinazione>)ordinazioneSession.selezionaOrdinazioniGionalierePerTavolo(tavolo, terminato)).get(0);

		return null;
	}

    /* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#selezionaOrdinazioneGiornalieraPerTavolo(java.lang.String, java.lang.Boolean)
	 */
    public Ordinazione selezionaOrdinazioneGiornalieraPerTavolo(String riferimentoTavolo, Boolean terminato) {
		if (riferimentoTavolo != null && terminato != null){
			ArrayList<Ordinazione> list=((ArrayList<Ordinazione>)ordinazioneSession.selezionaOrdinazioniGionalierePerTavolo(tavoloSession.selezionaTavoloPerRiferimento(riferimentoTavolo), terminato));
			return list.get(0);
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#rimuoviOrdinazione(java.lang.Long, java.lang.Boolean)
	 */
	public boolean rimuoviOrdinazione(Long id, Boolean ripristinaPietanze) {
		if (id != null) {
            Boolean statoEliminazione = false;
            Ordinazione ordinazione = ordinazioneSession.selezionaOrdinazionePerId(id);
            ArrayList<LineaOrdinazione> lineeOrdinazione = (ArrayList<LineaOrdinazione>) lineaOrdinazioneSession.selezionaLineeOrdinazionePerOrdinazione(ordinazione);

            if (lineeOrdinazione != null) {
                try {
                    for (LineaOrdinazione lineaOrdinazione : lineeOrdinazione) {
                        ArrayList<Variante> varianti = (ArrayList<Variante>) varianteSession.selezionaVariantiPerLineaOrdinazione(lineaOrdinazione);

                        if(varianti != null && varianti.size() > 0)
                            for (Variante variante : varianti)
                                varianteSession.rimuoviVariante(variante.getId());
                        
                        if (ripristinaPietanze) {
                            Articolo articolo = lineaOrdinazione.getArticolo();
                            if (articolo instanceof Pietanza) {
                                if (!this.aggiornaMagazzinoIngredienti(lineaOrdinazione, "+"))
                                    throw new Exception();
                            } else if (articolo instanceof Bevanda) {
                                if (!this.aggiornaMagazzinoBevande(lineaOrdinazione, "+"))
                                    throw new Exception();
                            }
                        }
                        lineaOrdinazioneSession.rimuoviLineaOrdinazione(lineaOrdinazione.getId());
                    }

                    Tavolo tavolo = tavoloSession.selezionaTavoloPerId(ordinazione.getTavolo().getId());
                    
                    if (tavolo.getRiferimento().contains("+")) {
                        String riferimento = tavolo.getRiferimento();
                        StringTokenizer st = new StringTokenizer(riferimento, "+");
                        while(st.hasMoreTokens()) {
                            String temp = st.nextToken();
                            Tavolo tavoloDaAttivare = tavoloSession.selezionaTavoloPerRiferimento(temp);
//                            tavoloDaAttivare = em.merge(tavoloDaAttivare);
                            tavoloDaAttivare.setAttivo(true);
                            tavoloSession.update(tavoloDaAttivare);
//                            em.flush();
                        }
                        statoEliminazione = ordinazioneSession.rimuoviOrdinazione(id);
                        tavoloSession.rimuoviTavolo(tavolo.getId());
                    } else {
                        tavoloSession.modificaStatoTavolo(tavolo, false);
                        statoEliminazione = ordinazioneSession.rimuoviOrdinazione(id);
                    }

                    return statoEliminazione;
                } catch (Exception e) {
                	System.out.println("ERROREEEEEEEEEEEEEEEEEEEEEEEE "+e);
                }
            }
        }

		return false;
	}
	
	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#inserisciLineaOrdinazione(it.softfood.entity.LineaOrdinazione)
	 */
	public LineaOrdinazione inserisciLineaOrdinazione(LineaOrdinazione lineaOrdinazione) {
		if (lineaOrdinazione != null) {
            try {
                lineaOrdinazione.setOrdinazione(lineaOrdinazione.getOrdinazione());
//                em.flush();
                lineaOrdinazione = lineaOrdinazioneSession.inserisciLineaOrdinazione(lineaOrdinazione);
                Articolo articolo = lineaOrdinazione.getArticolo();
                if (articolo instanceof Pietanza)
                    if (!this.aggiornaMagazzinoIngredienti(lineaOrdinazione, "-"))
                        throw new Exception();
                else
                    if (!this.aggiornaMagazzinoBevande(lineaOrdinazione, "-"))
                        throw  new Exception();

                return lineaOrdinazione;
            } catch (Exception e) {
            	System.out.println("ERROREEEEEEEEEEEEEEEEEEEEEEEE "+e);
            }
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#modificaLineaOrdinazione(it.softfood.entity.LineaOrdinazione, it.softfood.entity.LineaOrdinazione)
	 */
	public LineaOrdinazione modificaLineaOrdinazione(LineaOrdinazione nuovaLineaOrdinazione,
            LineaOrdinazione vecchiaLineaOrdinazione) {
        if (nuovaLineaOrdinazione != null && vecchiaLineaOrdinazione != null) {
			LineaOrdinazione lineaOrdinazione = vecchiaLineaOrdinazione;
            lineaOrdinazione.setArticolo(nuovaLineaOrdinazione.getArticolo());
            lineaOrdinazione.setOrdinazione(nuovaLineaOrdinazione.getOrdinazione());
            lineaOrdinazione.setQuantita(nuovaLineaOrdinazione.getQuantita());
            lineaOrdinazioneSession.update(lineaOrdinazione);
            return lineaOrdinazione;
		}

		return null;
	}

    /* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#selezionaLineaOrdinazionePerId(java.lang.Long)
	 */
    public LineaOrdinazione selezionaLineaOrdinazionePerId(Long id) {
        if (id != null)
			return lineaOrdinazioneSession.selezionaLineaOrdinazionePerId(id);

        return null;
    }
    
    /* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#selezionaLineeOrdinazionePerOrdinazione(it.softfood.entity.Ordinazione)
	 */
    public List<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazione(Ordinazione ordinazione) {
        if (ordinazione != null)
			return lineaOrdinazioneSession.selezionaLineeOrdinazionePerOrdinazione(ordinazione);

        return null;
    }

    /* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#selezionaLineeOrdinazionePerOrdinazione(it.softfood.entity.Ordinazione, it.softfood.enumeration.TipoPietanza)
	 */
    public List<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazione(Ordinazione ordinazione,
            TipoPietanza tipoPietanza) {
        ArrayList<LineaOrdinazione> lineeOrdinazione = new ArrayList<LineaOrdinazione>();
        if (ordinazione != null) 
			 lineeOrdinazione = (ArrayList<LineaOrdinazione>) lineaOrdinazioneSession.selezionaLineeOrdinazionePerOrdinazione(ordinazione);
        
        ArrayList<LineaOrdinazione> lineeOrdinazioneArticoli = new ArrayList<LineaOrdinazione> ();
        
        for (LineaOrdinazione lineaOrdinazione : lineeOrdinazione) {
            Articolo articolo = lineaOrdinazione.getArticolo();
            if (articolo instanceof Bevanda && tipoPietanza == null)
                lineeOrdinazioneArticoli.add(lineaOrdinazione);
            if (articolo instanceof Pietanza)
                if (((Pietanza)articolo).getTipo().equals(tipoPietanza))
                    lineeOrdinazioneArticoli.add(lineaOrdinazione);
            
        }

        return lineeOrdinazioneArticoli;
    }

    /* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#rimuoviLineaOrdinazione(java.lang.Long)
	 */
    public boolean rimuoviLineaOrdinazione(Long id) {
        if (id != null) {
            try {
                ArrayList<Variante> varianti = (ArrayList<Variante>) varianteSession.selezionaVariantiPerLineaOrdinazione(lineaOrdinazioneSession.selezionaLineaOrdinazionePerId(id));
                if (varianti != null && varianti.size() > 0) {
                    for (Variante variante : varianti) {
                        varianteSession.rimuoviVariante(variante.getId());
                    }
                }

                return lineaOrdinazioneSession.rimuoviLineaOrdinazione(id);
            } catch (Exception e){
            	System.out.println("ERROREEEEEEEEEEEEEEEEEEEEEEEE "+e);
            }
        }

		return false;
    }

    /* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#inserisciVariante(it.softfood.entity.Variante)
	 */
    public Variante inserisciVariante(Variante variante) {
        if (variante != null) {
            LineaOrdinazione lineaOrdinazione = variante.getLineaOrdinazione();
            Ingrediente ingrediente = variante.getIngrediente();

            if (lineaOrdinazione != null && ingrediente != null) {
                try {
//                    lineaOrdinazione = em.merge(lineaOrdinazione);
//                    ingrediente = em.merge(ingrediente);
//                    em.flush();

                    variante.setIngrediente(ingrediente);
                    variante.setLineaOrdinazione(lineaOrdinazione);
                    
                    variante = varianteSession.inserisciVariante(variante);
                    
                    return variante;
                } catch (Exception e) {
                	System.out.println("ERROREEEEEEEEEEEEEEEEEEEEEEEE "+e);
                }
            }
        }

        return null;
    }

    /* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#selezionaIngredientiPerVariante()
	 */
    public List<Ingrediente> selezionaIngredientiPerVariante() {
        ArrayList<IngredienteMagazzino> ingredientiMagazzino = (ArrayList<IngredienteMagazzino>) ingredienteMagazzinoSession.selezionaIngredientiMagazzino();
        ArrayList<Ingrediente> ingredienti = (ArrayList<Ingrediente>) ingredienteSession.selezionaIngredientePerVariante();

        if (ingredientiMagazzino != null && ingredienti != null) {
            ArrayList<Ingrediente> ingredientiVariante = new ArrayList<Ingrediente>();

            for (Ingrediente ingrediente : ingredienti) 
                for (IngredienteMagazzino ingredienteMagazzino : ingredientiMagazzino)
                    if (ingrediente.getId().equals(ingredienteMagazzino.getIngrediente().getId()) && ingredienteMagazzino.getQuantita() > 200)
                        ingredientiVariante.add(ingredienteMagazzino.getIngrediente());

            return ingredientiVariante;
        }
        
        return null;
    }

    /* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#modificaVariante(it.softfood.entity.Variante, it.softfood.entity.Variante)
	 */
    public Variante modificaVariante(Variante nuovaVariante, Variante vecchiaVariante) {
        if (nuovaVariante != null && vecchiaVariante != null) {
			Variante variante = vecchiaVariante;
            variante.setIngrediente(vecchiaVariante.getIngrediente());
            variante.setLineaOrdinazione(vecchiaVariante.getLineaOrdinazione());
            variante.setTipoVariazione(vecchiaVariante.getTipoVariazione());
            varianteSession.update(variante);
            return variante;
		}

		return null;
	}

    /* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#selezionaVariantePerId(java.lang.Long)
	 */
    public Variante selezionaVariantePerId(Long id) {
        if (id != null)
			return varianteSession.selezionaVariantePerId(id);

        return null;
    }

    /* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#selezionaIngredientePerNome(java.lang.String)
	 */
    public Ingrediente selezionaIngredientePerNome (String ingrediente) {
        if (ingrediente != null) {
            ArrayList<Ingrediente> ingredienti = (ArrayList<Ingrediente>) ingredienteSession.selezionaIngredientePerNome(ingrediente);

            if (ingredienti != null)
                return ingredienti.get(0);
        }

        return null;
    }

    /* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#selezionaVariantiPerIngrediente(it.softfood.entity.Ingrediente)
	 */
    public List<Variante> selezionaVariantiPerIngrediente(Ingrediente ingrediente) {
        return null;
    }

    /* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#selezionaVariantiPerLineaOrdinazione(it.softfood.entity.LineaOrdinazione)
	 */
    public List<Variante> selezionaVariantiPerLineaOrdinazione(LineaOrdinazione lineaOrdinazione) {
        if (lineaOrdinazione != null) {
            ArrayList<Variante> varianti =(ArrayList<Variante>) varianteSession.selezionaVariantiPerLineaOrdinazione(lineaOrdinazione);

            if (varianti != null && varianti.size() > 0)
                return varianti;
        }

        return null;
    }

    /* (non-Javadoc)
	 * @see it.softfood.handler.IOrdinazioneFacade#rimuoviVariante(java.lang.Long)
	 */
    public boolean rimuoviVariante(Long id) {
        if (id != null)
			return varianteSession.rimuoviVariante(id);

		return false;
    }

    private boolean aggiornaMagazzinoIngredienti(LineaOrdinazione lineaOrdinazione, String tipoAggiornamento) {
        try {
            ArrayList<IngredientePietanza> ingredientiPietanze = (ArrayList<IngredientePietanza>) ingredientePietanzaSession.selezionaIngredientiPietanze();
            ArrayList<IngredienteMagazzino> ingredientiMagazzino = (ArrayList<IngredienteMagazzino>) ingredienteMagazzinoSession.selezionaIngredientiMagazzino();
            if (ingredientiPietanze != null && ingredientiMagazzino != null) {
                for (IngredientePietanza ingredientePietanza : ingredientiPietanze) {
                    if (ingredientePietanza.getId().getPietanza().equals(lineaOrdinazione.getArticolo().getId())) {
                            for (IngredienteMagazzino ingredienteMagazzino : ingredientiMagazzino) {
                                if (ingredienteMagazzino.getIngrediente().getId().
                                        equals(ingredientePietanza.getId().getIngrediente())) {
//                                    ingredienteMagazzino = em.merge(ingredienteMagazzino);
                                    int quantita = ingredienteMagazzino.getQuantita();

                                    if (tipoAggiornamento.equals("+"))
                                        ingredienteMagazzino.setQuantita(quantita + (lineaOrdinazione.getQuantita() * ingredientePietanza.getQuantita()));
                                    else
                                         ingredienteMagazzino.setQuantita(quantita - (lineaOrdinazione.getQuantita() * ingredientePietanza.getQuantita()));

                                    ingredienteMagazzinoSession.update(ingredienteMagazzino);
                                }
                            }
                        }
                }
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        
        return false;
    }

    private boolean aggiornaMagazzinoBevande(LineaOrdinazione lineaOrdinazione, String tipoAggiornamento) {
        try {
            ArrayList<BevandaMagazzino> bevandeMagazzino = (ArrayList<BevandaMagazzino>) bevandaMagazzinoSession.selezionaBevandeMagazzino();
            if (bevandeMagazzino != null) {
                for (BevandaMagazzino bevandaMagazzino : bevandeMagazzino) {
                    if (bevandaMagazzino.getArticolo().getId().equals(lineaOrdinazione.getArticolo().getId())) {
//                        bevandaMagazzino = em.merge(bevandaMagazzino);
                        int quantita = bevandaMagazzino.getQuantita();

                        if (tipoAggiornamento.equals("+")) 
                            bevandaMagazzino.setQuantita(quantita + (lineaOrdinazione.getQuantita() * ((Bevanda) lineaOrdinazione.getArticolo()).getCapacita().intValue()));
                        else
                            bevandaMagazzino.setQuantita(quantita - (lineaOrdinazione.getQuantita() * ((Bevanda) lineaOrdinazione.getArticolo()).getCapacita().intValue()));

                        bevandaMagazzinoSession.update(bevandaMagazzino);
                    }
                }
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }
    
}
