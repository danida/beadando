package application.beadando3.view;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import application.beadando3.Main;
import application.beadando3.model.FeaturesModel;
import application.beadando3.model.RouterModel;
import application.beadando3.services.implementations.FeaturesModelServiceImplementation;
import eu.hansolo.medusa.Gauge;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * @author danida
 *
 */
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

	private String features;
	@FXML
	private ChoiceBox<String> platforms;
	@FXML
	private CheckBox EIGRP = new CheckBox();
	@FXML
	private CheckBox OSPF = new CheckBox();
	@FXML
	private CheckBox RIP = new CheckBox();
	@FXML
	private CheckBox BGP = new CheckBox();
	@FXML
	private CheckBox MPLS = new CheckBox();
	@FXML
	private CheckBox NETFLOW = new CheckBox();
	@FXML
	private CheckBox QOS = new CheckBox();
	@FXML
	private CheckBox NAT = new CheckBox();
	@FXML
	private Gauge CheckPerformance = new Gauge();
	@FXML
	private TextField bandwidth;

	private List<CheckBox> boxes;

	private Stage dialogStage;
	/**
	 * One instance of the routermodel.
	 */
	private RouterModel router;
	private boolean okClicked = false;
	private static FeaturesModelServiceImplementation fms = new FeaturesModelServiceImplementation();
	private final static Logger logger = LoggerFactory.getLogger(RouterEditDialogView.class);
	/**
	 * Main variable.
	 */
	public static Main mainApp;

	/**
	 * Initializes the controller class. This method is automatically called.
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		boxes =  new ArrayList<>(Arrays.asList(EIGRP,OSPF,RIP,BGP,MPLS,NETFLOW,QOS,NAT));
	for (CheckBox i: boxes){
		i.setOnAction((event) -> {
			CheckPerformance.setValue(calculatePerforfmance());
		});
		}
	
	platforms.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
	      @Override
	      public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
	    	  router.setPlatform((platforms.getItems().get((Integer) number2)));
	    		FeaturesModel f = fms.getFeatureModelList(router.getPlatform());
	    	  	for (CheckBox i: boxes){
	    	  		i.setSelected(false);
	    	  		i.setDisable(true);
	    	  	}	
	    				if (f.getBGP()==1){BGP.setDisable(false);}
	    				if(f.getEIGRP()==1){EIGRP.setDisable(false);}
	    				if(f.getMPLS()==1){MPLS.setDisable(false);}
	    				if(f.getNAT()==1){NAT.setDisable(false);}
	    				if(f.getNETFLOW()==1){NETFLOW.setDisable(false);}
	    				if(f.getOSPF()==1){OSPF.setDisable(false);}
	    				if(f.getQOS()==1){QOS.setDisable(false);}
	    				if(f.getRIP()==1){RIP.setDisable(false);}
	    				
	    				CheckPerformance.setValue(calculatePerforfmance());
	    		}
	      
	    });

	}

	/**
	 * Sets the stage of this dialog.
	 * 
	 * @param dialogStage - Dialogstage of the main
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Sets the person to be edited in the dialog.
	 * 
	 * @param router - One instance of the routermodel
	 */
	public void setRouter(RouterModel router) {
		this.router = router;
		platforms.getItems().addAll(FXCollections.observableArrayList(fms.listAllPlatforms()));
		routerSerial.setText(router.getSerial_number());
		configuredBy.setText(router.getWho_Configured());
		configured.setText(router.getWhen_configured().toString());
		confReg.setText(router.getConfig_register());
		IOS.setText(router.getIOS());
		man_IP.setText(router.getMan_IP());
		routerName.setText(router.getName());
		if (router.getFeatures().contains("EIGRP")) {
			EIGRP.setSelected(true);
		}
		if (router.getFeatures().contains("OSPF")) {
			OSPF.setSelected(true);
		}
		if (router.getFeatures().contains("RIP")) {
			RIP.setSelected(true);
		}
		if (router.getFeatures().contains("MPLS")) {
			MPLS.setSelected(true);
		}
		if (router.getFeatures().contains("NETFLOW")) {
			NETFLOW.setSelected(true);
		}
		if (router.getFeatures().contains("BGP")) {
			BGP.setSelected(true);
		}
		if (router.getFeatures().contains("NAT")) {
			NAT.setSelected(true);
		}
	}

	/**
	 * Initialize the routermodel instance for the new router.
	 * @param router - Routermodel for new router
	 */
	public void setNewRouter(RouterModel router) {
		this.router = router;
		platforms.getItems().addAll(FXCollections.observableArrayList(fms.listAllPlatforms()));

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
	 * @return Returns if the okclicked
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
		features = EIGRP.isSelected() ? "EIGRP" : "";
		features += OSPF.isSelected() ? ":OSPF" : "";
		features += RIP.isSelected() ? ":RIP" : "";
		features += BGP.isSelected() ? ":BGP" : "";
		features += MPLS.isSelected() ? ":MPLS" : "";
		features += NETFLOW.isSelected() ? ":NETFLOW" : "";
		features += QOS.isSelected() ? ":QOS" : "";
		features += NAT.isSelected() ? ":NAT" : "";
		router.setFeatures(features);

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
	 * Calculates the maximum performance of the router.
	 * @return Returns a number for the gauge
	 */
	public double calculatePerforfmance() {
		try{
		return fms.calculatePerforfmance(boxes, bandwidth.getText(), router.getPlatform());
		}
		catch (InvalidParameterException ex){
			logger.error("User trying to save a new router with insufficient performance");
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Invalid Arguments");
			alert.setHeaderText("Your adjusted fields can cause insufficent performance");
			alert.setContentText("Please note this can cause outage!");
			alert.showAndWait();
			return 0;
		}
		catch(IllegalArgumentException ex){
			logger.error("User trying to save a new router with insufficient performance");
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Invalid Arguments");
			alert.setHeaderText("You have to set the following arguments:Routername,Management IP,Platform,Bandwidth,Features(Checkboxes)");
			alert.setContentText("Please note this can cause outage!");
			alert.showAndWait();
			return 0;

		}
		finally {
			return fms.calculatePerforfmance(boxes, bandwidth.getText(), router.getPlatform());

		}
	}

	/**
	 * Getter of the mainApp.
	 * @return Returns the mainApp of the RouterEditDialogView
	 */
	public static Main getMainApp() {
		return mainApp;
	}

	/**
	 * Setter of the mainApp.
	 * @param mainApp - Main.class isntance
	 */
	public static void setMainApp(Main mainApp) {
		RouterEditDialogView.mainApp = mainApp;
	}

	
	


}