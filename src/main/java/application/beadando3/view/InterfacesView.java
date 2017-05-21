package application.beadando3.view;

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

public class InterfacesView {
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
	private  TextField namef = new TextField();
	@FXML
	private TextField ipfield= new TextField();
	@FXML
	private TextField macfield= new TextField();
	boolean newinterface;

	private Stage dialogStage;
	private RouterModel router;
	private boolean okClicked = false;

	@FXML
	private void initialize() {
		namef.setVisible(false);
		ipfield.setVisible(false);
		macfield.setVisible(false);
	}

	public void setData() {
		InterfacesModelServiceImplementation rm = new InterfacesModelServiceImplementation();

		if (rm.getInterfacebyRouterModel(router.getId()) != null) {

			InterfacesTable.setItems(FXCollections.observableList(rm.getInterfacebyRouterModel(router.getId())));

			name.setCellValueFactory(cellData -> cellData.getValue().getInterface_nameProperty());

			mac.setCellValueFactory(cellData -> cellData.getValue().getMACProperty());
			ip_address.setCellValueFactory(cellData -> cellData.getValue().getIPProperty());
		}
	}

	@FXML
	public void disableFields() {
		namef.setText("");
		ipfield.setText("");
		macfield.setText("");
		namef.setVisible(false);
		ipfield.setVisible(false);
		macfield.setVisible(false);
	}

	@FXML
	public void enableFields() {
		namef.setVisible(true);
		ipfield.setVisible(true);
		macfield.setVisible(true);
	}

	@FXML
	public void handleOK() {
		InterfacesModelServiceImplementation rm = new InterfacesModelServiceImplementation();
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

	@FXML
	public void handleCancel() {
		disableFields();
		newinterface = false;
	}

	@FXML
	public void deleteInterface() {
		InterfacesModelServiceImplementation rm = new InterfacesModelServiceImplementation();
		rm.delete(InterfacesTable.getSelectionModel().getSelectedItem());
		setData();

	}

	@FXML
	public void addNewInterface() {
		
		newinterface = true;
		enableFields();
	}
	@FXML
	public void editInterface() {
		InterfacesModel e = InterfacesTable.getSelectionModel().getSelectedItem();
		this.newinterface = false;
		enableFields();
		namef.setText(e.getInterface_name());
		ipfield.setText(e.getIP());
		macfield.setText(e.getMAC());
	}

	public RouterModel getRouter() {
		return router;
	}

	public void setRouter(RouterModel router) {
		this.router = router;
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	public void setOkClicked(boolean okClicked) {
		this.okClicked = okClicked;
	}

	public Stage getDialogStage() {
		return dialogStage;
	}

	public boolean isNewinterface() {
		return newinterface;
	}

	public void setNewinterface(boolean newinterface) {
		this.newinterface = newinterface;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public TableColumn<InterfacesModel, String> getName() {
		return name;
	}

	public void setName(TableColumn<InterfacesModel, String> name) {
		this.name = name;
	}

	public TextField getNamefield() {
		return namef;
	}

	public void setNamefield(TextField namefield) {
		this.namef = namefield;
	}

	public TextField getMacfield() {
		return macfield;
	}

	public void setMacfield(TextField macfield) {
		this.macfield = macfield;
	}

}
