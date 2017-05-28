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

/**
 * @author danida
 *
 */
public class LoginModelServicesImplementation  implements ServicesInterface<LoginModel> {

	private LoginModelDAO dao = new LoginModelDAO();
    private final static Logger logger = LoggerFactory.getLogger(LoginModelServicesImplementation.class);

    /**
	 *{@inheritDoc}
	 */
	@Override
	public void save(LoginModel e) {
		if (validateLoginModel(e)){
			logger.info("Saving loginmodel");
			e.setPassword(encryptPassword(e.getPassword()));
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
	public void update(LoginModel e) {
		if (!validateLoginModel(e)){
			throw new IllegalArgumentException("Valamelyik mező üres!");			
		}
		
		
		else {		
			logger.info("Editing loginmodel");
			dao.edit(e);
		}		
	}
	/**
	 *{@inheritDoc}
	 */
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
	/**
	 *{@inheritDoc}
	 */
	@Override
	public List<LoginModel> getAll() {
		logger.info("Finding all loginmodels");
		return  dao.findAll();
	}
	/**
	 *{@inheritDoc}
	 */
	@Override
	public String count() {
		logger.info("Counting users in the database");

		return dao.count();
	}
	
	/**
	 * Returns the list of admins.
	 * @return List of admin users
	 */
	public List<LoginModel> listAdmins(){
		return dao.getAllAdmins();
	}
	/**
	 * Returns the list of normal users.
	 * @return List the not admin users
	 */
	public List<LoginModel> listUsers(){
		return dao.getAllUsers();
	}
	
	/**
	 * Checks if the password for the user is valid or not.
	 * @param lm - LoginModel
	 * @return Returns true if the authentication is successful
	 */
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
	
	
	
	
	/**
	 * Validates if it is a real loginmodel.
	 * @param loginModel - Logimodel that has to be validated
	 * @return Returns true if the user is valid
	 */
	public boolean validateLoginModel(LoginModel loginModel){
		boolean valid = true;
		if ( loginModel.getPassword()== null || loginModel.getUser() == null ) {
        	valid = false;
        }
		return valid;
	}

	/**
	 * Checks if the loginModel already exists in the table or not.
	 * @param loginModel - Loginmodel that has to checked
	 * @return Returns true if the user already exists in the database
	 */
	public boolean checkDuplicatesLoginModel(LoginModel loginModel){
		boolean valid = false;
		
		if (dao.getUserById(loginModel.getId()) != null){
			valid = true;
		}
		
		return valid;
	}
	/**
	 * Encrypts the password with base64.
	 * @param pass - password of the user in plain text
	 * @return Returns the password of the user 
	 */
	public String encryptPassword (String pass){
		byte[] byteArray =((Base64.encodeBase64(pass.getBytes())));
	  String encodedString = new String(byteArray);
	  return encodedString;
	}
}