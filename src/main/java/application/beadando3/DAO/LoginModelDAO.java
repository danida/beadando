package application.beadando3.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import Interfaces.DAOInterface;
import application.beadando3.model.DatabaseConnection;
import application.beadando3.model.LoginModel;

public class LoginModelDAO implements DAOInterface<LoginModel> {

	private  EntityManager entityManager = DatabaseConnection.getEm();
	private EntityManagerFactory emf = DatabaseConnection.getInstance();
	

	@Override
	public void create(LoginModel e) {
		entityManager =emf.createEntityManager();

		entityManager.getTransaction().begin();
		entityManager.persist(e);
		entityManager.getTransaction().commit();

		entityManager.close();		
	}

	@Override
	public void edit(LoginModel e) {

		entityManager =emf.createEntityManager();

		entityManager.getTransaction().begin();

		entityManager.merge(e);
		entityManager.flush();
		entityManager.getTransaction().commit();

		entityManager.close();		
	}

	@Override
	public void remove(LoginModel e) {

		entityManager =emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.merge(e));
		entityManager.flush();
		entityManager.getTransaction().commit();

		entityManager.close();		
	}

	@Override
	public <T> List<T> findAll() {
		entityManager =emf.createEntityManager();
		List<T>ret =entityManager.createNamedQuery("LoginModel.findAll").getResultList();
		entityManager.close();
		return ret;		
	}

	@Override
	public String count() {
		entityManager =emf.createEntityManager();
		String ret =entityManager.createNamedQuery("LoginModel.count").getResultList().get(0).toString();
		entityManager.close();
		return ret;		
	}
	public List<LoginModel> getUserByUsername(String username){
		entityManager =emf.createEntityManager();
		List<LoginModel> ret =entityManager.createNamedQuery("LoginModel.getUserByUsername").setParameter("username", username).getResultList();
		entityManager.close();
		return ret;	
	}

	public String getPasswordForUser(String username) {
		entityManager =emf.createEntityManager();
		String ret =entityManager.createNamedQuery("LoginModel.getPasswordByUsername").setParameter("username", username).getResultList().get(0).toString();
		entityManager.close();
		return ret;	
	}

	public Object getUserById(int id) {
		entityManager =emf.createEntityManager();
		List<LoginModel> ret =entityManager.createNamedQuery("LoginModel.getbyid").setParameter("id", id).getResultList();
		entityManager.close();
		return ret;		}

}
