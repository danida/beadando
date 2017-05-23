package application.beadando3.services.implementations;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Interfaces.ServicesInterface;
import application.beadando3.DAO.FeaturesModelDAO;
import application.beadando3.model.FeaturesModel;
import application.beadando3.model.InterfacesModel;

public class FeaturesModelServiceImplementation implements ServicesInterface<FeaturesModel> {
	
	private FeaturesModelDAO dao = new FeaturesModelDAO();
    private final static Logger logger = LoggerFactory.getLogger(FeaturesModelServiceImplementation.class);
	
	@Override
	public void save(FeaturesModel e) {
		if (validateFeaturesModel(e)){
			logger.info("Saving featuresmodel");

			dao.create(e);
			
		}
		else {
			throw new IllegalArgumentException("Valamelyik mező üres!");
		}
	}

	@Override
	public void update(FeaturesModel e) {
		if (!validateFeaturesModel(e)){
			throw new IllegalArgumentException("Valamelyik mező üres!");			
		}
		
		
		else {		
			logger.info("Editing interfacemodel");
			dao.edit(e);
		}		
	}

	@Override
	public void delete(FeaturesModel e) {
		if (!checkDuplicatesFeaturesModel(e)){
			throw new IllegalArgumentException("Ez az interface nem létezik!");
		}
		else{
			logger.info("Deleting interfacemodel");
			dao.remove(e);
			
		}		
	}

	@Override
	public  List<FeaturesModel> getAll() {
		logger.info("Finding all features");
		return  dao.findAll();
	}

	@Override
	public String count() {
		logger.info("Counting features in the database");
		return dao.count();
	}

	public List<String> listAllPlatforms(){
		return dao.findPlatforms();
	}
	
	public boolean validateFeaturesModel(FeaturesModel featuresModel){
		boolean valid = true;
		if ( featuresModel.getBGP()== null || featuresModel.getEIGRP() == null || featuresModel.getMAXI() == null||featuresModel.getMPLS()==null||featuresModel.getNAT()==null||featuresModel.getNETFLOW()==null||featuresModel.getOSPF()==null||featuresModel.getOSPF()==null||featuresModel.getPlatform_name()==null||featuresModel.getQOS()==null||featuresModel.getRIP()==null) {
        	valid = false;
        }
		return valid;
	}
	public boolean checkDuplicatesFeaturesModel(FeaturesModel featuresModel){
		boolean valid = false;
		
		if ((dao.getFeaturesModelByFeatures_name((featuresModel.getPlatform_name()))) != null){
			valid = true;
		}
		
		return valid;
	}

}
