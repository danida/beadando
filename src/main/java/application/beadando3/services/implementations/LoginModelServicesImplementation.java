package application.beadando3.services.implementations;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Interfaces.ServicesInterface;
import application.beadando3.DAO.LoginModelDAO;
import application.beadando3.model.LoginModel;
import static org.apache.commons.codec.digest.DigestUtils.*;

public class LoginModelServicesImplementation  implements ServicesInterface<LoginModel> {

	private LoginModelDAO dao = new LoginModelDAO();
    private final static Logger logger = LoggerFactory.getLogger(LoginModelServicesImplementation.class);

	@Override
	public void save(LoginModel e) {
		if (validateLoginModel(e)){
			logger.info("Saving loginmodel");

			dao.create(e);
			
		}
		else {
			throw new IllegalArgumentException("Valamelyik mező üres!");
		}
				
	}

	@Override
	public void update(LoginModel e) {
		if (!validateLoginModel(e)){
			throw new IllegalArgumentException("Valamelyik mező üres!");			
		}
		
		
		else {		
			logger.info("Editing loginmodel");
			dao.edit(e);
		}		
	}

	@Override
	public void delete(LoginModel e) {
		if (!checkDuplicatesLoginModel(e)){
			throw new IllegalArgumentException("This user already exists");
		}
		else{
			logger.info("Deleting interfacemodel");
			dao.remove(e);
			
		}
				
	}

	@Override
	public <T> List<T> getAll() {
		logger.info("Finding all loginmodels");
		return  dao.findAll();
	}

	@Override
	public String count() {
		logger.info("Counting users in the database");

		return dao.count();
	}
	public LoginModel tryToAuthenticate (LoginModel lm){
		logger.info("One user is trying to authenticate");

		String username = lm.getUser();
		String password = lm.getPassword();
		
		if (!dao.getUserByUsername(lm.getUser()).isEmpty()){
			String passwordfromdb = dao.getPasswordForUser(lm.getUser());
			 byte[] byteArray =((Base64.encodeBase64(password.getBytes())));
			  String encodedString = new String(byteArray);
				if (encodedString.equals(passwordfromdb)){
					return dao.getUserByUsername(lm.getUser()).get(0);
				}
			
				
			
		}
		return null;
		
	}
	
	
	
	
	public boolean validateLoginModel(LoginModel loginModel){
		boolean valid = true;
		if ( loginModel.getPassword()== null || loginModel.getUser() == null ) {
        	valid = false;
        }
		return valid;
	}
	public boolean checkDuplicatesLoginModel(LoginModel loginModel){
		boolean valid = false;
		
		if (dao.getUserById(loginModel.getId()) != null){
			valid = true;
		}
		
		return valid;
	}
}