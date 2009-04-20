package it.softfood.facade;

import it.softfood.entity.User;
import it.softfood.handler.UserFacade;

public class PDAUserFacade {
	
	public User login(String user,String password){
		UserFacade role=UserFacade.getInstance();
		User u=role.login(user, password);
		System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu"+u.getName());
		return u;
	}
	
	public void logout(User u){
		System.out.println(u);
		UserFacade role=UserFacade.getInstance();
		role.logout(u);
	}

}
