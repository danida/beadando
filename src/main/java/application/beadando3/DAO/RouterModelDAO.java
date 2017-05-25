package application.beadando3.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import Interfaces.DAOInterface;
import application.beadando3.model.DatabaseConnection;
import application.beadando3.model.RouterModel;

public class RouterModelDAO implements DAOInterface<RouterModel> {

	private  EntityManager entityManager = DatabaseConnection.getEm();
	private EntityManagerFactory emf = DatabaseConnection.getInstance();

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public RouterModelDAO() {

	}


	/**
	 *{@inheritDoc}
	 */

	@Override
	public void create(RouterModel e) {

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
	public void edit(RouterModel e) {

		entityManager = emf.createEntityManager();

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
	public void remove(RouterModel e) {
		entityManager = emf.createEntityManager();

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
	public List<RouterModel> findAll() {
		entityManager = emf.createEntityManager();

		List<RouterModel> ret = entityManager.createNamedQuery("RouterModel.findAll").getResultList();
		entityManager.close();
		return ret;
	}
	/**
	 *{@inheritDoc}
	 */
	@Override
	public String count() {
		entityManager = emf.createEntityManager();

		String ret = entityManager.createNamedQuery("RouterModel.count").getResultList().get(0).toString();
		entityManager.close();
		return ret;
	}

	/**
	 * List all of the platforms which are in the table
	 * @return Returns the used platforms
	 */
	public List<String> getPlatforms() {
		entityManager = emf.createEntityManager();
		List<String> ret = entityManager.createNamedQuery("RouterModel.platforms").getResultList();
		entityManager.close();
		return ret;
	}

	/**
	 * Get the numbers of router for different platforms
	 * @param platform - Platform of the router
	 * @return Returns the number as a String by number
	 */
	public String getnumberByPlatform(String platform) {
		entityManager = emf.createEntityManager();

		String ret = entityManager.createNamedQuery("RouterModel.routersnumberByPlatform")
				.setParameter("platform", platform).getResultList().get(0).toString();
		entityManager.close();
		return ret;

	}

	/**
	 * List the routers by their Id
	 * @param Id - Id of the RouterModel
	 * @return Returns the RouterModel by Id
	 */
	public List<RouterModel> getRouterModelbyId(int Id) {
		entityManager = emf.createEntityManager();

		List<RouterModel> ret = entityManager.createNamedQuery("RouterModel.getRouterModelbyId").setParameter("id", Id)
				.getResultList();
		entityManager.close();

		return ret;

	}

	/**
	 * List the routers by its platform
	 * @param parameter - Platform of the router
	 * @return Returns a list of RouterModel where the platform is the same
	 */
	public List<RouterModel> getRouterbyPlatform(String parameter) {
		List<RouterModel> ret = new ArrayList<>();
		entityManager = emf.createEntityManager();

		ret = entityManager.createNamedQuery("RouterModel.getRouterByPlatform").setParameter("platform", parameter).getResultList();
		entityManager.close();
		return ret;

	}

	/**
	 * List routers by their IOS
	 * @param parameter - IOS name
	 * @return Returns the router with the same IOS
	 */
	public List<RouterModel> getRouterbyIOS(String parameter) {

		List<RouterModel> ret = new ArrayList<>();
		entityManager = emf.createEntityManager();

		ret = entityManager.createNamedQuery("RouterModel.getRouterbyIOS").setParameter("IOS", parameter)
				.getResultList();
		entityManager.close();
		return ret;

	}

	/**
	 * Get the router by the name of its
	 * @param parameter - Name of the Router
	 * @return Returns the router by its name
	 */
	public List<RouterModel> getRouterbyName(String parameter) {
		List<RouterModel> ret = new ArrayList<>();
		entityManager = emf.createEntityManager();

		ret = entityManager.createNamedQuery("RouterModel.getRouterbyName").setParameter("name", parameter)
				.getResultList();
		entityManager.close();
		return ret;

	}
}
