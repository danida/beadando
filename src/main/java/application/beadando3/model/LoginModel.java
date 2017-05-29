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

	/**
	 * Default constructor.
	 */
	public LoginModel() {
		super();
	}

	/**
	 * Non-default constructor.
	 * @param user - username
	 * @param password - password for the user
	 */
	public LoginModel(String user, String password) {
		super();
		this.user = user;
		this.password = password;

	}

	/**
	 * Non-default constructor for all of the values.
	 * @param id - Id of the user
	 * @param user - username of the user
	 * @param password - password of the user
	 * @param isAdmin - 
	 */
	public LoginModel(Integer id, String user, String password, Integer isAdmin) {
		this.id = id;
		this.user = user;
		this.password = password;
		this.isAdmin = isAdmin;
	}

	/**
	 * Getter of the username.
	 * @return Returns the name of the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Setter of the username.
	 * @param user - username of the user
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * Getter of the password.
	 * @return Returns the name of the user
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Setter of the password.
	 * @param password - password of the user
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Getter of the id.
	 * @return Returns the id of the user
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Setter of the id.
	 * @param id - id of the user
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * Getter of the Admin.
	 * @return Returns if the user is admin or not
	 */
	public Integer getIsAdmin() {
		return isAdmin;
	}
	/**
	 * Setter of the Admin.
	 * @param isAdmin - Admin of the user
	 */
	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}
	  /**
		 *{@inheritDoc}
		 */
	@Override
	public String toString() {
		return "user: " + user + ", isAdmin=" + isAdmin;
	}

}
