package application.beadando3.services.implementations;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Interfaces.ServicesInterface;
import application.beadando3.DAO.PingModelDAO;
import application.beadando3.model.PingModel;

/**
 * @author danida
 *
 */
public class PingModelServiceImplementation implements ServicesInterface<PingModel> {
	PingModelDAO dao = new PingModelDAO();
    private final static Logger logger = LoggerFactory.getLogger(PingModelServiceImplementation.class);
    /**
	 *{@inheritDoc}
	 */
	@Override
	public void save(PingModel e) {
		

		
		if (validatePingModel(e)){
			logger.info("Saving pingmodel");

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
	public void update(PingModel e) {

		if (!validatePingModel(e)){
			throw new IllegalArgumentException("Valamelyik mező üres!");			
		}
		else if (checkDuplicatesPingModel(e)){
			throw new IllegalArgumentException("Ez az interface már létezik!");
		}
		else {
			logger.info("Editing pingmodel");

			dao.edit(e);
		}
				
	}
	/**
	 *{@inheritDoc}
	 */
	@Override
	public void delete(PingModel e) {
		if (!checkDuplicatesPingModel(e)){
			throw new IllegalArgumentException("Ez az interface nem létezik!");
		}
		else{
			logger.info("Removing pingmodel");

			dao.remove(e);
			
		}		
	}
	/**
	 *{@inheritDoc}
	 */
	@Override
	public List<PingModel> getAll() {
		logger.info("List all pingmodel");

		return dao.findAll();
	}
	/**
	 *{@inheritDoc}
	 */
	@Override
	public String count() {
		return dao.count();
	}
	
	/**
	 * Validates the ping if it is valid or not.
	 * @param PingModel - One instance of pingmodel
	 * @return Returns true if the pingmodel is savable
	 */
	public boolean validatePingModel(PingModel PingModel){
		boolean valid = true;
		if ( PingModel.getDestination()== null || PingModel.getOutput() == null) {
        	valid = false;
        }
		return valid;
	}
	/**
	 * Checks if the ping record already is in the table.
	 * @param PingModel - Model of ping what should be saved
	 * @return Returns true or false depends on it the pingmodel already there or not
	 */
	public boolean checkDuplicatesPingModel(PingModel PingModel){
		boolean valid = false;
		
		if (dao.getPingModelbyId(PingModel.getId()) != null){
			valid = true;
		}
		
		return valid;
	}
}
