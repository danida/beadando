package application.beadando3.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Interfaces.DAOInterface;
import application.beadando3.Main;
import application.beadando3.model.DatabaseConnection;
import application.beadando3.model.PingModel;

public class PingModelDAO implements DAOInterface<PingModel> {

	private  EntityManager entityManager = DatabaseConnection.getEm();
	private EntityManagerFactory emf = DatabaseConnection.getInstance();
	
	/**
	 *{@inheritDoc}
	 */
	@Override
	public void create(PingModel e) {

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
	public void edit(PingModel e) {

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
	public void remove(PingModel e) {

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
	public  List<PingModel> findAll() {

		entityManager =emf.createEntityManager();
		List<PingModel>ret =entityManager.createNamedQuery("PingModel.findAll").getResultList();
		entityManager.close();
		return ret;
	}
	/**
	 *{@inheritDoc}
	 */
	@Override
	public String count() {
		entityManager =emf.createEntityManager();
		String ret =entityManager.createNamedQuery("PingModel.count").getResultList().get(0).toString();
		entityManager.close();
		return ret;
	}
	/**
	 * Get the ping by Id
	 * @param Id - Id of the ping request
	 * @return Returns a list of  instances of the PingModel
	 */
	public List<PingModel> getPingModelbyId(int Id){

		entityManager =emf.createEntityManager();
		List<PingModel >ret =  entityManager.createNamedQuery("PingModel.getbyid").setParameter("id", Id).getResultList();
		entityManager.close();

		return ret;
	}
	
}
