package it.softfood.aspect;

import org.hibernate.Session;

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

	pointcut operation(): execution(* it.softfood.session.*.*(..)) && !execution(* it.softfood.session.*.*Session(..));

	/**
	 * Before initializing a handler, init the session
	 */
	before() :  operation() {
		System.out.println("Persistenza - OPENING SESSIONNNNNNNNNNNNNNNNNNNNN");
		
		session = HibernateUtil.openSession();
		
		if(session.isConnected())
			System.out.println("Persistenza - ASPECT SESSION CONNECTEDDDDDDDDDDDDDDDDD");
		if(session.isOpen())
			System.out.println("Persistenza - ASPECT SESSION OPENEDDDDDDDDDDDDDDDDDDDDD");
		
		System.out.println("Persistenza - OPENED SESSIONNNNNNNNNNNNNNNNNNNNNNN");
	}

	after() : operation()  {

		System.out.println("Dopo - Persistenza - CLOSING SESSIONNNNNNNNNNNNNNNNNNNNN");
		HibernateUtil.closeSession();
		System.out.println("Dopo - Persistenza - CLOSED SESSIONNNNNNNNNNNNNNNNNNNNNNN");
	}
	

}