package application.beadando3.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import Interfaces.DAOInterface;
import application.beadando3.model.DatabaseConnection;
import application.beadando3.model.RouterModel;

public class RouterModelDAO implements DAOInterface<RouterModel> {

	private static EntityManager entityManager;

	private EntityManagerFactory emf = DatabaseConnection.getInstance().getEmf();

	
	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public RouterModelDAO() {

	}

	public List<RouterModel> getRouterbyWho_configured(String username) {

		List<RouterModel> ret = new ArrayList<>();

		return ret;

	}

	public List<RouterModel> getRouterbyPlatform(String parameter) {
		List<RouterModel> ret = new ArrayList<>();

		return ret;

	}

	public List<RouterModel> getRouterbyIOS(String parameter) {

		List<RouterModel> ret = new ArrayList<>();

		return ret;

	}

	public List<RouterModel> getRouterbyName(String parameter) {
		List<RouterModel> ret = new ArrayList<>();

		return ret;

	}

	@Override
	public void create(RouterModel e) {
		entityManager =emf.createEntityManager();
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
		entityManager =emf.createEntityManager();

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
