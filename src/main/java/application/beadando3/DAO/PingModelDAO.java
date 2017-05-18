package application.beadando3.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import Interfaces.DAOInterface;
import application.beadando3.model.DatabaseConnection;
import application.beadando3.model.PingModel;

public class PingModelDAO implements DAOInterface<PingModel> {

	private static EntityManager entityManager;
	private EntityManagerFactory emf = DatabaseConnection.getInstance().getEmf();


	

	public void create(PingModel e) {
		entityManager =emf.createEntityManager();

		entityManager.getTransaction().begin();
		entityManager.persist(e);
		entityManager.getTransaction().commit();

		entityManager.close();
	}

	@Override
	public void edit(PingModel e) {
		entityManager =emf.createEntityManager();

		entityManager.getTransaction().begin();

		entityManager.merge(e);
		entityManager.flush();
		entityManager.getTransaction().commit();

		entityManager.close();

	}

	@Override
	public void remove(PingModel e) {
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
		List<T>ret =entityManager.createNamedQuery("PingModel.findAll").getResultList();
		entityManager.close();
		return ret;
	}

	@Override
	public String count() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<PingModel> getPingModelbyId(int id){
		entityManager =emf.createEntityManager();
		List<PingModel >ret =  entityManager.createNamedQuery("PingModel.getbyid").setParameter("id", id).getResultList();
		entityManager.close();

		return ret;
	}
	
}
