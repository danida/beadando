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
	/**
	 *{@inheritDoc}
	 */
	@Override
	public void create(TracerouteModel e) {

		entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		entityManager.persist(e);
		entityManager.getTransaction().commit();

		entityManager.close();
	}
	/**
	 *{@inheritDoc}
	 */
	@Override
	public void edit(TracerouteModel e) {
		entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		entityManager.merge(e);
		entityManager.getTransaction().commit();

		entityManager.close();

	}
	/**
	 *{@inheritDoc}
	 */
	@Override
	public void remove(TracerouteModel e) {
		entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		entityManager.remove(entityManager.merge(e));
		entityManager.getTransaction().commit();

		entityManager.close();

	}
	/**
	 *{@inheritDoc}
	 */
	@Override
	public  List<TracerouteModel> findAll() {

		entityManager = emf.createEntityManager();

		List<TracerouteModel> ret = entityManager.createNamedQuery("TracerouteModel.findAll").getResultList();
		entityManager.close();
		return ret;
	}
	/**
	 *{@inheritDoc}
	 */
	@Override
	public String count() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get the traceroute result by its Id
	 * @param Id - Id of the tracerouteModel
	 * @return Returns the traceroutemodel by Id
	 */
	public List<TracerouteModel> getTracerouteModelbyId(int Id) {
		entityManager = emf.createEntityManager();

		List<TracerouteModel> ret = entityManager.createNamedQuery("TracerouteModel.getbyId").setParameter("id",Id).getResultList();
		entityManager.close();
		return ret;
	}

}
