/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.softfood.aspect;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 
 * @author marcograsso
 */
public class HibernateUtil {

	private static SessionFactory sessionFactory;
	private static ThreadLocal<Session> sharedSession;

	public static void init() throws Throwable {
		try {
			File file = new File(".");
			String path = file.getCanonicalPath();
			System.out.println(System.getProperty("java.class.path"));
			System.out.println("PATH::::::::::" + path);
			System.out
					.println("INITIALIZING SESSIONNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			System.out
					.println("INITIALIZING SHARED SESSIONNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
			sharedSession = new ThreadLocal<Session>();
		}

		catch (Throwable ex) {
			System.err
					.println("ERROR INITIALING HIBERNATE INIT UTILLLLLLLLLLLLLLLLLLLLLLLLLLLL");
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session openSession() {
		Session session = (Session) sharedSession.get();
		System.out.println("HIBERNATE UTIL OPENING SESSION");
		if (session == null) {
			System.out.println("HIBERNATE UTIL SESSION INITIALLY NULL");
			session = (Session) sessionFactory.openSession();
			sharedSession.set(session);
		}
		return session;
	}

	public static void closeSession() {
		Session shSession = (Session) sharedSession.get();
		if (shSession != null) {
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
