package it.softfood;

import it.softfood.entity.Bevanda;
import it.softfood.entity.Ingrediente;
import it.softfood.entity.IngredientePietanza;
import it.softfood.entity.IngredientePietanzaPK;
import it.softfood.entity.Listino;
import it.softfood.entity.Menu;
import it.softfood.entity.Ordinazione;
import it.softfood.entity.Pietanza;
import it.softfood.entity.Ristorante;
import it.softfood.entity.Tavolo;
import it.softfood.entity.User;
import it.softfood.enumeration.TipoPietanza;
import it.softfood.handler.ArticoloMenuFacade;
import it.softfood.handler.IArticoloMenuFacade;
import it.softfood.handler.IOrdinazioneFacade;
import it.softfood.handler.IRistoranteFacade;
import it.softfood.handler.ITavoloFacade;
import it.softfood.handler.IUserFacade;
import it.softfood.handler.OrdinazioneFacade;
import it.softfood.handler.RistoranteFacade;
import it.softfood.handler.TavoloFacade;
import it.softfood.handler.UserFacade;
import it.softfood.login.LoginHandler;
import it.softfood.session.BevandaSession;
import it.softfood.session.IngredientePietanzaSession;
import it.softfood.session.IngredienteSession;
import it.softfood.session.ListinoSession;
import it.softfood.session.MenuSession;
import it.softfood.session.OrdinazioneSession;
import it.softfood.session.PietanzaSession;
import it.softfood.session.RistoranteSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
//		TavoloSession tavolo=new TavoloSession();
//		RistoranteSession ristorante= RistoranteSession.getInstance();
//		List<Tavolo> tav=tavolo.selezionaTavoliLiberi();
//		Tavolo tav1=tavolo.selezionaTavoloPerId(new Long(1));
//		System.out.println("Oggetto tavolo : "+tav);
//		System.out.println("TAVOLI LIBERI: "+tav.size());
//		System.out.println("TAVOLI SELEZIONATO: "+tav1.getRiferimento());
//		
//		
//		Ristorante rist= ristorante.selezionaRistorantePerRagioneSociale("La taverna");
//		System.out.println("ristoranteeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee " +rist.getPartitaIva());
////		Ristorante rist=ristorante.selezionaRistorantePerPartitaIva("12032874912");
//		System.out.println("RISTORANTE: "+rist);
//		
//		Tavolo t=new Tavolo();
//		t.setRistorante(rist);
//		t.setRiferimento("NUOVO TAVOLO");
//		t.setId(new Long(21));
//		t.setNumeroPosti(4);
//		t.setOccupato(false);
//		t.setAttivo(true);
//		
//		t=tavolo.inserisciTavolo(t);
//		
//		tav1=tavolo.selezionaTavoloPerId(t.getId());
//		
//		tavolo.modificaStatoTavolo(t, false);
		
//		SoftfoodFacade facade=SoftfoodFacade.getInstance();
//		TavoloFacade facade=TavoloFacade.getInstance();
		//List<Tavolo> l=	facade.selezionaTavoliLiberi();
		//System.out.println("sddddddddddddddddddddddddd"+l);
//		TavoloFacade facade=new TavoloFacade();
//        Tavolo tavo=facade.selezionaTavolo(new Long(2));
//        Tavolo tav1=facade.selezionaTavolo(new Long(19));
//        ArrayList<String> list=new ArrayList<String>();
//        list.add(tav.getRiferimento());
//        System.out.println("AGGIUNTO TAVOLO "+tav.getRiferimento());
//        list.add(tav1.getRiferimento());
//        System.out.println("AGGIUNTO TAVOLO "+tav1.getRiferimento());
//        Long id=facade.occupaTavoli(list);
//        facade.rimuoviTavolo(id);
		
		ITavoloFacade tav=TavoloFacade.getInstance();
		IOrdinazioneFacade ord=(IOrdinazioneFacade) OrdinazioneFacade.getInstance();
		IRistoranteFacade rist=RistoranteFacade.getInstance();
		IArticoloMenuFacade art=(IArticoloMenuFacade) ArticoloMenuFacade.getInstance();
		IUserFacade iuser = (IUserFacade) UserFacade.getInstance();
