package application.beadando3.services.implementations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Interfaces.ServicesInterface;
import application.beadando3.DAO.TracerouteModelDAO;
import application.beadando3.model.TracerouteModel;

/**
 * @author danida
 *
 */
public class TracerouteModelServiceImplementation implements ServicesInterface<TracerouteModel> {
	TracerouteModelDAO dao;
    private final static Logger logger = LoggerFactory.getLogger(TracerouteModelServiceImplementation.class);
    
    /**
     * Default constructor.
     */
    public TracerouteModelServiceImplementation() {
		super();
		 dao = new TracerouteModelDAO();
    }
	/**
	 * Non default constructor.
	 * @param dao - dao of traceroutemodel
	 */
	public TracerouteModelServiceImplementation(TracerouteModelDAO dao) {
		super();
		this.dao = dao;
	}
	/**
	 *{@inheritDoc}
	 */
	@Override
		public void save(TracerouteModel e) {
		
			if (validateTracerouteModel(e)){
				logger.info("Saving traceroutemodel");
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
	public void update(TracerouteModel e) {

		if (!validateTracerouteModel(e)){
			throw new IllegalArgumentException("Valamelyik mező üres!");			
		}
		else if (checkDuplicatesTracerouteModel(e)){
			throw new IllegalArgumentException("Ez az interface már létezik!");
		}
		else {
			logger.info("Editing traceroutemodel");

			dao.edit(e);
		}		
	}
	 /**
	 *{@inheritDoc}
	 */
	@Override
	public void delete(TracerouteModel e) {
		if (!checkDuplicatesTracerouteModel(e)){
			throw new IllegalArgumentException("Ez az interface nem létezik!");
		}
		else{
			logger.info("Deleting traceroutemodel");
			dao.remove(e);
			
		}				
	}
	 /**
	 *{@inheritDoc}
	 */
	@Override
	public List getAll() {
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
	 * Checks if the traceroute is savable.
	 * @param TracerouteModel - One instance of traceroute
	 * @return Returns true if it is savables
	 */
	public boolean validateTracerouteModel(TracerouteModel TracerouteModel){
		boolean valid = true;
		if ( TracerouteModel.getDestination()== null || TracerouteModel.getOutput() == null) {
        	valid = false;
        }
		return valid;
	}
	/**
	 * Checks if the traceroute already exists in the table.
	 * @param TracerouteModel - traceroute
	 * @return Returns true if it is already exists
	 */
	public boolean checkDuplicatesTracerouteModel(TracerouteModel TracerouteModel){
		boolean valid = false;
		
		if (dao.getTracerouteModelbyId(TracerouteModel.getId()) != null){
			valid = true;
		}
		
		return valid;
	}
	/**
	 * Getter of the dao.
	 * @return Returns the dao for the traceroutemodelservice.
	 */
	public TracerouteModelDAO getDao() {
		return dao;
	}
	/**
	 * Setter of the dao.
	 * @param dao - dao of traceroutemodeldao
	 */
	public void setDao(TracerouteModelDAO dao) {
		this.dao = dao;
	}
	
}
