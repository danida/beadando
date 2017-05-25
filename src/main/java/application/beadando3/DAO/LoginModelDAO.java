package application.beadando3.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import Interfaces.DAOInterface;
import application.beadando3.model.DatabaseConnection;
import application.beadando3.model.LoginModel;

/**
 * @author danida
 *
 */
public class LoginModelDAO implements DAOInterface<LoginModel> {
	/**
	 * Set the EntityManager for the instance
	 */
	private  EntityManager entityManager = DatabaseConnection.getEm();
	/**
	 * Set the EntityManagerFactory for the instance
	 */
	private EntityManagerFactory emf = DatabaseConnection.getInstance();
	/**
	 *{@inheritDoc}
	 */
	@Override
	public void create(LoginModel e) {
		entityManager =emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(e);
		entityManager.getTransaction().commit();
		entityManager.close();		
	}
	/**
	 *{@inheritDoc}
	 */
	@Override
	public void edit(LoginModel e) {

		entityManager =emf.createEntityManager();

		entityManager.getTransaction().begin();

		entityManager.merge(e);
		entityManager.flush();
		entityManager.getTransaction().commit();

		entityManager.close();		
	}
	/**
	 *{@inheritDoc}
	 */
	@Override
	public void remove(LoginModel e) {

		entityManager =emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.merge(e));
		entityManager.flush();
		entityManager.getTransaction().commit();

		entityManager.close();		
	}
	/**
	 *{@inheritDoc}
	 */
	@Override
	public  List<LoginModel> findAll() {
		entityManager =emf.createEntityManager();
		List<LoginModel>ret =entityManager.createNamedQuery("LoginModel.findAll").getResultList();
		entityManager.close();
		return ret;		
	}
	/**
	 *{@inheritDoc}
	 */
	@Override
	public String count() {
		entityManager =emf.createEntityManager();
		String ret =entityManager.createNamedQuery("LoginModel.count").getResultList().get(0).toString();
		entityManager.close();
		return ret;		
	}
	/**
	 * Get UserModel by username
	 * @param username - username of the user
	 * @return Returns the Usermodel by username
	 */
	public List<LoginModel> getUserByUsername(String username){
		entityManager =emf.createEntityManager();
		List<LoginModel> ret =entityManager.createNamedQuery("LoginModel.getUserByUsername").setParameter("username", username).getResultList();
		entityManager.close();
		return ret;	
	}

	/**
	 * Get the password for the user
	 * @param username - username of the user
	 * @return Returns the hash of the password
	 */
	public String getPasswordForUser(String username) {
		entityManager =emf.createEntityManager();
		String ret =entityManager.createNamedQuery("LoginModel.getPasswordByUsername").setParameter("username", username).getResultList().get(0).toString();
		entityManager.close();
		return ret;	
	}

	/**
	 * Get Usermodel by User ID
	 * @param Id - Id of the User 
	 * @return Returns the usermodel by its userId
	 */
	public List<LoginModel> getUserById(int Id) {
		entityManager =emf.createEntityManager();
		List<LoginModel> ret =entityManager.createNamedQuery("LoginModel.getbyid").setParameter("id", Id).getResultList();
		entityManager.close();
		return ret;		
		}
	/**
	 * List the admin users
	 * @return Returns all of the admin users
	 */
	public List<LoginModel> getAllAdmins(){
		entityManager =emf.createEntityManager();
		List<LoginModel> ret =entityManager.createNamedQuery("LoginModel.getAllIsAdmins").setParameter("isadmin", 1).getResultList();
		entityManager.close();
		return ret;	

	}
	/**
	 * List all of the users which are not admins
	 * @return Returns all of the normal users
	 */
	public List<LoginModel> getAllUsers(){
		entityManager =emf.createEntityManager();
		List<LoginModel> ret =entityManager.createNamedQuery("LoginModel.getAllIsAdmins").setParameter("isadmin", 0).getResultList();
		entityManager.close();
		return ret;	
	}

}
