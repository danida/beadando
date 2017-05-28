package application.beadando3.view;

import application.beadando3.Main;
import application.beadando3.model.LoginModel;
import application.beadando3.services.implementations.LoginModelServicesImplementation;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author danida
 *
 */
public class AdminsView {

	@FXML 
	private ListView<LoginModel> admins;
	@FXML
	private ListView<LoginModel> users;
	@FXML 
	private AnchorPane Anchornew;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private CheckBox admin;
	
	/**
	 * Ok button clicked or not.
	 */
	private boolean OkClicked;

	
	/**
	 * Initializing the controller.
	 */
	@FXML
	public void initialize(){
		LoginModelServicesImplementation lms = new LoginModelServicesImplementation();
		admins.setItems(FXCollections.observableList(lms.listAdmins()));
		users.setItems(FXCollections.observableList(lms.listUsers()));
		Anchornew.setVisible(false);
	}
	
	/**
	 * Handle, if delete button clicked.
	 */
	@FXML
	public void handleDeleting(){
		LoginModelServicesImplementation lms = new LoginModelServicesImplementation();
		if (admins.getSelectionModel().getSelectedItem()!=null){
			lms.delete(admins.getSelectionModel().getSelectedItem());
			admins.setItems(FXCollections.observableList(lms.listAdmins()));
		}
		if (users.getSelectionModel().getSelectedItem()!=null){
			lms.delete(users.getSelectionModel().getSelectedItem());
			users.setItems(FXCollections.observableList(lms.listUsers()));
		}
		
	}
	/**
	 * Handle, if the new button clicked.
	 */
	@FXML
	public void handleNew(){
		username.setText("");
		password.setText("");
		Anchornew.setVisible(true);
	}
	/**
	 * Saves the loginmodel.
	 */
	@FXML
	public void handleSav(){
		LoginModel um = new LoginModel();
		um.setUser(username.getText());
		um.setPassword(password.getText());
		if (admin.selectedProperty().get()){
		um.setIsAdmin(1);
		}
		else {
			um.setIsAdmin(0);
		}
		LoginModelServicesImplementation lms = new LoginModelServicesImplementation();
		lms.save(um);
		username.setText("");
		password.setText("");
		Anchornew.setVisible(false);
		admins.setItems(FXCollections.observableList(lms.listAdmins()));
		users.setItems(FXCollections.observableList(lms.listUsers()));
		admins.refresh();
		users.refresh();
		
	}
	/**
	 * Cances the current operation.
	 */
	@FXML
	public void handleCancel(){
		username.setText("");
		password.setText("");
		Anchornew.setVisible(false);
		
	}
	
	

	/**
	 * @return Returns if the ok clicked or not.
	 */
	public boolean isOkClicked() {
		return OkClicked;
	}


	/**
	 * @return Returns the admins.
	 */
	public ListView<LoginModel> getAdmins() {
		return admins;
	}



	/**
	 * @return Returns the normal users.
	 */
	public ListView<LoginModel> getUsers() {
		return users;
	}

	

}
