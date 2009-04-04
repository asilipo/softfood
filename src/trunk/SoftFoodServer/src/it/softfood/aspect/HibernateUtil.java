package it.softfood.aspect;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	private static ThreadLocal<Session> sharedSession;

	public static void init() throws Throwable {
		try {
			System.out.println("Init - Session");
			sessionFactory = new Configuration().configure().buildSessionFactory();
			sharedSession = new ThreadLocal<Session>();
		}

		catch (Throwable ex) {
			System.err.println("Error Init - Session");
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session openSession() {
		Session session = (Session) sharedSession.get();
		if (session == null) {
			System.out.println("Opening - Session");
			session = (Session) sessionFactory.openSession();
			sharedSession.set(session);
		}
		return session;
	}

	public static void closeSession() {
		Session shSession = (Session) sharedSession.get();
		if (shSession != null) {
			System.out.println("Closing - Session");
			shSession.close();
			sharedSession.set(null);
		}

	}

	public static Session getSession() throws Exception {
		Session shSession = (Session) sharedSession.get();
		if (shSession == null) {
			throw new Exception("Session is null.");
		}
		return shSession;
	}
	
}
