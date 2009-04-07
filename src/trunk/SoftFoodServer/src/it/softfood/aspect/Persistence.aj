package it.softfood.aspect;

import org.hibernate.Session;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public aspect Persistence {

	declare parents: it.softfood.session.* implements SessionHolder;

	public Persistence() {
		try {
			HibernateUtil.init();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private Session session = null;

	public Session getSession() {
		session = HibernateUtil.openSession();
		return session;
	}

	pointcut operation(): execution(* it.softfood.session.*.*(..)) && !cflowbelow(execution(* it.softfood.session.*.*(..))) && !execution(* it.softfood.session.*.*Session(..));

	before() :  operation() {		
		session = HibernateUtil.openSession();
		
		
	}

	after() : operation()  {
//		System.out.println("Dopo - Persistenza - CLOSING SESSION");
		HibernateUtil.closeSession();
//		System.out.println("Dopo - Persistenza - CLOSED SESSION");
	}

}