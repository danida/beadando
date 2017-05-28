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

/**
 * @author danida
 *
 */
public class InterfacesModelDAO implements DAOInterface<InterfacesModel> {

	/**
	 * Set the EntityManager for the instance.
	 */
	private  EntityManager entityManager = DatabaseConnection.getEm();
	/**
	 * Set the EntityManagerFactory for the instance.
	 */
	private EntityManagerFactory emf = DatabaseConnection.getInstance();

	/**
	 * Default constructor of the DAO.
	 */
	public InterfacesModelDAO() {

	}
	/**
	 *{@inheritDoc}
	 */
	@Override
	public void create(InterfacesModel e) {
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
	public void edit(InterfacesModel e) {
		entityManager =emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(e);
		entityManager.getTransaction().commit();
		entityManager.close();

	}
	/**
	 *{@inheritDoc}
	 */
	@Override
	public void remove(InterfacesModel e) {

		entityManager =emf.createEntityManager();
		entityManager.getTransaction().begin();

		entityManager.remove(entityManager.merge(e));
		entityManager.getTransaction().commit();
		entityManager.close();

	}
	/**
	 *{@inheritDoc}
	 */
	@Override
	public List<InterfacesModel> findAll() {

		entityManager =emf.createEntityManager();
		List<InterfacesModel> ret = entityManager.createNamedQuery("InterfacesModel.findAll").getResultList();
		entityManager.close();

		return ret;

	}
	/**
	 *{@inheritDoc}
	 */
	@Override
	public String count() {

		entityManager =emf.createEntityManager();

		String ret = entityManager.createNamedQuery("InterfacesModel.count").getResultList().get(0).toString();
		entityManager.close();

		return ret;
	}

	/**
	 * Lists all of the interfaces which the router has.
	 * @param Id of the interface
	 * @return Returns list of Interfacesmodel
	 */
	public List<InterfacesModel> getInterfacesByRouterId(int Id) {
		entityManager =emf.createEntityManager();
		List<InterfacesModel> ret = entityManager.createNamedQuery("InterfacesModel.getByRouterId")
				.setParameter("router_id", Id).getResultList();
		entityManager.close();
		return ret;

	}

	/**
	 * List an interface with the provided id.
	 * @param Id of the interface
	 * @return Returns the list of the Interfacesmodel by Id
	 */
	public InterfacesModel getInterfacesbyId(int Id) {
		entityManager =emf.createEntityManager();
		InterfacesModel ret = (InterfacesModel) entityManager.createNamedQuery("InterfacesModel.getInterfacesbyId")
				.setParameter("interfaces_id", Id).getResultList().get(0);
		entityManager.close();

		return ret;
	}

}
