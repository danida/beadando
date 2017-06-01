package application.beadando3.view;

import application.beadando3.Main;
import application.beadando3.DAO.LoginModelDAO;
import application.beadando3.model.LoginModel;
import application.beadando3.services.implementations.LoginModelServicesImplementation;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author danida
 *
 */
public class LoginView {

	@FXML
	TextField username;
	@FXML
	TextField password;
	@FXML
	Label message;
	
	private Stage dialogStage;
	private boolean okClicked = false;
	
	private LoginModelDAO lmdao= new LoginModelDAO();

	private static Main  mainApp; 
	
	/**
	 * Initialize the loginview.
	 */
	@FXML 
	public void initialize(){
		message.setVisible(false);
		
		
	}
	
	/**
	 * Calls the authentication for the user.
	 */
	public void authenticate(){
		LoginModel lm = new LoginModel(username.getText(),password.getText());
		LoginModelServicesImplementation lms = new LoginModelServicesImplementation(lmdao);
		LoginModel ret;
	    if ((ret =lms.tryToAuthenticate(lm))==null){
	    message.setVisible(true);
	    message.setTextFill(Color.web("#7776a3"));
	    message.setText("Incorrect password/username!");
	    }
	    else {
	    	okClicked = true;
	    	Main.setUser(ret);
	    	dialogStage.close();
	    }
				
	}


	/**
	 * Getter of the main.
	 * @return Returns the mainApp
	 */
	public static Main getMainApp() {
		return mainApp;
	}


	/**
	 * Setter of the main.
	 * @param mainApp - One instance of the Main
	 */
	public static void setMainApp(Main mainApp) {
		LoginView.mainApp = mainApp;
	}
	/**Getter of the dialogstage.
	 * @return Returns the dialogstage
	 */
	public Stage getDialogStage() {
		return dialogStage;
	}

	/**
	 * Setter of the dialogstage.
	 * @param dialogStage - One instance of dialogstagee
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Getter of the OkClicked.
	 * @return Returns true if the Ok was clicked
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Setter of the okClicked.
	 * @param okClicked - Boolean value that's tell if the okClicked or not
	 */
	public void setOkClicked(boolean okClicked) {
		this.okClicked = okClicked;
	}

}
