package application.beadando3.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import application.beadando3.model.DatabaseConnection;
import application.beadando3.model.FeaturesModel;
import application.beadando3.model.InterfacesModel;
import Interfaces.DAOInterface;

public class FeaturesModelDAO implements DAOInterface<FeaturesModel>{

	private  EntityManager entityManager = DatabaseConnection.getEm();
	private EntityManagerFactory emf = DatabaseConnection.getInstance();
	
	@Override
	public void create(FeaturesModel e) {
		entityManager =emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(e);
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}

	@Override
	public void edit(FeaturesModel e) {
		entityManager =emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(e);
		entityManager.getTransaction().commit();
		entityManager.close();		
	}

	@Override
	public void remove(FeaturesModel e) {

		entityManager =emf.createEntityManager();
		entityManager.getTransaction().begin();

		entityManager.remove(entityManager.merge(e));
		entityManager.getTransaction().commit();
		entityManager.close();		
	}

	@Override
	public List<FeaturesModel> findAll() {
		entityManager =emf.createEntityManager();
		List<FeaturesModel> ret = entityManager.createNamedQuery("FeaturesModel.findAll").getResultList();
		entityManager.close();

		return ret;		
	}

	@Override
	public String count() {
		entityManager =emf.createEntityManager();

		String ret = entityManager.createNamedQuery("FeaturesModel.count").getResultList().get(0).toString();
		entityManager.close();

		return ret;
	}
	public List<String> findPlatforms(){
		entityManager =emf.createEntityManager();

		List<String> ret = entityManager.createNamedQuery("FeaturesModel.findPlatforms").getResultList();
		return ret;

	}
	
	public List<FeaturesModel> getFeaturesModelByFeatures_name(String platform) {
		entityManager =emf.createEntityManager();

		List <FeaturesModel>ret = entityManager.createNamedQuery("FeaturesModel.getbyPlatformName").setParameter("platform", platform).getResultList();
		entityManager.close();

		return ret;
	}
	public int getMaxibyPlatform(String platform) {
		entityManager =emf.createEntityManager();

		 int ret = (Integer) entityManager.createNamedQuery("FeaturesModel.findMaxByPlatform").setParameter("platform", platform).getResultList().get(0);
		entityManager.close();

		return ret;
	}
	
	
}
