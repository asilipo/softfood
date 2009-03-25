

import java.util.List;

import org.hibernate.Session;

import it.softfood.entity.Tavolo;
import it.softfood.session.TavoloSession;

public class start {
	
	//private Session session;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TavoloSession tavolo=new TavoloSession();
		List<Tavolo> tav=tavolo.selezionaTavoliLiberi();
		Tavolo tav1=tavolo.selezionaTavoloPerId(new Long(1));
		System.out.println("Oggetto tavolo : "+tav);
		System.out.println("TAVOLI LIBERI: "+tav.size());
		System.out.println("TAVOLI SELEZIONATO: "+tav1.getRiferimento());

	}
	/*
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}*/

}
