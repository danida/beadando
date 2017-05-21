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



	public List<RouterModel> getRouterbyPlatform(String parameter) {
		List<RouterModel> ret = new ArrayList<>();
		entityManager = emf.createEntityManager();

		ret = entityManager.createNamedQuery("RouterModel.getRouterByPlatform").setParameter("platform", parameter).getResultList();
		entityManager.close();
		return ret;

	}

	public List<RouterModel> getRouterbyIOS(String parameter) {

		List<RouterModel> ret = new ArrayList<>();
		entityManager = emf.createEntityManager();

		ret = entityManager.createNamedQuery("RouterModel.getRouterbyIOS").setParameter("IOS", parameter)
				.getResultList();
		entityManager.close();
		return ret;

	}

	public List<RouterModel> getRouterbyName(String parameter) {
		List<RouterModel> ret = new ArrayList<>();
		entityManager = emf.createEntityManager();

		ret = entityManager.createNamedQuery("RouterModel.getRouterbyName").setParameter("name", parameter)
				.getResultList();
		entityManager.close();
		return ret;

	}

	@Override
	public void create(RouterModel e) {

		entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(e);
		entityManager.getTransaction().commit();
		entityManager.close();

	}

	@Override
	public void edit(RouterModel e) {

		entityManager = emf.createEntityManager();

		entityManager.getTransaction().begin();
		entityManager.merge(e);
		entityManager.flush();
		entityManager.getTransaction().commit();
		entityManager.close();

	}

	@Override
	public void remove(RouterModel e) {
		entityManager = emf.createEntityManager();

		entityManager.getTransaction().begin();

		entityManager.remove(entityManager.merge(e));
		entityManager.flush();
		entityManager.getTransaction().commit();
		entityManager.close();

	}

	@Override
	public List<RouterModel> findAll() {
		entityManager = emf.createEntityManager();

		List<RouterModel> ret = entityManager.createNamedQuery("RouterModel.findAll").getResultList();
		entityManager.close();
		return ret;
	}

	@Override
	public String count() {
		entityManager = emf.createEntityManager();

		String ret = entityManager.createNamedQuery("RouterModel.count").getResultList().get(0).toString();
		entityManager.close();
		return ret;
	}

	public List<String> getPlatforms() {
		entityManager = emf.createEntityManager();
		List<String> ret = entityManager.createNamedQuery("RouterModel.platforms").getResultList();
		entityManager.close();
		return ret;
	}

	public String getnumberByPlatform(String platform) {
		entityManager = emf.createEntityManager();

		String ret = entityManager.createNamedQuery("RouterModel.routersnumberByPlatform")
				.setParameter("platform", platform).getResultList().get(0).toString();
		entityManager.close();
		return ret;

	}

	public List<RouterModel> getRouterModelbyId(int id) {
		entityManager = emf.createEntityManager();

		List<RouterModel> ret = entityManager.createNamedQuery("RouterModel.getRouterModelbyId").setParameter("id", id)
				.getResultList();
		entityManager.close();

		return ret;

	}

}
