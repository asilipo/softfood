package it.softfood.aspect;

import org.hibernate.Session;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public aspect TransactionManagement {

	pointcut transactionalMethods(SessionHolder holder): 
		execution(*  it.softfood.session.*.*(..)) 
		&& ! execution(*  it.softfood.session.*.*Session(..))
		&& target(holder) 
		&& !cflowbelow(execution(* it.softfood.session.*.*(..)));

	private Session getSession() {
		return Persistence.aspectOf().getSession();

	}

	before(SessionHolder holder): transactionalMethods(holder) {
		this.getSession().beginTransaction();
		holder.setSession(this.getSession());
	}

	after(SessionHolder holder) returning: transactionalMethods(holder) {
		holder.getSession().getTransaction().commit();
	}

	after(SessionHolder holder) throwing: transactionalMethods(holder) {
		holder.getSession().getTransaction().rollback();
	}

}
