package it.softfood.entity;

import java.security.Principal;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class User implements  Principal, java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private String ruolo;

	public User() {
	}

	public User(String userName, String password, String ruolo) {
		this.userName = userName;
		this.password = password;
		this.ruolo = ruolo;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRuolo() {
		return this.ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	@Override
	public String getName() {
		return ruolo.toString();
	}

}

