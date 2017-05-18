package application.beadando3.services.implementations;

import java.util.List;

import Interfaces.ServicesInterface;
import application.beadando3.DAO.TracerouteModelDAO;
import application.beadando3.model.PingModel;
import application.beadando3.model.TracerouteModel;

public class TracerouteModelServiceImplementation implements ServicesInterface<TracerouteModel> {
	TracerouteModelDAO dao = new TracerouteModelDAO();
	
	@Override
		public void save(TracerouteModel e) {
			if (validateTracerouteModel(e)){
				dao.create(e);
			}
			else {
				throw new IllegalArgumentException("Valamelyik mező üres!");
			}
			
		
	}

	@Override
	public void update(TracerouteModel e) {
		if (!validateTracerouteModel(e)){
			throw new IllegalArgumentException("Valamelyik mező üres!");			
		}
		else if (checkDuplicatesTracerouteModel(e)){
			throw new IllegalArgumentException("Ez az interface már létezik!");
		}
		else {
			dao.edit(e);
		}		
	}

	@Override
	public void delete(TracerouteModel e) {
		if (!checkDuplicatesTracerouteModel(e)){
			throw new IllegalArgumentException("Ez az interface nem létezik!");
		}
		else{
			dao.remove(e);
			
		}				
	}

	@Override
	public List getAll() {
		return dao.findAll();

	}

	@Override
	public String count() {
		return dao.count();

	}
	public boolean validateTracerouteModel(TracerouteModel TracerouteModel){
		boolean valid = true;
		if ( TracerouteModel.getDestination()== null || TracerouteModel.getOutput() == null) {
        	valid = false;
        }
		return valid;
	}
	public boolean checkDuplicatesTracerouteModel(TracerouteModel TracerouteModel){
		boolean valid = false;
		
		if (dao.getTracerouteModelbyId(TracerouteModel.getId()) != null){
			valid = true;
		}
		
		return valid;
	}
}
