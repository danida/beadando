package Interfaces;

import java.util.List;

public interface ServicesInterface<T> {
	 public void save(T e) ;
	    public void update(T e) ;
	    public void delete(T e);
	    public <T> List<T> getAll() ;
	    public String count() ;
}
