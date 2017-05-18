package application.beadando3.view;

import application.beadando3.Main;
import application.beadando3.DAO.RouterModelDAO;
import application.beadando3.model.RouterModel;
import application.beadando3.services.implementations.RouterModelServiceImplementation;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class RouterView {
    @FXML
    public  TableView<RouterModel> routerTable;
    @FXML
    private TableColumn<RouterModel, String> name;
    @FXML
    private TableColumn<RouterModel, String> man_ip;
    @FXML
	private Label  routerName;
    @FXML
	private Label  routerPlatform;
    @FXML
	private Label  routerSerial;
    @FXML
	private Label  configuredBy;  
    @FXML
	private Label  configured;
    @FXML
	private Label  confReg;
    @FXML
	private Label  IOS;
    @FXML
	private Label  man_IP;
	@FXML
	private Label bootstrap;

    // Reference to the main application.
    public static Main mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public RouterView() {
    }

    private void showRouterDetails(RouterModel router) {
        if (router != null) {
            // Fill the labels with info from the person object.
        	routerPlatform.setText(router.getPlatform());
        	routerSerial.setText(router.getSerial_number());
        	configuredBy.setText(router.getWho_Configured());
        	configured.setText(router.getWhen_configured().toLocaleString());
        	confReg.setText(router.getConfig_register());
        	IOS.setText(router.getIOS());
        	man_IP.setText(router.getMan_IP());
        	routerName.setText(router.getName());
        	bootstrap.setText(router.getBootstrap_version());


          
        } else {
            // Person is null, remove all the text.
        	routerName.setText("");
        	routerPlatform.setText("");
        	routerSerial.setText("");
        	configuredBy.setText("");
        	configured.setText("");
        	confReg.setText("");
        	IOS.setText("");
        	man_IP.setText("");
        	bootstrap.setText("");
        }
    }
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	RouterModelDAO rm = new RouterModelDAO();
    	routerTable.setItems(FXCollections.observableList(rm.findAll()));
        name.setCellValueFactory(cellData -> cellData.getValue().getRouterNameProperty());
        man_ip.setCellValueFactory(cellData -> cellData.getValue().getMan_IPProperty());
        showRouterDetails(null);

        routerTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showRouterDetails(newValue));
    }
    @FXML
    private void handleDeleteRouter() {
    	RouterModelDAO rm = new RouterModelDAO();
        int selectedIndex = routerTable.getSelectionModel().getSelectedIndex();
        RouterModel routermodel= routerTable.getItems().get(selectedIndex);
    	rm.remove(routermodel);
    	routerTable.setItems(FXCollections.observableList(rm.findAll()));
    }

    @FXML
    private void handleNewRouter() {
    	RouterModel tempRouter = new RouterModel(null, "routerName", "routerPlatform", "routerSerial", "2016-07-02 23:12:11.1","ConfiguredBy", "confReg", "iOS", "man_IP", "bootstrap");
            boolean okClicked = mainApp.showRouterNewDialog(tempRouter);
            if ( routerName.getText()!= null && routerPlatform.getText() != null && routerSerial.getText() != null) {
            	RouterModelServiceImplementation rm = new RouterModelServiceImplementation();
            	rm.save(tempRouter);
            	routerTable.setItems(FXCollections.observableList(rm.getAll()));
            }

        
    }
       
    

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditRouter() {
        RouterModel selectedRouter = routerTable.getSelectionModel().getSelectedItem();
        if (selectedRouter != null) {
            boolean okClicked = mainApp.showRouterEditDialog(selectedRouter);
            if (okClicked) {
            	RouterModelDAO rm = new RouterModelDAO();
            	rm.edit(selectedRouter);
            	routerTable.setItems(FXCollections.observableList(rm.findAll()));
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Router Selected");
            alert.setContentText("Please select a router in the table.");

            alert.showAndWait();
        }
    }
    @FXML
    private void handleSearchRouter() {
            boolean okClicked = mainApp.showRouterSearchDialog();
            if (okClicked) {
            	RouterModelDAO rm = new RouterModelDAO();
            	routerTable.setItems(FXCollections.observableList(rm.findAll()));
            }

            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Router Selected");
            alert.setContentText("Please select a router in the table.");

            alert.showAndWait();
        }
    @FXML
    private void handleShowInterfaces() {
    	 RouterModel selectedRouter = routerTable.getSelectionModel().getSelectedItem();
         if (selectedRouter != null) {
             boolean okClicked = mainApp.showInterfacesSearchDialog(selectedRouter);
             if (okClicked) {
             	RouterModelDAO rm = new RouterModelDAO();
             	routerTable.setItems(FXCollections.observableList(rm.findAll()));
             }

         } else {
             // Nothing selected.
             Alert alert = new Alert(AlertType.WARNING);
             alert.initOwner(mainApp.getPrimaryStage());
             alert.setTitle("No Selection");
             alert.setHeaderText("No Router Selected");
             alert.setContentText("Please select a router in the table.");

             alert.showAndWait();
         }
        }
   
}