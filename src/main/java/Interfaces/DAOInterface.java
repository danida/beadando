package Interfaces;

import java.util.List;

import javax.persistence.Entity;

import application.beadando3.model.RouterModel;


/**
 * @author danida
 *
 * @param <T> - Type of elements are used for database operation
 */
public interface DAOInterface<T> {
    /**
     * Saves the model into the table
     * @param e model
     */
   
    public void create(T e) ;
    /**
     * Update the model in the table
     * @param e model
     */
    public void edit(T e) ;
    /**
     * Delete the model from the table
     * @param e model
     */
    public void remove(T e);
    /**
     * Listing all of the models available in the table
     * @return Returns the models with all of them values
     */
    public  List<T> findAll() ;
    /**
     * Counts the number of records for the table
     * @return Returns all of the records
     */
    public String count() ;

    

}
