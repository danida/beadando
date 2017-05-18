package application.beadando3.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import Interfaces.DAOInterface;
import application.beadando3.model.DatabaseConnection;
import application.beadando3.model.TracerouteModel;

public class TracerouteModelDAO implements DAOInterface<TracerouteModel> {
	private static EntityManager entityManager;

	private EntityManagerFactory emf = DatabaseConnection.getInstance().getEmf();

	

	@Override
	public void create(TracerouteModel e) {
		entityManager =emf.createEntityManager();
		entityManager.persist(e);
		entityManager.flush();
		entityManager.getEntityManagerFactory().getCache().evictAll();	
		entityManager.close();
	}

	@Override
	public void edit(TracerouteModel e) {
		entityManager =emf.createEntityManager();
		entityManager.merge(e);
		entityManager.flush();
		entityManager.getEntityManagerFactory().getCache().evictAll();	
		entityManager.close();

	}

	@Override
	public void remove(TracerouteModel e) {
		entityManager =emf.createEntityManager();
		entityManager.remove(entityManager.merge(e));
		entityManager.flush();
		entityManager.getEntityManagerFactory().getCache().evictAll();	
		entityManager.close();

	}

	@Override
	public <T> List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String count() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<TracerouteModel> getTracerouteModelbyId(int id){
		entityManager =emf.createEntityManager();

		List<TracerouteModel>ret = entityManager.createNamedQuery("TracerouteModel.getbyId").getResultList();
		entityManager.close();
		return ret;
	}
	
}
