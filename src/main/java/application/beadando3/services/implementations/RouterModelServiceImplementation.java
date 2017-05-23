package application.beadando3.services.implementations;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Interfaces.ServicesInterface;
import application.beadando3.Main;
import application.beadando3.DAO.PingModelDAO;
import application.beadando3.DAO.RouterModelDAO;
import application.beadando3.model.RouterModel;

public class RouterModelServiceImplementation implements ServicesInterface<RouterModel> {
	private RouterModelDAO dao = new RouterModelDAO();
    private final static Logger logger = LoggerFactory.getLogger(RouterModelServiceImplementation.class);

	@Override
	public void save(RouterModel e) {
		if (validateRouterModel(e)){
			logger.info("Saving routermodel");
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
		
		else  {
			logger.info("Editing routermodel");
			dao.edit(e);
		}		
	}

	@Override
	public void delete(RouterModel e) {
		if (!checkDuplicatesRouterModel(e)){
			throw new IllegalArgumentException("Ez a router nem létezik!");
		}
		else{
			logger.info("Deleting routermodel");

			dao.remove(e);
			
		}		
	}		
	

	@Override
	public List getAll() {
		logger.info("Getting all of the routermodels");

		return dao.findAll();

	}

	@Override
	public String count() {
		logger.info("Counting number of routermodels");

		return dao.count();

	}
	
	public String getNumberbyPlatform(String platform){
		logger.info("Getting routermodel by platform");

		return dao.getnumberByPlatform(platform);
		
	}
	
	public List<String> getPlatforms(){
		logger.info("Getting all of the router platforms");

		return dao.getPlatforms();
	}
	
	public boolean validateRouterModel(RouterModel RouterModel){
		boolean valid = true;
		if ( RouterModel.getPlatform()== null || RouterModel.getConfig_register() == null || RouterModel.getIOS()==null || RouterModel.getMan_IP()==null || RouterModel.getName()==null || RouterModel.getPlatform()==null || RouterModel.getSerial_number()==null || RouterModel.getWhen_configured()==null || RouterModel.getWho_Configured()==null  ) {
			valid = false;
        }
		if (RouterModel.getMan_IP().trim().isEmpty() || RouterModel.getName().trim().isEmpty() || RouterModel.getPlatform().trim().isEmpty()){
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
	public List<RouterModel> getRouterModelbyName (String routername){
		logger.info("Getting router by name");

		return dao.getRouterbyName(routername);
		
	}
	public List<RouterModel> getRouterModelbyIOS (String IOS){
		logger.info("Getting routermodel by IOS");

		return dao.getRouterbyIOS(IOS);
	}
	public List<RouterModel> getRouterModelbyPlatform (String platform){
		logger.info("Getting routermodel by platform");
		return dao.getRouterbyPlatform(platform);
	}
	
	public RouterModelDAO getDao() {
		return dao;
	}

	public void setDao(RouterModelDAO dao) {
		this.dao = dao;
	}
	

}
