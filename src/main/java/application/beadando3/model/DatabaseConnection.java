package application.beadando3.model;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseConnection {

	private final String PUN = "Router";
	private static EntityManagerFactory emf;
	private static DatabaseConnection instance = new DatabaseConnection();
	public DatabaseConnection() {
	}
	public static DatabaseConnection getInstance() {
		return instance;
	};
	public EntityManagerFactory getEmf() {
		if (emf == null){
			emf = Persistence.createEntityManagerFactory(PUN);
		}
		return emf;
	}
	
	public void closeConnection(){
		if (emf.isOpen()){emf.close();}
	}
	
	
	
	
	
}
