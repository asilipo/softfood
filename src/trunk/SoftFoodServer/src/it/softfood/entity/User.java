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

	public String getName() {
		return ruolo.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((ruolo == null) ? 0 : ruolo.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (ruolo == null) {
			if (other.ruolo != null)
				return false;
		} else if (!ruolo.equals(other.ruolo))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

}

