package application.beadando3.view;

import application.beadando3.DAO.InterfacesModelDAO;
import application.beadando3.model.InterfacesModel;
import application.beadando3.model.RouterModel;
import application.beadando3.services.implementations.InterfacesModelServiceImplementation;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author danida
 *
 */
public class InterfacesView {
	/**
	 * List of the interface which are in the database, and belong to the selected router.
	 */
	@FXML
	public TableView<InterfacesModel> InterfacesTable;
	@FXML
	private TableColumn<InterfacesModel, String> name;
	@FXML
	private TableColumn<InterfacesModel, String> ip_address;
	@FXML
	private TableColumn<InterfacesModel, String> mac;

	@FXML
	private StringProperty interface_name;
	@FXML
	private StringProperty MAC;
	@FXML
	private StringProperty IP;
	@FXML
	private TextField namef = new TextField();
	@FXML
	private TextField ipfield = new TextField();
	@FXML
	private TextField macfield = new TextField();
	boolean newinterface;
	
	private InterfacesModelDAO imdao = new InterfacesModelDAO();
	private Stage dialogStage;
	private RouterModel router;

	/**
	 * Initializing the interfaceView.
	 */
	@FXML
	private void initialize() {
		namef.setVisible(false);
		ipfield.setVisible(false);
		macfield.setVisible(false);
	}

	/**
	 * Sets the data in the interfacestable.
	 */
	public void setData() {
		InterfacesModelServiceImplementation rm = new InterfacesModelServiceImplementation(imdao);

		if (rm.getInterfacebyRouterModel(router.getId()) != null) {

			InterfacesTable.setItems(FXCollections.observableList(rm.getInterfacebyRouterModel(router.getId())));

			name.setCellValueFactory(cellData -> cellData.getValue().getInterface_nameProperty());
			mac.setCellValueFactory(cellData -> cellData.getValue().getMACProperty());
			ip_address.setCellValueFactory(cellData -> cellData.getValue().getIPProperty());
		}
	}

	/**
	 * Disable all of the input fields.
	 */
	@FXML
	public void disableFields() {
		namef.setText("");
		ipfield.setText("");
		macfield.setText("");
		namef.setVisible(false);
		ipfield.setVisible(false);
		macfield.setVisible(false);
	}
	/**
	 * Enable all of the input fields.
	 */
	@FXML
	public void enableFields() {
		namef.setVisible(true);
		ipfield.setVisible(true);
		macfield.setVisible(true);
	}

	/**
	 * Decide if the user add/edit an interface and make modification in the database based on his selection.
	 */
	@FXML
	public void handleOK() {
		InterfacesModelServiceImplementation rm = new InterfacesModelServiceImplementation(imdao);
		if (this.newinterface == true) {
			InterfacesModel im = new InterfacesModel(null, router.getId(), namef.getText(), macfield.getText(),
					ipfield.getText());
			rm.save(im);
			this.newinterface = false;
		} else {
			InterfacesModel e = InterfacesTable.getSelectionModel().getSelectedItem();
			e.setInterface_name(namef.getText());
			e.setIP(ipfield.getText());
			e.setMAC(macfield.getText());
			rm.update(e);
		}
		disableFields();
		setData();

	}

	/**
	 * Cancels the started modification.
	 */
	@FXML
	public void handleCancel() {
		disableFields();
		newinterface = false;
	}

	/**
	 * Deletes the selected interface from the database.
	 */
	@FXML
	public void deleteInterface() {
		InterfacesModelServiceImplementation rm = new InterfacesModelServiceImplementation(imdao);
		rm.delete(InterfacesTable.getSelectionModel().getSelectedItem());
		setData();

	}

	/**
	 * This method runs firstly, when the user tries to add a new interface.
	 */
	@FXML
	public void addNewInterface() {

		newinterface = true;
		enableFields();
	}

	/**
	 * This method runs firstly, when the user tries to add a edit interface.
	 */
	@FXML
	public void editInterface() {
		InterfacesModel e = InterfacesTable.getSelectionModel().getSelectedItem();
		this.newinterface = false;
		enableFields();
		namef.setText(e.getInterface_name());
		ipfield.setText(e.getIP());
		macfield.setText(e.getMAC());
	}

	/**
	 * Getter of the router.
	 * @return Returns the routermodel, where the interfaces are belonged.
	 */
	public RouterModel getRouter() {
		return router;
	}

	/**
	 * Setter of the router.
	 * @param router - Sets which routers interfaces should be showed.
	 */
	public void setRouter(RouterModel router) {
		this.router = router;
	}

	

	/**
	 * Getter of the dialogstage.
	 * @return Returns the dialogstage.
	 */
	public Stage getDialogStage() {
		return dialogStage;
	}

	/**
	 * Getter of the newinterface.
	 * @return Returns true if the user tries to add new interface to the router.
	 */
	public boolean isNewinterface() {
		return newinterface;
	}

	/**
	 * Setter of the new interface.
	 * @param newinterface - Sets if the user want to add a new interface.
	 */
	public void setNewinterface(boolean newinterface) {
		this.newinterface = newinterface;
	}

	/**
	 * Setter of the dialogstage.
	 * @param dialogStage - Dialogstage of the main.
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Getter of the Name column.
	 * @return Returns the name from the Name column
	 */
	public TableColumn<InterfacesModel, String> getName() {
		return name;
	}

	/**
	 * Setter of the name column.
	 * @param name - Name of the interface
	 */
	public void setName(TableColumn<InterfacesModel, String> name) {
		this.name = name;
	}

	/**
	 * Getter of the namefield.
	 * @return - Returns the value of the namefield
	 */
	public TextField getNamefield() {
		return namef;
	}

	/**
	 * Setter of the namefield.
	 * @param namefield - It has to be a name of the interface
	 */
	public void setNamefield(TextField namefield) {
		this.namef = namefield;
	}

	/**
	 * Getter of the Macfield.
	 * @return Returns the MAC address of an interface.
	 */
	public TextField getMacfield() {
		return macfield;
	}

	/**
	 * Setter of the macfield.
	 * @param macfield - Sets the value of the MAC
	 */
	public void setMacfield(TextField macfield) {
		this.macfield = macfield;
	}

}
