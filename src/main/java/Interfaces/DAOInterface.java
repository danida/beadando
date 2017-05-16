package Interfaces;

import java.util.List;

import javax.persistence.Entity;

import application.beadando3.model.RouterModel;


public interface DAOInterface<T> {
    public void create(T e) ;
    public void edit(T e) ;
    public void remove(T e);
    public <T> List<T> findAll() ;
    public String count() ;

    

}
