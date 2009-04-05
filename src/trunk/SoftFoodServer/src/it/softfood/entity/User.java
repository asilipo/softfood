package it.softfood.entity;



import java.security.Principal;


public class User implements  Principal, java.io.Serializable {
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
		// TODO Auto-generated method stub
		return null;
	}


    

}