//		
//		Pietanza p = new Pietanza();
//		p.setId(51L);
//		p.setNome("Patatine");
//		p.setDescrizione("Patatine");
		
//		ArrayList<LineaOrdinazione> linee = ord.selezionaLineeOrdinazionePerOrdinazioneTipoPietanza(o, null);
//		System.out.println("lineeeeeeeeeeeeeeeeeeeee" + linee.size());
		
//		Ordinazione ordine=new Ordinazione();
//		ordine.setTavolo(tavo);
//		
//		System.out.println("ID NUOVO ORDINE: "+ord.selezionaOrdinazioneGiornalieraPerTavolo(tavo, false).getId());
        

//		ArticoloMenuFacade a = ArticoloMenuFacade.getInstance();
//		
//		int b = a.verificaIngredientiPietanza(p);
//		System.out.print(b);
		
		/*User u = new User();
        u.setUserName("m");
        u.setPassword("m");
        u.setRuolo("amministratore");
*/
//        UserFacade ut = UserFacade.getInstance();
//
//        LoginHandler facade = LoginHandler.getInstance();
        User user1 = iuser.login("cuoco", "1234");

    
        //Maria
//        System.out.println("UTENTE "+ut.selezionaUserName(user1, u));
//       System.out.println(ut.selezionaPassword(user1, u));
        	
//		System.out.println(ut.modificaRuolo(user1, u, "cassiere"));
//		System.out.println(ut.eliminaUtente(user1, u));
        
//        facade.logout(user1);	
//		User user = new User("mary", "mary", "amministratore");
//		ArrayList<Bevanda> bevande = art.selezionaBevandeDisponibili(user);
//		System.out.println("Bevande "+ bevande.size());
//		Ordinazione o=ord.selezionaOrdinazionePerId(user1, new Long(0));
//		ord.rimuoviOrdinazione(user1, o.getId(), true);
		MenuSession ms = MenuSession.getInstance();
		Menu menu = ms.selezionaMenuPerId("Menu 1");
		
		ListinoSession ls = ListinoSession.getInstance();
		Listino listino = new Listino();
		
		listino.setDescrizione("test_descrizione");
		listino.setMenu(menu);
		listino.setPrezzo(9.9);
		
		ls.inserisciListino(listino);
		
/*		PietanzaSession ps = PietanzaSession.getInstance();
		Pietanza p = new Pietanza();
		p.setDescrizione("test_descrizione");
		p.setListino(listino);
		p.setNome("test_nome");
		p.setTipo(TipoPietanza.ANTIPASTI);
		p.setTipoArticolo("Pietanza");
		p.setTipoPietanza(TipoPietanza.ANTIPASTI.ordinal());
		p=ps.inserisciPietanza(p);
		
		ps.rimuoviPietanza(p.getId());
		
		BevandaSession bs = BevandaSession.getInstance();
		Bevanda b = new Bevanda();
		b.setDescrizione("test_descrizione");
		b.setListino(listino);
		b.setNome("test_nome");
		b.setCapacita(1000F);
		b.setTipoArticolo("Bevanda");
		b.setTipoPietanza(TipoPietanza.BEVANDA.ordinal());
		//b=bs.inserisciBevanda(b);
		
		//bs.rimuoviBevanda(80L);
*/		
		IngredienteSession is = IngredienteSession.getInstance();
		Ingrediente i = new Ingrediente();
		i.setDescrizione("test_descrizione");
		//i.setIngredienteMagazzinos(ingredienteMagazzinos);
		//i.setIngredientePietanzas(ingredientePietanzas);
		i.setNome("test_nome");
		i.setScadenza(new Date(System.currentTimeMillis()));
		i.setTipoIngrediente("IngredienteLungaConservazione");
		i.setUnitaMisura("ml");
		i.setVariante(false);
		//i.setVariantes(variantes);
		//is.inserisciIngrediente(i);
		is.rimuoviIngrediente(48L);
	}
	

}




