package application.beadando3.services.implementations;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Interfaces.ServicesInterface;
import application.beadando3.Main;
import application.beadando3.DAO.InterfacesModelDAO;
import application.beadando3.DAO.RouterModelDAO;
import application.beadando3.model.*;
import javafx.collections.FXCollections;

/**
 * @author danida
 *
 */
public class InterfacesModelServiceImplementation implements ServicesInterface<InterfacesModel> {
	
	
	
	
	

	private InterfacesModelDAO dao;
    private final static Logger logger = LoggerFactory.getLogger(InterfacesModelServiceImplementation.class);
    
    
    /**
     * Default contructor.
     */
    public InterfacesModelServiceImplementation() {
    }
	/**
	 * Non-default constructor.
	 * @param dao - DAO for InterfacesModelDAO
	 */
	public InterfacesModelServiceImplementation(InterfacesModelDAO dao) {
		super();
		this.dao = dao;
	}
    /**
	 *{@inheritDoc}
	 */
	@Override
	public void save(InterfacesModel e) {
		if (validateInterfacesModel(e)){
			logger.info("Saving interfacemodel");

			dao.create(e);
			
		}
		else {
			throw new IllegalArgumentException("Valamelyik mező üres!");
		}
		
		
	}
	 /**
	 *{@inheritDoc}
	 */
	@Override
	public void update(InterfacesModel e) {
		if (!validateInterfacesModel(e)){
			throw new IllegalArgumentException("Valamelyik mező üres!");			
		}
		
		
		else {		
			logger.info("Editing interfacemodel");
			dao.edit(e);
		}
		
	}
	 /**
	 *{@inheritDoc}
	 */
	@Override
	public void delete(InterfacesModel e) {
		if (!checkDuplicatesInterfacesModel(e)){
			throw new IllegalArgumentException("Ez az interface nem létezik!");
		}
		else{
			logger.info("Deleting interfacemodel");
			dao.remove(e);
			
		}
		
	}
	 /**
	 *{@inheritDoc}
	 */
	@Override
	public  List<InterfacesModel> getAll() {
		logger.info("Finding all interfacemodels");
		return  dao.findAll();
	}
	 /**
	 *{@inheritDoc}
	 */
	@Override
	public String count() {
		logger.info("Counting interfaces in the database");

		return dao.count();
	}
	
	/**
	 * List the interfaces for a router.
	 * @param Id - Id of the routermodel
	 * @return Returns a list of  interfaces for a router
	 */
	public List<InterfacesModel> getInterfacebyRouterModel(int Id){
		logger.info("Find all interfaces for a router");
		return dao.getInterfacesByRouterId(Id);
	}
	
	/**
	 * Checks if the interface is savable or not.
	 * @param interfacesModel - Interfacemodel 
	 * @return Returns 
	 */
	public boolean validateInterfacesModel(InterfacesModel interfacesModel){
		boolean valid = true;
		if ( interfacesModel.getInterface_name()== null || interfacesModel.getIP() == null || interfacesModel.getMAC() == null) {
        	valid = false;
        }
		return valid;
	}
	/**
	 * Checks if the interface already exists or not.
	 * @param interfacesModel - 
	 * @return Returns true if the interface is not exist
	 */
	public boolean checkDuplicatesInterfacesModel(InterfacesModel interfacesModel){
		boolean valid = false;
		
		if (dao.getInterfacesbyId(interfacesModel.getId()) != null){
			valid = true;
		}
		
		return valid;
	}

	/**
	 * Get the dao.
	 * @return Returns InterfacesModelDAO
	 */
	public InterfacesModelDAO getDao() {
		return dao;
	}

	/**
	 * Set the dao.
	 * @param dao - InterfacesModelDAO
	 */
	public void setDao(InterfacesModelDAO dao) {
		this.dao = dao;
	}
	
}