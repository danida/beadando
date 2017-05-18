package application.beadando3.services.implementations;

import java.util.List;

import Interfaces.ServicesInterface;
import application.beadando3.DAO.PingModelDAO;
import application.beadando3.model.PingModel;
import application.beadando3.model.PingModel;

public class PingModelServiceImplementation implements ServicesInterface<PingModel> {
	PingModelDAO dao = new PingModelDAO();
	
	@Override
	public void save(PingModel e) {
		if (validatePingModel(e)){
			dao.create(e);
		}
		else {
			throw new IllegalArgumentException("Valamelyik mező üres!");
		}
		
				
	}

	@Override
	public void update(PingModel e) {
		if (!validatePingModel(e)){
			throw new IllegalArgumentException("Valamelyik mező üres!");			
		}
		else if (checkDuplicatesPingModel(e)){
			throw new IllegalArgumentException("Ez az interface már létezik!");
		}
		else {
			dao.edit(e);
		}
				
	}

	@Override
	public void delete(PingModel e) {
		if (!checkDuplicatesPingModel(e)){
			throw new IllegalArgumentException("Ez az interface nem létezik!");
		}
		else{
			dao.remove(e);
			
		}		
	}

	@Override
	public <T> List<T> getAll() {
		return dao.findAll();
	}

	@Override
	public String count() {
		return dao.count();
	}
	public boolean validatePingModel(PingModel PingModel){
		boolean valid = true;
		if ( PingModel.getDestination()== null || PingModel.getOutput() == null) {
        	valid = false;
        }
		return valid;
	}
	public boolean checkDuplicatesPingModel(PingModel PingModel){
		boolean valid = false;
		
		if (dao.getPingModelbyId(PingModel.getId()) != null){
			valid = true;
		}
		
		return valid;
	}
}
