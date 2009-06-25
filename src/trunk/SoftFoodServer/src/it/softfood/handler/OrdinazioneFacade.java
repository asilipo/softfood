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
import it.softfood.entity.User;
import it.softfood.entity.Variante;
import it.softfood.enumeration.TipoPietanza;
import it.softfood.exception.AggiornamentoIngredientiMagazzinoException;
import it.softfood.exception.DisponibilitaBevandaException;
import it.softfood.exception.DisponibilitaPietanzaException;
import it.softfood.exception.TavoloOccupatoException;
import it.softfood.exception.UserException;
import it.softfood.session.BevandaMagazzinoSession;
import it.softfood.session.BevandaSession;
import it.softfood.session.IngredienteMagazzinoSession;
import it.softfood.session.IngredientePietanzaSession;
import it.softfood.session.IngredienteSession;
import it.softfood.session.LineaOrdinazioneSession;
import it.softfood.session.OrdinazioneSession;
import it.softfood.session.PietanzaSession;
import it.softfood.session.TavoloSession;
import it.softfood.session.VarianteSession;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class OrdinazioneFacade {

	private static OrdinazioneFacade singleton;
	private TavoloSession tavoloSession = TavoloSession.getInstance();
	private OrdinazioneSession ordinazioneSession = OrdinazioneSession.getInstance();
	private LineaOrdinazioneSession lineaOrdinazioneSession = LineaOrdinazioneSession.getInstance();
	private IngredientePietanzaSession ingredientePietanzaSession = IngredientePietanzaSession.getInstance();
	private IngredienteMagazzinoSession ingredienteMagazzinoSession = IngredienteMagazzinoSession.getInstance();
	private IngredienteSession ingredienteSession = IngredienteSession.getInstance();
	private VarianteSession varianteSession = VarianteSession.getInstance();
	private BevandaMagazzinoSession bevandaMagazzinoSession = BevandaMagazzinoSession.getInstance();
	private PietanzaSession pietanzaSession = PietanzaSession.getInstance();
	private BevandaSession bevandaSession = BevandaSession.getInstance();
	
	public OrdinazioneFacade(){}
	
	public synchronized static OrdinazioneFacade getInstance() {
		if (singleton == null) {
			singleton = new OrdinazioneFacade();
		}
		return singleton;
	}
	
	public Ordinazione inserisciOrdinazione(User user, Ordinazione ordinazione) throws TavoloOccupatoException {
		if (ordinazione.getTavolo()!=null && user != null && ordinazione != null && ordinazione.getCoperti() != null && ordinazione.getCoperti()>0 && ordinazione.getData()!=null) {
			Tavolo tavolo = null;
			try {
				tavolo = tavoloSession.selezionaTavoloPerId(ordinazione.getTavolo().getId());
			} catch (NullPointerException npe) {
				throw new TavoloOccupatoException(null);
			}
            
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

    @SuppressWarnings("unused")
	private Integer verificaIngredientiPietanza(User user, Pietanza pietanza) {
    	if (user != null && pietanza != null) {
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
    	
    	return 0;
    }

    public Ordinazione modificaOrdinazione(User user, Ordinazione nuovaOrdinazione, Ordinazione vecchiaOrdinazione) {
		if (user != null && nuovaOrdinazione != null && vecchiaOrdinazione != null) {
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

	public Ordinazione selezionaOrdinazionePerId(User user, Long id) {
		if (user != null && id != null) {
			Ordinazione ord = ordinazioneSession.selezionaOrdinazionePerId(id);
			return ord;
		}
		return null;
	}
	
	public ArrayList<Ordinazione> selezionaOrdinazioni(User user) {
		if (user != null)
			return (ArrayList<Ordinazione>) ordinazioneSession.selezionaOrdinazioni();
		
		return null;
	}
	
	public ArrayList<LineaOrdinazione> selezionaOrdinazioniGiornaliere(User user) {
		if (user != null) {
			Date data = new Date(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String a = sdf.format(data);
	
			try {
				data = sdf.parse(a);
			} catch (ParseException e1) {
				System.err.println("OrdinazioneFacade#selezionaOrdinazioniGiornaliere");
				e1.printStackTrace();
			}
			
			ArrayList<Ordinazione> ord = null;
			ArrayList<LineaOrdinazione> non_evasi = new ArrayList<LineaOrdinazione>();
			Set<LineaOrdinazione> linea=new HashSet<LineaOrdinazione>();
			
			try {
				ord = (ArrayList<Ordinazione>) ordinazioneSession.selezionaOrdinazioniGiornaliere();
				System.out.println("ORDINI PRESENTI " + ord.size());
				for(Ordinazione o : ord){
					linea=o.getLineaOrdinaziones();
					for(LineaOrdinazione lin : linea)
						if(!lin.getEvaso())
							non_evasi.add((LineaOrdinazione)lin);
				}
				
			} catch (Exception e) {
				System.err.println("ERRORE 2");
				e.printStackTrace();
			}
			
			return non_evasi;
		}
		
		return null;
	}
	
	public ArrayList<LineaOrdinazione> selezionaOrdinazioniGiornaliereNoData(User user) {
		if (user != null) {
			Date data = new Date(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String a = sdf.format(data);
	
			try {
				data = sdf.parse(a);
			} catch (ParseException e1) {
				System.err.println("OrdinazioneFacade#selezionaOrdinazioniGiornaliere");
				e1.printStackTrace();
			}
			
			ArrayList<Ordinazione> ord = null;
			ArrayList<LineaOrdinazione> non_evasi = new ArrayList<LineaOrdinazione>();
			Set<LineaOrdinazione> linea = new HashSet<LineaOrdinazione>();
			
			try {
				ord = (ArrayList<Ordinazione>) ordinazioneSession.selezionaOrdinazioniGiornaliereNoData();
				for(Ordinazione o : ord){
					linea = o.getLineaOrdinaziones();
					for(LineaOrdinazione l : linea)
						if(!l.getEvaso() && l.getArticolo().getTipoPietanza() != 5)
							non_evasi.add((LineaOrdinazione)l);
				}
			} catch (Exception e) {
				System.err.println("OrdinazioneFacade#selezionaOrdinazioniGiornaliereNoData");
			}
			
			if (non_evasi != null ) {
				for (int i = 0; i < non_evasi.size()-1; i++) {
			        for (int j = i + 1; j < non_evasi.size(); j++) {
			            if (non_evasi.get(i).getId() > non_evasi.get(j).getId()) {
			                LineaOrdinazione temp = non_evasi.get(i);
			                non_evasi.set(i, non_evasi.get(j));
			                non_evasi.set(j, temp);
			            }
			        }
			    }
			}

			return non_evasi;
		}
		
		return null;
	}
	
	public ArrayList<Ordinazione> selezionaOrdinazioniGiornalierePerTavolo(User user, Tavolo tavolo, Boolean terminato) {
		if (user != null && tavolo != null && terminato != null) 
			return (ArrayList<Ordinazione>) ordinazioneSession.selezionaOrdinazioniGionalierePerTavolo(tavolo, terminato);
		
		return null;
	}

    public Ordinazione selezionaOrdinazioneGiornalieraPerTavolo(User user, String riferimentoTavolo, Boolean terminato) {
    	if (user != null && riferimentoTavolo != null && terminato != null){
    		Tavolo tavolo = tavoloSession.selezionaTavoloPerRiferimento(riferimentoTavolo, true);
    		ArrayList<Ordinazione> list = ((ArrayList<Ordinazione>) ordinazioneSession.selezionaOrdinazioniGionalierePerTavolo(tavolo, terminato));
			if (list != null && list.size() > 0) {
				Ordinazione ordinazione = list.get(0);
				return ordinazione;
			}
		}
    	
		return null;
	}
    
    public Ordinazione selezionaOrdinazionePerTavolo(User user, String riferimentoTavolo, Boolean terminato) {
    	if (user != null && riferimentoTavolo != null && terminato != null) {
    		Tavolo tavolo = tavoloSession.selezionaTavoloPerRiferimento(riferimentoTavolo, true);
    		ArrayList<Ordinazione> list = ((ArrayList<Ordinazione>)ordinazioneSession.selezionaOrdinazioniPerTavolo(tavolo, terminato));
			if (list != null && list.size() > 0) {
				Ordinazione ordinazione = list.get(0);
				return ordinazione;
			}
		}
    	
		return null;
	}
	
	public boolean rimuoviOrdinazione(User user, Long id, Boolean ripristinaPietanze) {
		if (user != null && id != null) {
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
                                if (!this.aggiornaMagazzinoIngredienti(user,lineaOrdinazione, "+"))
                                    throw new Exception();
                            } else {
                                if (!this.aggiornaMagazzinoBevande(user,lineaOrdinazione, "+"))
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

	public LineaOrdinazione inserisciLineaOrdinazione(User user, LineaOrdinazione lineaOrdinazione) throws DisponibilitaBevandaException, DisponibilitaPietanzaException, AggiornamentoIngredientiMagazzinoException, UserException {
		if (user != null && lineaOrdinazione != null) {
			lineaOrdinazione.setOrdinazione(lineaOrdinazione.getOrdinazione());
			lineaOrdinazione.setEvaso(false);
			Articolo articolo = lineaOrdinazione.getArticolo();

			if (articolo != null) {
				if (articolo.getTipoArticolo().equals("Bevanda")) {
					if (this.selezionaDisponibilitaBevanda(user, articolo.getId()) > 0)
						lineaOrdinazione = lineaOrdinazioneSession.inserisciLineaOrdinazione(lineaOrdinazione);
					else
						throw new DisponibilitaBevandaException(null);
				}

				if (articolo.getTipoArticolo().equals("Pietanza")) {
					if (this.selezionaDisponibilitaPietanza(user, articolo.getId()) > 0) {
						lineaOrdinazione = lineaOrdinazioneSession.inserisciLineaOrdinazione(lineaOrdinazione);
					} else 
						throw new DisponibilitaPietanzaException(null);
				}

				if (articolo.getTipoArticolo().equals("Pietanza")) {
					if (!this.aggiornaMagazzinoIngredienti(user, lineaOrdinazione, "-"))
						throw new AggiornamentoIngredientiMagazzinoException(null);
				} else {
					if (!this.aggiornaMagazzinoBevande(user, lineaOrdinazione, "-"))
						throw new AggiornamentoIngredientiMagazzinoException(null);
				}
				return lineaOrdinazione;
			}

			return null;
		}

		throw new UserException(null);
	}
	 
	/*public LineaOrdinazione inserisciLineaOrdinazione(User user, LineaOrdinazione lineaOrdinazione) {
		if (user != null && lineaOrdinazione != null) {
            try {
            	lineaOrdinazione.setOrdinazione(lineaOrdinazione.getOrdinazione());
            	lineaOrdinazione.setEvaso(false);
                lineaOrdinazione = lineaOrdinazioneSession.inserisciLineaOrdinazione(lineaOrdinazione);
                
                Articolo articolo = lineaOrdinazione.getArticolo();
                
                if (articolo.getTipoArticolo().equals("Pietanza")) {
                    if (!this.aggiornaMagazzinoIngredienti(user, lineaOrdinazione, "-"))
                        throw new Exception();
                } else {
                	if (!this.aggiornaMagazzinoBevande(user, lineaOrdinazione, "-"))
                        throw  new Exception();
                }
                return lineaOrdinazione;
            	
            } catch (Exception e) {
            	System.err.println("OrdinazioneFacade#inserisciLineaOrdinazione " + e);
            }
		}
		
		return null;
	}*/
	
	public LineaOrdinazione modificaLineaOrdinazione(User user, LineaOrdinazione nuovaLineaOrdinazione, LineaOrdinazione vecchiaLineaOrdinazione) {
        if (user != null && nuovaLineaOrdinazione != null && vecchiaLineaOrdinazione != null) {
			LineaOrdinazione lineaOrdinazione = vecchiaLineaOrdinazione;
            lineaOrdinazione.setArticolo(nuovaLineaOrdinazione.getArticolo());
            lineaOrdinazione.setOrdinazione(nuovaLineaOrdinazione.getOrdinazione());
            lineaOrdinazione.setQuantita(nuovaLineaOrdinazione.getQuantita());
            lineaOrdinazioneSession.update(lineaOrdinazione);
            return lineaOrdinazione;
		}

		return null;
	}

    public LineaOrdinazione selezionaLineaOrdinazionePerId(User user, Long id) {
        if (user != null && id != null)
			return lineaOrdinazioneSession.selezionaLineaOrdinazionePerId(id);

        return null;
    }
    
    public ArrayList<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazione(User user, Ordinazione ordinazione) {
    	if (user != null && ordinazione != null){
        	ArrayList<LineaOrdinazione> linea=(ArrayList<LineaOrdinazione>) lineaOrdinazioneSession.selezionaLineeOrdinazionePerOrdinazione(ordinazione);
			return linea;
        }
        return null;
    }

    public ArrayList<LineaOrdinazione> selezionaLineeOrdinazionePerOrdinazioneTipoPietanza(User user, Ordinazione ordinazione, TipoPietanza tipoPietanza) {
        if (user != null && ordinazione!=null && tipoPietanza!=null) {
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
        
        return null;
    }

    public boolean rimuoviLineaOrdinazione(User user, Long id) {
        if (user != null && id != null) {
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

    public Variante inserisciVariante(User user, Variante variante) {
        if (user != null && variante != null) {
            LineaOrdinazione lineaOrdinazione = variante.getLineaOrdinazione();
            Ingrediente ingrediente = variante.getIngrediente();

            if (lineaOrdinazione != null && ingrediente != null) {
                try {                	
                    variante.setIngrediente(ingrediente);
                    variante.setLineaOrdinazione(lineaOrdinazione);
                    
                    variante = varianteSession.inserisciVariante(variante);
                    
                    return variante;
                } catch (Exception e) {
                	System.err.println("OrdinazioneFacade#inserisciVariante");
                }
            }
        }

        return null;
    }

    public ArrayList<Ingrediente> selezionaIngredientiPerVariante (User user) {
    	if (user != null) {
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
    	}
        
        return null;
    }

    public Variante modificaVariante(User user, Variante nuovaVariante, Variante vecchiaVariante) {
        if (user != null && nuovaVariante != null && vecchiaVariante != null) {
			Variante variante = vecchiaVariante;
            variante.setIngrediente(vecchiaVariante.getIngrediente());
            variante.setLineaOrdinazione(vecchiaVariante.getLineaOrdinazione());
            variante.setTipoVariazione(vecchiaVariante.getTipoVariazione());
            varianteSession.update(variante);
            return variante;
		}

		return null;
	}

    public Variante selezionaVariantePerId(User user,Long id) {
        if (user != null && id != null)
			return varianteSession.selezionaVariantePerId(id);

        return null;
    }
    
    public void setLineaEvasa(User user, LineaOrdinazione linea) {
    	if (user != null && linea != null) {
    		linea.setEvaso(true);
    		lineaOrdinazioneSession.update(linea);
    	}
    }

    public Ingrediente selezionaIngredientePerNome (User user, String ingrediente) {
        if (user != null && ingrediente != null) {
            ArrayList<Ingrediente> ingredienti = (ArrayList<Ingrediente>) ingredienteSession.selezionaIngredientePerNome(ingrediente);

            if (ingredienti != null)
                return ingredienti.get(0);
        }

        return null;
    }

    public ArrayList<Variante> selezionaVariantiPerIngrediente(User user, Ingrediente ingrediente) {
        return null;
    }

    public ArrayList<Variante> selezionaVariantiPerLineaOrdinazione(User user, LineaOrdinazione lineaOrdinazione) {
        if (user != null && lineaOrdinazione != null) {
            ArrayList<Variante> varianti =(ArrayList<Variante>) varianteSession.selezionaVariantiPerLineaOrdinazione(lineaOrdinazione);

            if (varianti != null && varianti.size() > 0)
                return varianti;
        }

        return null;
    }

    public boolean rimuoviVariante(User user, Long id) {
        if (user != null && id != null)
			return varianteSession.rimuoviVariante(id);

		return false;
    }

    private boolean aggiornaMagazzinoIngredienti(User user, LineaOrdinazione lineaOrdinazione, String tipoAggiornamento) {
    	if (user != null) {
	    	try {
	            ArrayList<IngredientePietanza> ingredientiPietanze = (ArrayList<IngredientePietanza>) ingredientePietanzaSession.selezionaIngredientiPietanze();
	            ArrayList<IngredienteMagazzino> ingredientiMagazzino = (ArrayList<IngredienteMagazzino>) ingredienteMagazzinoSession.selezionaIngredientiMagazzino();
	            if (ingredientiPietanze != null && ingredientiMagazzino != null) {
	                for (IngredientePietanza ingredientePietanza : ingredientiPietanze) {
	                    if (ingredientePietanza.getId().getPietanza().equals(lineaOrdinazione.getArticolo().getId())) {
	                            for (IngredienteMagazzino ingredienteMagazzino : ingredientiMagazzino) {
	                            	if (ingredienteMagazzino.getIngrediente().getId().equals(ingredientePietanza.getId().getIngrediente())) {
	                                	
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
        }
        
        return false;
    }

    private boolean aggiornaMagazzinoBevande(User user, LineaOrdinazione lineaOrdinazione, String tipoAggiornamento) {
    	if (user != null) {
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
        }

        return false;
    }
    
    public Integer selezionaDisponibilitaPietanza(User user, Long id) {
        if (user != null && id != null) {
            Pietanza pietanza = pietanzaSession.selezionaPietanzaPerId(id);
            return this.verificaDisponibilitaIngredientiPietanza(user, pietanza);
        }

        return null;
    }

    private Integer selezionaDisponibilitaBevanda(User user, Long id) {
        if (user != null && id != null) {
            Bevanda bevanda = bevandaSession.selezionaBevandaPerId(id);
            ArrayList<BevandaMagazzino> bevandeMagazzino = (ArrayList<BevandaMagazzino>) bevandaMagazzinoSession.selezionaBevandeMagazzino();

            for (BevandaMagazzino bevandaMagazzino : bevandeMagazzino) {
                if (bevandaMagazzino.getArticolo().getId().equals(id)) {
                    return (bevandaMagazzino.getQuantita()) / (bevanda.getCapacita()).intValue();
                }
            }
        }

        return 0;
    }
    
    private Integer verificaDisponibilitaIngredientiPietanza(User user, Pietanza pietanza) {
    	if (user != null && pietanza != null) {
	        ArrayList<IngredientePietanza> ingredientiPietanze = (ArrayList<IngredientePietanza>) ingredientePietanzaSession.selezionaIngredientiPietanze();
	        ArrayList<IngredienteMagazzino> ingredientiMagazzino = (ArrayList<IngredienteMagazzino>) ingredienteMagazzinoSession.selezionaIngredientiMagazzino();
	        Date data = new Date(System.currentTimeMillis());
	
	        int contatore = 0;
	        int disponibilita = 0;
	        int disponibilitaMinima = 1000;
	        int numeroIngredienti = 0;
	
			for (IngredientePietanza ingredientePietanza : ingredientiPietanze) {
	        	if (ingredientePietanza.getId().getPietanza().equals(pietanza.getId())) {
	            	numeroIngredienti++;
	                Ingrediente ingrediente = ingredienteSession.selezionaIngredientePerId(ingredientePietanza.getId().getIngrediente());
	                
	                for (IngredienteMagazzino ingredienteMagazzino : ingredientiMagazzino) {
	                	
	                	if (ingredienteMagazzino.getIngrediente().getId().equals(ingrediente.getId())) {
	                        contatore++;
	                        
	                        if (ingredienteMagazzino.getQuantita() >= ingredientePietanza.getQuantita() && ingrediente.getScadenza().after(data)) {
	                            try {
	                                disponibilita = ingredienteMagazzino.getQuantita() / ingredientePietanza.getQuantita();
	                            } catch (Exception nfe) {
	                                disponibilita = 0;
	                            }
	                        }
	
	                        if (disponibilita < disponibilitaMinima)
	                            disponibilitaMinima = disponibilita;
	                        disponibilita = 0;
	                        
	                    }
	                }
	            }
	        }
			
	        if (contatore == numeroIngredienti && contatore > 0)
	            return disponibilitaMinima;
    	}
    	
        return 0;
    }
    
}
