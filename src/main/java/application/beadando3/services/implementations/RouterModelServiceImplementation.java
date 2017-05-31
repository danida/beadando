package application.beadando3.services.implementations;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Interfaces.ServicesInterface;

import application.beadando3.DAO.RouterModelDAO;
import application.beadando3.model.RouterModel;

/**
 * @author danida
 *
 */
public class RouterModelServiceImplementation implements ServicesInterface<RouterModel> {
	private RouterModelDAO dao ;
    private final static Logger logger = LoggerFactory.getLogger(RouterModelServiceImplementation.class);
    
    
    
    /**
     * Default constructor.
     */
    public RouterModelServiceImplementation() {
    	dao  = new RouterModelDAO();
    }
    
    
    
	/**
	 * Constructor of the class.
	 * @param dao - One instance from the DAO of the routermodel
	 */
	public RouterModelServiceImplementation(RouterModelDAO dao) {
		super();
		this.dao = dao;
	}



	/**
	 *{@inheritDoc}
	 */
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
	 /**
	 *{@inheritDoc}
	 */
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
	 /**
	 *{@inheritDoc}
	 */
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
	
	 /**
	 *{@inheritDoc}
	 */
	@Override
	public List getAll() {
		logger.info("Getting all of the routermodels");

		return dao.findAll();

	}
	 /**
	 *{@inheritDoc}
	 */
	@Override
	public String count() {
		logger.info("Counting number of routermodels");

		return dao.count();

	}
	
	/**
	 * Get how many exists from the provided platforms.
	 * @param platform - Platform of the router
	 * @return Returns a number of the platform
	 */
	public String getNumberbyPlatform(String platform){
		logger.info("Getting routermodel by platform");

		return dao.getnumberByPlatform(platform);
		
	}
	
	/**
	 * Lists all of the platforms available in the database.
	 * @return Returns the platforms
	 */
	public List<String> getPlatforms(){
		logger.info("Getting all of the router platforms");

		return dao.getPlatforms();
	}
	
	/**
	 * Checks if the routermodel is savable or not.
	 * @param RouterModel - One instance of the routermodel
	 * @return Returns true if the user can save the instance
	 */
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
	/**
	 * Checks if the router is in the table or not.
	 * @param RouterModel - One instance of routermodel
	 * @return Returns true if the router is already in the table
	 */
	public boolean checkDuplicatesRouterModel(RouterModel RouterModel){
		boolean valid = false;
		
		if (dao.getRouterModelbyId(RouterModel.getId()) != null){
			valid = true;
		}
		
		return valid;
	}
	/**
	 * Get the router by its name.
 	 * @param routername - name of the router
	 * @return Returns a routermodel which has the same name
	 */
	public List<RouterModel> getRouterModelbyName (String routername){
		logger.info("Getting router by name");

		return dao.getRouterbyName(routername);
	}
	/**
	 * Lists routers with the same IOS.
	 * @param IOS - IOS of the router
	 * @return Returns the router by its IOS
	 */
	public List<RouterModel> getRouterModelbyIOS (String IOS){
		logger.info("Getting routermodel by IOS");

		return dao.getRouterbyIOS(IOS);
	}
	/**
	 * Get the routers by its platform.
	 * @param platform - platfrom of the router
	 * @return  Returns all of the router with the same platform
	 */
	public List<RouterModel> getRouterModelbyPlatform (String platform){
		logger.info("Getting routermodel by platform");
		return dao.getRouterbyPlatform(platform);
	}
	
	/**
	 * Getter of the DAO.
	 * @return Returns the instance of the DAO
	 */
	public RouterModelDAO getDao() {
		return dao;
	}

	/**
	 * Setter of the DAO.
	 * @param dao - One instance of the RouterModelDAO
	 */
	public void setDao(RouterModelDAO dao) {
		this.dao = dao;
	}
	

}
