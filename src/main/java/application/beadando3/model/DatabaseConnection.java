package application.beadando3.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseConnection {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Router");
	private static EntityManager em;
	public DatabaseConnection() {
	}
	public static EntityManagerFactory getInstance() {
		if (emf == null){
			emf = Persistence.createEntityManagerFactory("Router");
		}
		return emf;
	};
	public static EntityManager getEm() {
		if (emf == null){
			emf = Persistence.createEntityManagerFactory("Router");
		}
		if (em == null  ){
			  em = emf.createEntityManager();
		}
		return em;
	}
	
	public void closeConnection(){
		if (emf.isOpen()){emf.close();}
	}
	
	
	
	
	
}
