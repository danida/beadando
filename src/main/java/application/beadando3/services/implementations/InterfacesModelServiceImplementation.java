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

public class InterfacesModelServiceImplementation implements ServicesInterface<InterfacesModel> {
	
	private InterfacesModelDAO dao = new InterfacesModelDAO();
    private final static Logger logger = LoggerFactory.getLogger(InterfacesModelServiceImplementation.class);

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

	@Override
	public  List<InterfacesModel> getAll() {
		logger.info("Finding all interfacemodels");
		return  dao.findAll();
	}

	@Override
	public String count() {
		logger.info("Counting interfaces in the database");

		return dao.count();
	}
	
	public List<InterfacesModel> getInterfacebyRouterModel(int id){
		logger.info("Find all interfaces for a router");

		return dao.getInterfacesByRouterId(id);
	}
	
	public boolean validateInterfacesModel(InterfacesModel interfacesModel){
		boolean valid = true;
		if ( interfacesModel.getInterface_name()== null || interfacesModel.getIP() == null || interfacesModel.getMAC() == null) {
        	valid = false;
        }
		return valid;
	}
	public boolean checkDuplicatesInterfacesModel(InterfacesModel interfacesModel){
		boolean valid = false;
		
		if (dao.getInterfacesbyId(interfacesModel.getId()) != null){
			valid = true;
		}
		
		return valid;
	}

	public InterfacesModelDAO getDao() {
		return dao;
	}

	public void setDao(InterfacesModelDAO dao) {
		this.dao = dao;
	}
	
}