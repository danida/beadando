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
	
	private boolean OkClicked;

	
	@FXML
	public void initialize(){
		LoginModelServicesImplementation lms = new LoginModelServicesImplementation();
		admins.setItems(FXCollections.observableList(lms.listAdmins()));
		users.setItems(FXCollections.observableList(lms.listUsers()));
		Anchornew.setVisible(false);
	}
	
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
	@FXML
	public void handleNew(){
		username.setText("");
		password.setText("");
		Anchornew.setVisible(true);
	}
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
	@FXML
	public void handleCancel(){
		username.setText("");
		password.setText("");
		Anchornew.setVisible(false);
		
	}
	
	
	public void setDialogStage(Stage dialogStage) {
		// TODO Auto-generated method stub
		
	}


	public void setUser(LoginModel user) {
		// TODO Auto-generated method stub
		
	}


	public boolean isOkClicked() {
		return OkClicked;
	}


	public ListView<LoginModel> getAdmins() {
		return admins;
	}


	public void setAdmins(ListView<LoginModel> admins) {
		this.admins = admins;
	}


	public ListView<LoginModel> getUsers() {
		return users;
	}


	public void setUsers(ListView<LoginModel> users) {
		this.users = users;
	}


	public AnchorPane getAnchornew() {
		return Anchornew;
	}


	public void setAnchornew(AnchorPane anchornew) {
		this.Anchornew = anchornew;
	}


	public void setOkClicked(boolean okClicked) {
		OkClicked = okClicked;
	}
	

}
