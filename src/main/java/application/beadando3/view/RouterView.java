package application.beadando3.view;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

/**
 * @author danida
 *
 */
public class RouterView {
	/**
	 * List of the available routers in the database.
	 */
	@FXML
	public TableView<RouterModel> routerTable;
	@FXML
	private TableColumn<RouterModel, String> name;
	@FXML
	private TableColumn<RouterModel, String> man_ip;
	@FXML
	private Label routerName;
	@FXML
	private Label routerPlatform;
	@FXML
	private Label routerSerial;
	@FXML
	private Label configuredBy;
	@FXML
	private Label configured;
	@FXML
	private Label confReg;
	@FXML
	private Label IOS;
	@FXML
	private Label man_IP;

	/**
	 * Reference to the main application.
	 */
	public static Main mainApp;
	private final static Logger logger = LoggerFactory.getLogger(RouterView.class);

	

	/**
	 * Show the router details in the labels.
	 * @param router - Router that has been selected by the user
	 */
	private void showRouterDetails(RouterModel router) {
		if (router != null) {
			// Fill the labels with info from the person object.
			routerPlatform.setText(router.getPlatform());
			routerSerial.setText(router.getSerial_number());
			configuredBy.setText(router.getWho_Configured());
			configured.setText(router.getWhen_configured().toString());
			confReg.setText(router.getConfig_register());
			IOS.setText(router.getIOS());
			man_IP.setText(router.getMan_IP());
			routerName.setText(router.getName());

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
		}
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		RouterModelServiceImplementation rm = new RouterModelServiceImplementation();
		routerTable.setItems(FXCollections.observableList(rm.getAll()));
		name.setCellValueFactory(cellData -> cellData.getValue().getRouterNameProperty());
		man_ip.setCellValueFactory(cellData -> cellData.getValue().getMan_IPProperty());
		showRouterDetails(null);

		routerTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showRouterDetails(newValue));
	}

	/**
	 * Deletes the selected router.
	 */
	@FXML
	private void handleDeleteRouter() {
		try {
			RouterModelServiceImplementation rm = new RouterModelServiceImplementation();
			int selectedIndex = routerTable.getSelectionModel().getSelectedIndex();
			RouterModel routermodel = routerTable.getItems().get(selectedIndex);
			rm.delete(routermodel);
			routerTable.setItems(FXCollections.observableList(rm.getAll()));
		} catch (ArrayIndexOutOfBoundsException e) {
			logger.error("User trying to delete router without selecting it");
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No router record Selected");
			alert.setContentText("Please select a router result from the table.");

			alert.showAndWait();
		}
	}

	/**
	 * Add a new router to the databse if it is possible.
	 */
	@FXML
	private void handleNewRouter() {
		RouterModel tempRouter = new RouterModel(null, "routerName", "routerPlatform", "routerSerial",
				LocalDateTime.of(2016, 12, 22, 11, 10), "ConfiguredBy", "confReg", "iOS", "man_IP", "bootstrap");
		boolean okClicked = mainApp.showRouterNewDialog(tempRouter);
		if (okClicked) {
			try {

				RouterModelServiceImplementation rm = new RouterModelServiceImplementation();
				rm.save(tempRouter);
				routerTable.setItems(FXCollections.observableList(rm.getAll()));
			} catch (Exception ex) {
				logger.error("User trying to save a new router without selecting filling the fields");
				Alert alert = new Alert(AlertType.WARNING);
				alert.initOwner(mainApp.getPrimaryStage());
				alert.setTitle("Invalid Arguments");
				alert.setHeaderText("There are empty fields");
				alert.setContentText("Please fill all of the fields!");

				alert.showAndWait();
			}
		}
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected router.
	 */
	@FXML
	private void handleEditRouter() {
		RouterModelServiceImplementation rm = new RouterModelServiceImplementation();

		RouterModel selectedRouter = routerTable.getSelectionModel().getSelectedItem();
		try {
			boolean okClicked = mainApp.showRouterEditDialog(selectedRouter);
			if (okClicked) {
				rm.update(selectedRouter);
			}

		} catch (NullPointerException ex) {
			logger.error("User trying to edit router without selecting it");
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No router record Selected");
			alert.setContentText("Please select a router result from the table.");
			ex.printStackTrace();
			alert.showAndWait();

		} catch (IllegalArgumentException e) {
			logger.error("User trying to edit router with invalid input(s)");
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Invalid input");
			alert.setHeaderText("One of the necessary fields are empty");
			alert.setContentText("Please fill all of the following fields:Name, Manangement IP,Platoform");

			alert.showAndWait();
		} finally {
			routerTable.setItems(FXCollections.observableList(rm.getAll()));

		}
	}

	/**
	 * Calls when the user want to search for a specific router type.
	 */
	@FXML
	private void handleSearchRouter() {
		boolean okClicked = mainApp.showRouterSearchDialog();
		if (okClicked) {
			RouterModelDAO rm = new RouterModelDAO();
			routerTable.setItems(FXCollections.observableList(rm.findAll()));
		}

	}

	/**
	 * Callse when the user wants to check the interfaces of the router.
	 */
	@FXML
	private void handleShowInterfaces() {
		RouterModel selectedRouter = routerTable.getSelectionModel().getSelectedItem();
		try {
			mainApp.showInterfacesDialog(selectedRouter);
			RouterModelDAO rm = new RouterModelDAO();
			routerTable.setItems(FXCollections.observableList(rm.findAll()));

		} catch (Exception ex) {
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