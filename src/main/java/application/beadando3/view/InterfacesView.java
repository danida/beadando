package application.beadando3.view;

import application.beadando3.model.InterfacesModel;
import application.beadando3.model.InterfacesModelDAO;
import application.beadando3.model.RouterModel;
import application.beadando3.model.RouterModelDAO;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
	private IntegerProperty id;
	private IntegerProperty router_id;

	@FXML
	private StringProperty interface_name;
	@FXML
	private StringProperty MAC;
	@FXML
	private StringProperty IP;

	   private Stage dialogStage;
	    private RouterModel router;
	    private boolean okClicked = false;
	
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

	@FXML
	private void initialize() {
		InterfacesModelDAO rm = new InterfacesModelDAO();
		rm.init();
		InterfacesTable.setItems(FXCollections.observableList(rm.getInterfacesByRouterId(router.getId())));
		name.setCellValueFactory(cellData -> cellData.getValue().getInterface_nameProperty());
		mac.setCellValueFactory(cellData -> cellData.getValue().getMACProperty());
		ip_address.setCellValueFactory(cellData -> cellData.getValue().getIPProperty());
		rm.closeEm();
	}

	public void setDialogStage(Stage dialogStage) {
this.dialogStage = dialogStage;		
	}

}
