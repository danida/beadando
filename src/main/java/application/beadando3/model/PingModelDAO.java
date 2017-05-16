package application.beadando3.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import Interfaces.DAOInterface;

public class PingModelDAO implements DAOInterface<PingModel> {
	@PersistenceContext(unitName = "Router")
	private static EntityManager entityManager;
	
	public void init() {
		entityManager = Persistence.createEntityManagerFactory("Router").createEntityManager();

	}

	public void closeEm() {
		entityManager.close();

	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	public void create(PingModel e) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(e);
		getEntityManager().getTransaction().commit();

		getEntityManager().getEntityManagerFactory().getCache().evictAll();		
	}

	@Override
	public void edit(PingModel e) {
		getEntityManager().getTransaction().begin();

		getEntityManager().merge(e);
		getEntityManager().flush();
		getEntityManager().getTransaction().commit();

		getEntityManager().getEntityManagerFactory().getCache().evictAll();		
	}

	@Override
	public void remove(PingModel e) {
		getEntityManager().getTransaction().begin();

		getEntityManager().remove(getEntityManager().merge(e));
		getEntityManager().flush();
		getEntityManager().getTransaction().commit();

		getEntityManager().getEntityManagerFactory().getCache().evictAll();		
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
	
}
