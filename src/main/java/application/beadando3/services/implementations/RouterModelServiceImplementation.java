package application.beadando3.services.implementations;

import java.util.List;

import Interfaces.ServicesInterface;
import application.beadando3.DAO.PingModelDAO;
import application.beadando3.DAO.RouterModelDAO;
import application.beadando3.model.RouterModel;

public class RouterModelServiceImplementation implements ServicesInterface<RouterModel> {
	private RouterModelDAO dao = new RouterModelDAO();

	@Override
	public void save(RouterModel e) {
		if (validateRouterModel(e)){
			dao.create(e);
		}
		
		else if (checkDuplicatesRouterModel(e)){
			throw new IllegalArgumentException("Ez a router már létezik!");
		}
		else {
			throw new IllegalArgumentException("Valamelyik mező üres!");
		}
	}

	@Override
	public void update(RouterModel e) {
		if (!validateRouterModel(e)){
			throw new IllegalArgumentException("Valamelyik mező üres!");			
		}
		else if (!checkDuplicatesRouterModel(e)){
			throw new IllegalArgumentException("Ez a router nem létezik!");
		}
		
		else {
			dao.edit(e);
		}		
	}

	@Override
	public void delete(RouterModel e) {
		if (!checkDuplicatesRouterModel(e)){
			throw new IllegalArgumentException("Ez a router nem létezik!");
		}
		else{
			dao.remove(e);
			
		}		
	}		
	

	@Override
	public List getAll() {
		return dao.findAll();

	}

	@Override
	public String count() {
		return dao.count();

	}
	
	public String getNumberbyPlatform(String platform){
		return dao.getnumberByPlatform(platform);
		
	}
	
	public List<String> getPlatforms(){
		return dao.getPlatforms();
	}
	
	public boolean validateRouterModel(RouterModel RouterModel){
		boolean valid = true;
		if ( RouterModel.getBootstrap_version()== null || RouterModel.getConfig_register() == null || RouterModel.getIOS()==null || RouterModel.getMan_IP()==null || RouterModel.getName()==null || RouterModel.getPlatform()==null || RouterModel.getSerial_number()==null || RouterModel.getWhen_configured()==null || RouterModel.getWho_Configured()==null  ) {
        	valid = false;
        }
		return valid;
	}
	public boolean checkDuplicatesRouterModel(RouterModel RouterModel){
		boolean valid = false;
		
		if (dao.getRouterModelbyId(RouterModel.getId()) != null){
			valid = true;
		}
		
		return valid;
	}

	public RouterModelDAO getDao() {
		return dao;
	}

	public void setDao(RouterModelDAO dao) {
		this.dao = dao;
	}
	

}
