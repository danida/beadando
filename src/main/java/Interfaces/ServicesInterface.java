package Interfaces;

import java.util.List;

/**
 * @author danida
 *
 * @param <T>
 */
public interface ServicesInterface<T> {
	
	 /**
	  * Validates if the model instance is savable or not, and saves it.
	 * @param e - model
	 */
	public void save(T e) ;
	    /**
	     * Updates the model with new data.
	     * @param e -model
	     */
	    public void update(T e) ;
	    /**
	     * Delete the model if it exists.
	     * @param e -model
	     */
	    public void delete(T e);
	    /**
	     * List all of the records form the table.
	     * @return - Lists all of the models available in the table
	     */
	    public List<T> getAll() ;
	    /**
	     * Counts the records in a table.
	     * @return - Counts the number of records
	     */
	    public String count() ;
}
