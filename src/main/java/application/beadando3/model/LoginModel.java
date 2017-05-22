package application.beadando3.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Login")
@NamedQueries({ @NamedQuery(name = "LoginModel.getbyid", query = "select l from LoginModel l where id = :id"),
		@NamedQuery(name = "LoginModel.findAll", query = "select l from LoginModel l"),
		@NamedQuery(name = "LoginModel.count", query = "select count(l.id) from LoginModel l"),
		@NamedQuery(name = "LoginModel.getUserByUsername", query = "select l from LoginModel l where username = :username"),
		@NamedQuery(name = "LoginModel.getPasswordByUsername", query = "select l.password from LoginModel l where username = :username")
})

public class LoginModel {
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	int id;
	@Column(name="username")
	@Basic
	String user;
	@Column(name="userpassword")
	@Basic
	String password;
	@Column
	@Basic
	Boolean isAdmin;
	

	public LoginModel() {
		super();
	}

	public LoginModel(String user, String password) {
		super();
		this.user = user;
		this.password = password;
		
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
