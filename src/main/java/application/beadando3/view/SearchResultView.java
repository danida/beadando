package application.beadando3.view;

import application.beadando3.DAO.RouterModelDAO;
import application.beadando3.model.RouterModel;
import application.beadando3.services.implementations.RouterModelServiceImplementation;
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
public class SearchResultView {

	@FXML
	private TextField name;
	@FXML
	private TextField IOS;
	@FXML
	private TextField platform;
	/**
	 * List of the routers which match for the searching pattern.
	 */
	@FXML
    public  TableView<RouterModel> routerTable;
    @FXML
    private TableColumn<RouterModel, String> routername;
    @FXML
    private TableColumn<RouterModel, String> man_ip;
	
    private Stage dialogStage;
	
    private boolean searchClicked = false;
	private RouterModelDAO rmdao= new RouterModelDAO();

	
	/**
	 * Getter of the searchCicked.
	 * @return Returns true if the user clicked on search
	 */
	public boolean isSearchClicked() {
		return searchClicked;
	}


	/**
	 * Setter of the searchClicked.
	 * @param searchClicked - It is setted to true when the user clicks on search
	 */
	public void setSearchClicked(boolean searchClicked) {
		this.searchClicked = searchClicked;
	}


	/**
	 * Getter of the dialogstage.
	 * @return Returns the dialogstage
	 */
	public Stage getDialogStage() {
		return dialogStage;
	}


	/**
	 * Setter of the dialogstage.
	 * @param dialogStage - dialogstage from the main
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}


	/**
	 * Calls when the customer wants to search for a specific router.
	 */
	@FXML
    private void handleSearch() {
      	RouterModelServiceImplementation rm = new RouterModelServiceImplementation(rmdao);

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
