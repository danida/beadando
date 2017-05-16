package application.beadando3.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import Interfaces.DAOInterface;

public class InterfacesModelDAO implements DAOInterface<InterfacesModel>{

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
	
	
	public InterfacesModelDAO() {
		
	}



	public void create(InterfacesModel e) {
		getEntityManager().persist(e);
		getEntityManager().flush();
		getEntityManager().getEntityManagerFactory().getCache().evictAll();		
	}

	@Override
	public void edit(InterfacesModel e) {
		getEntityManager().merge(e);
		getEntityManager().flush();
		getEntityManager().getEntityManagerFactory().getCache().evictAll();		
	}

	@Override
	public void remove(InterfacesModel e) {
		getEntityManager().remove(getEntityManager().merge(e));
		getEntityManager().flush();
		getEntityManager().getEntityManagerFactory().getCache().evictAll();		
	}

	@Override
	public  List<InterfacesModel> findAll() {
		return getEntityManager().createNamedQuery("InterfacesModel.findAll").getResultList();

	}

	@Override
	public String count() {
		return getEntityManager().createNamedQuery("InterfacesModel.count").getResultList().get(0).toString();
	}
	public List<InterfacesModel> getInterfacesByRouterId(int id){
		
		
		return getEntityManager().createNamedQuery("InterfacesModel.getByRouterId").setParameter("router_id", id).getResultList();
		}
	
	
}
