package application.beadando3.view;

import application.beadando3.model.RouterModel;
import application.beadando3.services.implementations.RouterModelServiceImplementation;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class searchResultView {

	@FXML
	private TextField name;
	@FXML
	private TextField IOS;
	@FXML
	private TextField platform;
	@FXML
    public  TableView<RouterModel> routerTable;
    @FXML
    private TableColumn<RouterModel, String> routername;
    @FXML
    private TableColumn<RouterModel, String> man_ip;
	
    private Stage dialogStage;
	
    private boolean searchClicked = false;
	
	
	public boolean isSearchClicked() {
		return searchClicked;
	}


	public void setSearchClicked(boolean searchClicked) {
		this.searchClicked = searchClicked;
	}


	public Stage getDialogStage() {
		return dialogStage;
	}


	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}


	@FXML
    private void handleSearch() {
      	RouterModelServiceImplementation rm = new RouterModelServiceImplementation();

      if (!name.getText().equals("")) {
          	 routerTable.setItems(FXCollections.observableList(rm.getRouterModelbyName(name.getText())));
             routername.setCellValueFactory(cellData -> cellData.getValue().getRouterNameProperty());
             man_ip.setCellValueFactory(cellData -> cellData.getValue().getMan_IPProperty());
      }	
      if (!IOS.getText().equals("")){
       	 routerTable.setItems(FXCollections.observableList(rm.getRouterModelbyIOS(IOS.getText())));
          routername.setCellValueFactory(cellData -> cellData.getValue().getRouterNameProperty());
          man_ip.setCellValueFactory(cellData -> cellData.getValue().getMan_IPProperty());
          }
      if (!platform.getText().equals("")){
       	 routerTable.setItems(FXCollections.observableList(rm.getRouterModelbyPlatform(platform.getText())));
          routername.setCellValueFactory(cellData -> cellData.getValue().getRouterNameProperty());
          man_ip.setCellValueFactory(cellData -> cellData.getValue().getMan_IPProperty());
    	  
      }

    	 
      }
}
