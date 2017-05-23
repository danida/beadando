package application.beadando3.view;

import application.beadando3.Main;
import application.beadando3.model.LoginModel;
import application.beadando3.services.implementations.LoginModelServicesImplementation;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginView {

	@FXML
	TextField username;
	@FXML
	TextField password;
	@FXML
	Label message;
	
	private Stage dialogStage;
	private boolean okClicked = false;
	

	private static Main  mainApp; 
	
	@FXML 
	public void initialize(){
		message.setVisible(false);
		
		
	}
	
	public void authenticate(){
		LoginModel lm = new LoginModel(username.getText(),password.getText());
		LoginModelServicesImplementation lms = new LoginModelServicesImplementation();
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


	public static Main getMainApp() {
		return mainApp;
	}


	public static void setMainApp(Main mainApp) {
		LoginView.mainApp = mainApp;
	}
	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	public void setOkClicked(boolean okClicked) {
		this.okClicked = okClicked;
	}

}
