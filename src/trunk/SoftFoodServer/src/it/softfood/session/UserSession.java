package it.softfood.session;



import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import it.softfood.aspect.HibernateUtil;
import it.softfood.entity.Tavolo;
import it.softfood.entity.User;


public class UserSession {
	
	private Session session;

	
	private static UserSession userSingleton;

	public synchronized static UserSession getInstance(){
		if(userSingleton==null)
			userSingleton=new UserSession();
		return(userSingleton);
	}

	
	public User inserisciUser(User user) {

		try {
			System.out.println("Inserisci Utente "+user.getUserName());
			session.persist(user);	
			return user;
		} catch (Exception e) {
			System.err.println("UserSessionBean#inserisciUser");
			System.err.println(e);
			return null;
		}
	}

	public User selezionaUserPerUserName(String user) {
		try {
			User u=(User) session.get(User.class, user);
			return u;
		} catch (Exception e) {
			System.err.println("UserSessionBean#selezionaUserPerUserName");
			return null;
		}
	}

	public User selezionaUserPerPassword(String password) {
		try {
			User result;
			String str = " from it.softfood.entity.User t where t.password = ? ";
			Query q = session.createQuery(str);
			q.setString(0, password);
			result = (User) q.list().get(0);
			return result;
			
		} catch (Exception e) {
			System.err
					.println("UserSessionBean#selezionaUserPerPassword");
			System.err
			.println(e);
			return null;
		}
	}


	public boolean modificaRuoloUser(User user, String ruolo) {
		try {
			user.setRuolo(ruolo);

			user = (User) session.merge(user);

			return true;
		} catch (Exception e) {
			System.err.println("UserSessionBean#modificaRuoloUser");
			return false;
		}
	}

	public boolean rimuoviUser(String username) {
		try {
			User user = this.selezionaUserPerUserName(username);
			if (user != null) {
				session.delete(user);
				return true;
			}

			return false;
		} catch (Exception e) {
			System.err.println("UserSessionBean#rimuoviUser");
			return false;
		}
	}

	public void flush() {
		this.session.flush();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public void update(User u) {
		session.update(u);
	}


	public User selezionaUtente(String username, String password) {
		User result;
		String str = " from it.softfood.entity.User t where t.userName = ? AND t.password = ? ";
		Query q = session.createQuery(str);
		q.setString(0, username);
		q.setString(1, password);
		result = (User) q.uniqueResult();
		return result;
	}
}
