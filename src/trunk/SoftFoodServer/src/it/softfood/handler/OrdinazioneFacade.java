package it.softfood.handler;

import it.softfood.entity.Articolo;
import it.softfood.entity.BevandaMagazzino;
import it.softfood.entity.Ingrediente;
import it.softfood.entity.IngredienteMagazzino;
import it.softfood.entity.IngredientePietanza;
import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.Ordinazione;
import it.softfood.entity.Pietanza;
import it.softfood.entity.Tavolo;
import it.softfood.entity.User;
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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

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
	
	
	public OrdinazioneFacade(){}
	
	public synchronized static OrdinazioneFacade getInstance() {
		if (singleton == null) {
			singleton = new OrdinazioneFacade();
		}
		return singleton;
	}
	
	public Ordinazione inserisciOrdinazione(User role,Ordinazione ordinazione) {
		if (ordinazione != null) {
            Tavolo tavolo = tavoloSession.selezionaTavoloPerId(ordinazione.getTavolo().getId());

            if (tavolo == null || tavolo.getNumeroPosti() < ordinazione.getCoperti()) {
                try {
                    if (tavolo.getRiferimento().contains("+")) {
                        String riferimento = tavolo.getRiferimento();
                        StringTokenizer st = new StringTokenizer(riferimento, "+");
                        while(st.hasMoreTokens()) {
                            String temp = st.nextToken();
                            Tavolo tavoloDaAttivare = tavoloSession.selezionaTavoloPerRiferimento(temp, false);
                            tavoloDaAttivare.setAttivo(true);
                            tavoloSession.update(tavoloDaAttivare);
                        }
                        tavoloSession.rimuoviTavolo(tavolo.getId());
                    } else {
                        tavoloSession.modificaStatoTavolo(tavolo, false);
                    }
                } catch (Exception e) {
                	System.out.println("OrdinazioneFacade#inserisciOrdinazione " + e);
                }

                return null;
            }

            try {    		    
                ordinazione.setData(new Timestamp(System.currentTimeMillis()));
                ordinazione.setSconto(0d);
                ordinazione.setTotale(0d);
                ordinazione.setTerminato(false);
                ordinazione = ordinazioneSession.inserisciOrdinazione(ordinazione);
                return ordinazione;
            } catch (Exception e) {
            	System.out.println("OrdinazioneFacade#inserisciOrdinazione " + e);
            }
        }

		return null;
	}

    private Integer verificaIngredientiPietanza(User role, Pietanza pietanza) {
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

    public Ordinazione modificaOrdinazione(User role,Ordinazione nuovaOrdinazione, Ordinazione vecchiaOrdinazione) {
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

	public Ordinazione selezionaOrdinazionePerId(User role,Long id) {
		if (id != null) {
			Ordinazione ord=ordinazioneSession.selezionaOrdinazionePerId(id);
			return ord;
		}
		return null;
	}
	
	public ArrayList<Ordinazione> selezionaOrdinazioni(User role) {
		return (ArrayList<Ordinazione>) ordinazioneSession.selezionaOrdinazioni();
	}
	
	public ArrayList<Ordinazione> selezionaOrdinazioniGiornaliere(User role) {
		return (ArrayList<Ordinazione>) ordinazioneSession.selezionaOrdinazioniGiornaliere();
	}
	
	public ArrayList<Ordinazione> selezionaOrdinazioniGiornalierePerTavolo(User role, Tavolo tavolo, Boolean terminato) {
		if (tavolo != null && terminato != null) 
			return (ArrayList<Ordinazione>) ordinazioneSession.selezionaOrdinazioniGionalierePerTavolo(tavolo, terminato);
		
		return null;
	}

    public Ordinazione selezionaOrdinazioneGiornalieraPerTavolo(User role,String riferimentoTavolo, Boolean terminato) {
		if (riferimentoTavolo != null && terminato != null){
			ArrayList<Ordinazione> list=((ArrayList<Ordinazione>)ordinazioneSession.selezionaOrdinazioniGionalierePerTavolo(tavoloSession.selezionaTavoloPerRiferimento(riferimentoTavolo, true), terminato));
			
			return list.get(0);
		}
		return null;
	}
	
	public boolean rimuoviOrdinazione(User role,Long id, Boolean ripristinaPietanze) {
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
                            if (articolo.getTipoArticolo().equals("Pietanza")) {
                                if (!this.aggiornaMagazzinoIngredienti(role,lineaOrdinazione, "+"))
                                    throw new Exception();
                            } else {
                                if (!this.aggiornaMagazzinoBevande(role,lineaOrdinazione, "+"))
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
                            Tavolo tavoloDaAttivare = tavoloSession.selezionaTavoloPerRiferimento(temp, false);                       
                            tavoloDaAttivare.setAttivo(true);
                            tavoloSession.update(tavoloDaAttivare);
                        }
                        statoEliminazione = ordinazioneSession.rimuoviOrdinazione(id);
                        tavoloSession.rimuoviTavolo(tavolo.getId());
                    } else {
                        tavoloSession.modificaStatoTavolo(tavolo, false);
                        statoEliminazione = ordinazioneSession.rimuoviOrdinazione(id);
                    }

                    return statoEliminazione;
                } catch (Exception e) {
                	System.out.println("OrdinazioneFacade#rimuoviOrdinazione: " + e);
                }
            }
        }

		return false;
	}
	
	public LineaOrdinazione inserisciLineaOrdinazione(User role, LineaOrdinazione lineaOrdinazione) {
		if (lineaOrdinazione != null) {
            try {
            	lineaOrdinazione.setOrdinazione(lineaOrdinazione.getOrdinazione());

                lineaOrdinazione = lineaOrdinazioneSession.inserisciLineaOrdinazione(lineaOrdinazione);
                
                Articolo articolo = lineaOrdinazione.getArticolo();
                
                if (articolo.getTipoArticolo().equals("Pietanza")) {
                    if (!this.aggiornaMagazzinoIngredienti(role, lineaOrdinazione, "-"))
                        throw new Exception();
                } else {
                	if (!this.aggiornaMagazzinoBevande(role, lineaOrdinazione, "-"))
                        throw  new Exception();
                }
                return lineaOrdinazione;
            } catch (Exception e) {
            	System.out.println("OrdinazioneFacade#inserisciLineaOrdinazione " + e);
            }
		}
		
		return null;
	}
	
	public LineaOrdinazione modificaLineaOrdinazione(User role,LineaOrdinazione nuovaLineaOrdinazione,
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

    public LineaOrdinazione selezionaLineaOrdinazionePerId(User role,Long id) {
        if (id != null)
			return lineaOrdinazioneSession.selezionaLineaOrdinazionePerId(id);

        return null;
    }
    
    public ArrayList<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazione(User role,Ordinazione ordinazione) {
        if (ordinazione != null){
        	ArrayList<LineaOrdinazione> linea=(ArrayList<LineaOrdinazione>) lineaOrdinazioneSession.selezionaLineeOrdinazionePerOrdinazione(ordinazione);
			return linea;
        }
        return null;
    }

    public ArrayList<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazioneTipoPietanza(User role,Ordinazione ordinazione, TipoPietanza tipoPietanza) {
        ArrayList<LineaOrdinazione> lineeOrdinazione = new ArrayList<LineaOrdinazione>();
        if (ordinazione != null) 
			 lineeOrdinazione = (ArrayList<LineaOrdinazione>) lineaOrdinazioneSession.selezionaLineeOrdinazionePerOrdinazione(ordinazione);
        
        ArrayList<LineaOrdinazione> lineeOrdinazioneArticoli = new ArrayList<LineaOrdinazione> ();
        
        for (LineaOrdinazione lineaOrdinazione : lineeOrdinazione) {
            Articolo articolo = lineaOrdinazione.getArticolo();
            if (articolo.getTipoArticolo().equals("Bevanda") && tipoPietanza.equals(TipoPietanza.BEVANDA))
                lineeOrdinazioneArticoli.add(lineaOrdinazione);
            if (articolo.getTipoArticolo().equals("Pietanza"))
                if (articolo.getTipoPietanza()==(tipoPietanza.ordinal()))
                    lineeOrdinazioneArticoli.add(lineaOrdinazione);
        }

        return lineeOrdinazioneArticoli;
    }

    public boolean rimuoviLineaOrdinazione(User role,Long id) {
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
            	System.out.println("OrdinazioneFacade#rimuoviLineaOrdinazione " + e);
            }
        }

		return false;
    }

    public Variante inserisciVariante(User role, Variante variante) {
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
                	System.out.println("OrdinazioneFacade#inserisciVariante: " + e);
                }
            }
        }

        return null;
    }

    public ArrayList<Ingrediente> selezionaIngredientiPerVariante (User role) {
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

    public Variante modificaVariante(User role,Variante nuovaVariante, Variante vecchiaVariante) {
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

    public Variante selezionaVariantePerId(User role,Long id) {
        if (id != null)
			return varianteSession.selezionaVariantePerId(id);

        return null;
    }

    public Ingrediente selezionaIngredientePerNome (User role,String ingrediente) {
        if (ingrediente != null) {
            ArrayList<Ingrediente> ingredienti = (ArrayList<Ingrediente>) ingredienteSession.selezionaIngredientePerNome(ingrediente);

            if (ingredienti != null)
                return ingredienti.get(0);
        }

        return null;
    }

    public ArrayList<Variante> selezionaVariantiPerIngrediente(User role,Ingrediente ingrediente) {
        return null;
    }

    public ArrayList<Variante> selezionaVariantiPerLineaOrdinazione(User role,LineaOrdinazione lineaOrdinazione) {
        if (lineaOrdinazione != null) {
            ArrayList<Variante> varianti =(ArrayList<Variante>) varianteSession.selezionaVariantiPerLineaOrdinazione(lineaOrdinazione);

            if (varianti != null && varianti.size() > 0)
                return varianti;
        }

        return null;
    }

    public boolean rimuoviVariante(User role,Long id) {
        if (id != null)
			return varianteSession.rimuoviVariante(id);

		return false;
    }

    private boolean aggiornaMagazzinoIngredienti(User role, LineaOrdinazione lineaOrdinazione, String tipoAggiornamento) {
        try {
            ArrayList<IngredientePietanza> ingredientiPietanze = (ArrayList<IngredientePietanza>) ingredientePietanzaSession.selezionaIngredientiPietanze();
            ArrayList<IngredienteMagazzino> ingredientiMagazzino = (ArrayList<IngredienteMagazzino>) ingredienteMagazzinoSession.selezionaIngredientiMagazzino();
            if (ingredientiPietanze != null && ingredientiMagazzino != null) {
                for (IngredientePietanza ingredientePietanza : ingredientiPietanze) {
                    if (ingredientePietanza.getId().getPietanza().equals(lineaOrdinazione.getArticolo().getId())) {
                            for (IngredienteMagazzino ingredienteMagazzino : ingredientiMagazzino) {
                                if (ingredienteMagazzino.getIngrediente().getId().
                                        equals(ingredientePietanza.getId().getIngrediente())) {
                                	
                                	int quantita = ingredienteMagazzino.getQuantita();
                                	if (tipoAggiornamento.equalsIgnoreCase("+"))
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

    private boolean aggiornaMagazzinoBevande(User role,LineaOrdinazione lineaOrdinazione, String tipoAggiornamento) {
        try {
            ArrayList<BevandaMagazzino> bevandeMagazzino = (ArrayList<BevandaMagazzino>) bevandaMagazzinoSession.selezionaBevandeMagazzino();
            if (bevandeMagazzino != null) {
                for (BevandaMagazzino bevandaMagazzino : bevandeMagazzino) {
                    if (bevandaMagazzino.getArticolo().getId().equals(lineaOrdinazione.getArticolo().getId())) {
                    	int quantita = bevandaMagazzino.getQuantita();
                    	
                    	if (tipoAggiornamento.equalsIgnoreCase("+")) {
                            bevandaMagazzino.setQuantita(quantita + (lineaOrdinazione.getQuantita() * (lineaOrdinazione.getArticolo()).getCapacita().intValue()));
                        } else {
                        	bevandaMagazzino.setQuantita(quantita - (lineaOrdinazione.getQuantita() * (lineaOrdinazione.getArticolo()).getCapacita().intValue()));
                        }
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
