package application.beadando3.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Interfaces.DAOInterface;
import application.beadando3.Main;
import application.beadando3.model.DatabaseConnection;
import application.beadando3.model.InterfacesModel;

public class InterfacesModelDAO implements DAOInterface<InterfacesModel> {

	private  EntityManager entityManager = DatabaseConnection.getEm();
	private EntityManagerFactory emf = DatabaseConnection.getInstance();

	public InterfacesModelDAO() {

	}

	public void create(InterfacesModel e) {
		entityManager =emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(e);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public void edit(InterfacesModel e) {
		entityManager =emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(e);
		entityManager.getTransaction().commit();
		entityManager.close();

	}

	@Override
	public void remove(InterfacesModel e) {

		entityManager =emf.createEntityManager();
		entityManager.getTransaction().begin();

		entityManager.remove(entityManager.merge(e));
		entityManager.getTransaction().commit();
		entityManager.close();

	}

	@Override
	public List<InterfacesModel> findAll() {

		entityManager =emf.createEntityManager();
		List<InterfacesModel> ret = entityManager.createNamedQuery("InterfacesModel.findAll").getResultList();
		entityManager.close();

		return ret;

	}

	@Override
	public String count() {

		entityManager =emf.createEntityManager();

		String ret = entityManager.createNamedQuery("InterfacesModel.count").getResultList().get(0).toString();
		entityManager.close();

		return ret;
	}

	public List<InterfacesModel> getInterfacesByRouterId(int id) {
		entityManager =emf.createEntityManager();
		List<InterfacesModel> ret = entityManager.createNamedQuery("InterfacesModel.getByRouterId")
				.setParameter("router_id", id).getResultList();
		entityManager.close();

		return ret;

	}

	public InterfacesModel getInterfacesbyId(int id) {
		entityManager =emf.createEntityManager();

		InterfacesModel ret = (InterfacesModel) entityManager.createNamedQuery("InterfacesModel.getInterfacesbyId")
				.setParameter("interfaces_id", id).getResultList().get(0);
		entityManager.close();

		return ret;
	}

}
