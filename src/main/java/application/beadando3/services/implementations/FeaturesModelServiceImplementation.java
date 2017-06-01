package application.beadando3.services.implementations;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Interfaces.ServicesInterface;
import application.beadando3.DAO.FeaturesModelDAO;
import application.beadando3.model.FeaturesModel;
import application.beadando3.model.InterfacesModel;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;

/**
 * @author danida
 *
 */
public class FeaturesModelServiceImplementation implements ServicesInterface<FeaturesModel> {


	private FeaturesModelDAO dao;
	private final static Logger logger = LoggerFactory.getLogger(FeaturesModelServiceImplementation.class);



	
	/**
	 * Non default constructor.
	 * @param dao - dao of the featuresmodel
	 */
	public FeaturesModelServiceImplementation(FeaturesModelDAO dao) {
		this.dao = dao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(FeaturesModel e) {
		if (validateFeaturesModel(e)) {
			logger.info("Saving featuresmodel");

			dao.create(e);

		} else {
			throw new IllegalArgumentException("Valamelyik mező üres!");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(FeaturesModel e) {
		if (!validateFeaturesModel(e)) {
			throw new IllegalArgumentException("Valamelyik mező üres!");
		}
		else if (!checkDuplicatesFeaturesModel(e)){
			throw new IllegalArgumentException("Ez a feature nem létezik!");
		}

		else {
			logger.info("Editing featuresmodel");
			dao.edit(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(FeaturesModel e) {
		if (!checkDuplicatesFeaturesModel(e)) {
			throw new IllegalArgumentException("Ez az interface nem létezik!");
		} else {
			logger.info("Deleting interfacemodel");
			dao.remove(e);

		}
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public List<FeaturesModel> getAll() {
		logger.info("Finding all features");
		return dao.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String count() {
		logger.info("Counting features in the database");
		return dao.count();
	}

	/**
	 * List all of the available platforms from the database.
	 * 
	 * @return Returns all of the platoforms
	 */
	public List<String> listAllPlatforms() {
		return dao.findPlatforms();
	}

	/**
	 * Checks if the feature is savable or not.
	 * 
	 * @param featuresModel
	 *            - One instance of the featuresmodel
	 * @return Returns true if the feature is savable
	 */
	public boolean validateFeaturesModel(FeaturesModel featuresModel) {
		boolean valid = true;
		if (featuresModel.getBGP() == null || featuresModel.getEIGRP() == null || featuresModel.getMAXI() == null
				|| featuresModel.getMPLS() == null || featuresModel.getNAT() == null
				|| featuresModel.getNETFLOW() == null || featuresModel.getOSPF() == null
				|| featuresModel.getOSPF() == null || featuresModel.getPlatform_name() == null
				|| featuresModel.getQOS() == null || featuresModel.getRIP() == null) {
			valid = false;
		}
		return valid;
	}

	/**
	 * Checks if the feature already exists or not.
	 * 
	 * @param featuresModel
	 *            - instance of the featuremodel
	 * @return Returns if the featuresmode already exists or not
	 */
	public boolean checkDuplicatesFeaturesModel(FeaturesModel featuresModel) {
		boolean valid = false;

		if ((dao.getFeaturesModelByFeatures_name((featuresModel.getPlatform_name()))) != null) {
			valid = true;
		}

		return valid;
	}

	/**
	 * Calculates the maximum performance of the router.
	 * 
	 * @param boxes
	 *            - Available features
	 * @param bandwidth
	 *            - The maximum data thougput
	 * @param platform
	 *            - Platform of the router
	 * @return Returns the performance value
	 */
	public double calculatePerforfmance(List<CheckBox> boxes, String bandwidth, String platform) {
		if (boxes == null || bandwidth == null || platform == null) {
			throw new IllegalArgumentException("One of the fields is missing");
		}
		int b = new Integer(bandwidth);
		double ret = 1;
		for (CheckBox box : boxes) {
			if (box.isSelected()) {
				ret += 1;
			}
		}
		if ((ret * b) / dao.getMaxibyPlatform(platform) > 100) {
			throw new InvalidParameterException(
					"With these values it is not guaranteed the router can handle this performance");
		}
		return (ret * b) / dao.getMaxibyPlatform(platform);

	}

	/**
	 * List the available features.
	 * 
	 * @param platform - Chosen platform
	 * @return Returns the list of the features
	 */
	public FeaturesModel getFeatureModelList(String platform) {

		return dao.getFeaturesModelByFeatures_name(platform).get(0);
	}

	/**
	 * Setter of the DAO.
	 * @return Returns the dao of the service
	 */
	public FeaturesModelDAO getDao() {
		return dao;
	}

	/**
	 * Getter of the DAO. 
	 * @param dao - dao of the service
	 */
	public void setDao(FeaturesModelDAO dao) {
		this.dao = dao;
	}
	
}
