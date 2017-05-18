package application.beadando3.services.implementations;

import java.util.List;

import Interfaces.ServicesInterface;
import application.beadando3.DAO.InterfacesModelDAO;
import application.beadando3.DAO.RouterModelDAO;
import application.beadando3.model.*;
import javafx.collections.FXCollections;

public class InterfacesModelServiceImplementation implements ServicesInterface<InterfacesModel> {
	
	InterfacesModelDAO dao = new InterfacesModelDAO();
	
	@Override
	public void save(InterfacesModel e) {
		if (validateInterfacesModel(e)){
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
			dao.edit(e);
		}
		
	}

	@Override
	public void delete(InterfacesModel e) {
		if (!checkDuplicatesInterfacesModel(e)){
			throw new IllegalArgumentException("Ez az interface nem létezik!");
		}
		else{
			dao.remove(e);
			
		}
		
	}

	@Override
	public  List<InterfacesModel> getAll() {
		return  dao.findAll();
	}

	@Override
	public String count() {
		return dao.count();
	}
	
	public List<InterfacesModel> getInterfacebyRouterModel(int id){
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
	
}