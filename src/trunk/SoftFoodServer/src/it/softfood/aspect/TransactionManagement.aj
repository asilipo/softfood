package it.softfood.aspect;

import org.hibernate.Session;

public aspect TransactionManagement {

	pointcut transactionalMethods(SessionHolder holder): 
		execution(*  it.softfood.session.*.*(..)) 
		&& ! execution(*  it.softfood.session.*.*Session(..))
		&& target(holder) 
		&& !cflowbelow(execution(* it.softfood.session.*.*(..)));

	/*pointcut transactionalMethods2(SessionHolder holder): 
		execution(*  it.softfood.session.*.*(..)) 
		&& ! execution(*  it.softfood.session.*.*Session(..))
		&& target(holder) 
		&& cflowbelow(execution(* it.softfood.session.*.*(..)));

	before(SessionHolder holder): transactionalMethods2(holder) {
		// this.getSession().beginTransaction();
		holder.setSession(this.getSession());

	}*/

	private Session getSession() {
		return Persistence.aspectOf().getSession();

	}

	before(SessionHolder holder): transactionalMethods(holder) {
		this.getSession().beginTransaction();
		System.out.println("ESECUZIONE PRIMA DEL METODO");
		System.out.println("BEFORE " + this.getSession().isConnected());
		holder.setSession(this.getSession());

	}

	after(SessionHolder holder) returning: transactionalMethods(holder)  {
		holder.getSession().getTransaction().commit();
	}

	after(SessionHolder holder) throwing: transactionalMethods(holder)  {
		holder.getSession().getTransaction().rollback();
	}

}
