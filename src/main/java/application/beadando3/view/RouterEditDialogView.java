package application.beadando3.view;

import java.time.LocalDateTime;

import application.beadando3.model.RouterModel;
import application.beadando3.services.implementations.FeaturesModelServiceImplementation;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RouterEditDialogView {

	@FXML
	private TextField routerName;	
	@FXML
	private TextField routerSerial;
	@FXML
	private TextField configuredBy;
	@FXML
	private TextField configured;
	@FXML
	private TextField confReg;
	@FXML
	private TextField IOS;
	@FXML
	private TextField man_IP;
	@FXML
	private ChoiceBox<String> features;
	@FXML
	private ChoiceBox<String> platforms;
	@FXML
	private CheckBox EIGRP;
	@FXML
	private CheckBox OSPF;
	@FXML
	private CheckBox RIP;
	@FXML
	private CheckBox BGP;
	@FXML
	private CheckBox MPLS;
	@FXML
	private CheckBox NETFLOW;
	@FXML
	private CheckBox QOS;
	@FXML
	private CheckBox NAT;
	
	
	
	private Stage dialogStage;
	private RouterModel router;
	private boolean okClicked = false;
	private static FeaturesModelServiceImplementation fms = new FeaturesModelServiceImplementation();

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
	}

	/**
	 * Sets the stage of this dialog.
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Sets the person to be edited in the dialog.
	 * 
	 * @param person
	 */
	public void setRouter(RouterModel router) {
		this.router = router;
	//	features.getItems().addAll(FXCollections.observableArrayList("EIGRP","OSPF","RIP","BGP","NETFLOW","NAT","MPLS","QOS"));
		platforms.getItems().addAll(FXCollections.observableArrayList(fms.listAllPlatforms()));
		routerSerial.setText(router.getSerial_number());
		configuredBy.setText(router.getWho_Configured());
		configured.setText(router.getWhen_configured().toString());
		confReg.setText(router.getConfig_register());
		IOS.setText(router.getIOS());
		man_IP.setText(router.getMan_IP());
		routerName.setText(router.getName());
	}

	public void setNewRouter(RouterModel router) {
		this.router = router;
		platforms.getItems().addAll(FXCollections.observableArrayList(fms.listAllPlatforms()));

//		features.getItems().addAll(FXCollections.observableArrayList("EIGRP","OSPF","RIP","BGP","NETFLOW","NAT","MPLS","QOS"));
		routerSerial.setText("");
		configuredBy.setText("");
		configured.setText("");
		confReg.setText("");
		IOS.setText("");
		man_IP.setText("");
		routerName.setText("");
	}

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 * 
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	
	@FXML
	private void handleOk() {
		
			router.setPlatform(platforms.getSelectionModel().getSelectedItem());
			router.setName(routerName.getText());
			router.setConfigured(configured.getText());
			router.setSerial_number(routerSerial.getText());
			router.setConfReg(confReg.getText());
			router.setConfigured(configuredBy.getText());
			router.setIOS(IOS.getText());
			router.setMan_IP(man_IP.getText());
			router.setWhen_configured(LocalDateTime.now());
			okClicked = true;
			dialogStage.close();
	
	}

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		okClicked = false;
		dialogStage.close();
	}

	/**
	 * Validates the user input in the text fields.
	 * 
	 * @return true if the input is valid
	 */
	
	
}