package application.beadando3.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author danida
 *
 */
@Entity

@Table(name = "Login")
@NamedQueries({ @NamedQuery(name = "LoginModel.getbyid", query = "select l from LoginModel l where id = :id"),
		@NamedQuery(name = "LoginModel.findAll", query = "select l from LoginModel l"),
		@NamedQuery(name = "LoginModel.count", query = "select count(l.id) from LoginModel l"),
		@NamedQuery(name = "LoginModel.getUserByUsername", query = "select l from LoginModel l where username = :username"),
		@NamedQuery(name = "LoginModel.getPasswordByUsername", query = "select l.password from LoginModel l where username = :username"),
		@NamedQuery(name = "LoginModel.getAllIsAdmins", query = "select l from LoginModel l where isadmin = :isadmin")

})

public class LoginModel {
	@Id
	@SequenceGenerator(name = "user_seq_gen", sequenceName = "user_SEQ", allocationSize=1,initialValue=1)

	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_seq_gen")

	@Column
	Integer id;
	@Column(name = "username")
	@Basic
	String user;
	@Column(name = "userpassword")
	@Basic
	String password;
	@Column
	@Basic
	Integer isAdmin;

	public LoginModel() {
		super();
	}

	public LoginModel(String user, String password) {
		super();
		this.user = user;
		this.password = password;

	}

	public LoginModel(Integer id, String user, String password, Integer isAdmin) {
		this.id = id;
		this.user = user;
		this.password = password;
		this.isAdmin = isAdmin;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "user: " + user + ", isAdmin=" + isAdmin;
	}

}
