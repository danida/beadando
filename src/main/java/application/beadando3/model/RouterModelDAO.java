package application.beadando3.model;

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

public class RouterModelDAO implements DAOInterface<RouterModel> {

	@PersistenceContext(unitName = "Router")
	private static EntityManager entityManager;



	
	public void init() {
		entityManager = Persistence.createEntityManagerFactory("Router").createEntityManager();

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

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public void create(RouterModel e) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(e);
		getEntityManager().getTransaction().commit();
		getEntityManager().getEntityManagerFactory().getCache().evictAll();
	}

	@Override
	@Transactional
	public void edit(RouterModel e) {
		getEntityManager().getTransaction().begin();
		getEntityManager().merge(e);
		getEntityManager().flush();
		getEntityManager().getTransaction().commit();
		getEntityManager().getEntityManagerFactory().getCache().evictAll();
		
	
	}

	@Override
	public void remove(RouterModel e) {
		getEntityManager().getTransaction().begin();

		getEntityManager().remove(getEntityManager().merge(e));
		getEntityManager().flush();
		getEntityManager().getTransaction().commit();
		getEntityManager().getEntityManagerFactory().getCache().evictAll();

	}

	@Override
	public List<RouterModel> findAll() {
		return entityManager.createNamedQuery("RouterModel.findAll").getResultList();
	}

	@Override
	public String count() {
		return entityManager.createNamedQuery("RouterModel.count").getResultList().get(0).toString();
	}
	
	public List<String> getPlatforms() {
		return entityManager.createNamedQuery("RouterModel.platforms").getResultList();
	}
	public String getnumberByPlatform(String platform){
		return entityManager.createNamedQuery("RouterModel.routersnumberByPlatform").setParameter("platform", platform).getResultList().get(0).toString();

		
		
	}
	
	


}
