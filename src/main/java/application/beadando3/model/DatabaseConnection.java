package application.beadando3.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author danida
 *
 */
public class DatabaseConnection {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Router");
	private static EntityManager em;
	/**
	 * Constructor of the databaseconnection.
	 */
	public DatabaseConnection() {
	}
	/**
	 * Getting an instance of databaseconnection.
	 * @return Returns one instance of the DatabaseConnection
	 */
	public static EntityManagerFactory getInstance() {
		if (emf == null){
			emf = Persistence.createEntityManagerFactory("Router");
		}
		return emf;
	};
	/**
	 * Getting one entitymanager.
	 * @return Returns EntityManagar for the database
	 */
	public static EntityManager getEm() {
		if (emf == null){
			emf = Persistence.createEntityManagerFactory("Router");
		}
		if (em == null  ){
			  em = emf.createEntityManager();
		}
		return em;
	}
	
	/**
	 * Closes the connection to tha database.
	 */
	public void closeConnection(){
		if (emf.isOpen()){emf.close();}
	}
	
	
	
	
	
}
