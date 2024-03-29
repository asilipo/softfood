package it.softfood.aspect;

import org.hibernate.Session;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public aspect Persistence {
	private Session session = null;
	
	declare parents: it.softfood.session.* implements SessionHolder;

	public Persistence() {
		try {
			HibernateUtil.init();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public Session getSession() {
		session = HibernateUtil.openSession();
		return session;
	}

	pointcut operation(): execution(* it.softfood.handler.*.*(..)) && !cflowbelow(execution(* it.softfood.handler.*.*(..))) && !execution(* it.softfood.handler.*.*Session(..)) && !execution(* it.softfood.handler.*.login(..)) && !execution(* it.softfood.handler.*.logout(..));

	before() :  operation() {		
		session = HibernateUtil.openSession();
	}

	after() : operation()  {
		HibernateUtil.closeSession();
	}

}