package application.beadando3.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import Interfaces.DAOInterface;
import application.beadando3.model.DatabaseConnection;
import application.beadando3.model.TracerouteModel;

public class TracerouteModelDAO implements DAOInterface<TracerouteModel> {
	private EntityManager entityManager = DatabaseConnection.getEm();
	private EntityManagerFactory emf = DatabaseConnection.getInstance();

	@Override
	public void create(TracerouteModel e) {

		entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		entityManager.persist(e);
		entityManager.getTransaction().commit();

		entityManager.close();
	}

	@Override
	public void edit(TracerouteModel e) {
		entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		entityManager.merge(e);
		entityManager.getTransaction().commit();

		entityManager.close();

	}

	@Override
	public void remove(TracerouteModel e) {
		entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		entityManager.remove(entityManager.merge(e));
		entityManager.getTransaction().commit();

		entityManager.close();

	}

	@Override
	public <T> List<T> findAll() {

		entityManager = emf.createEntityManager();

		List<T> ret = entityManager.createNamedQuery("TracerouteModel.findAll").getResultList();
		entityManager.close();
		return ret;
	}

	@Override
	public String count() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TracerouteModel> getTracerouteModelbyId(int id) {
		entityManager = emf.createEntityManager();

		List<TracerouteModel> ret = entityManager.createNamedQuery("TracerouteModel.getbyId").setParameter("id",id).getResultList();
		entityManager.close();
		return ret;
	}

}
