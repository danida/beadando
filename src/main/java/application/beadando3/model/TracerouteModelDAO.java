package application.beadando3.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import Interfaces.DAOInterface;

public class TracerouteModelDAO implements DAOInterface<TracerouteModel> {
	private EntityManager entityManager;

	public void init() {
		entityManager = Persistence.createEntityManagerFactory("Router").createEntityManager();

	}

	public void closeEm() {
		entityManager.close();

	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public void create(TracerouteModel e) {
		getEntityManager().persist(e);
		getEntityManager().flush();
		getEntityManager().getEntityManagerFactory().getCache().evictAll();				
	}

	@Override
	public void edit(TracerouteModel e) {
		getEntityManager().merge(e);
		getEntityManager().flush();
		getEntityManager().getEntityManagerFactory().getCache().evictAll();			
	}

	@Override
	public void remove(TracerouteModel e) {
		getEntityManager().remove(getEntityManager().merge(e));
		getEntityManager().flush();
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
