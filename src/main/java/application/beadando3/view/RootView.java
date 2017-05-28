package application.beadando3.view;

import application.beadando3.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * @author danida
 *
 */
public class RootView {
	@FXML
	TabPane tabpane;
	@FXML
	Tab admintab;
	@FXML
	Tab routertab;
	@FXML
	Tab dashbordtab;
	@FXML 
	Tab pathtesttab;
	
	
	/**
	 * Reference to the main application.
	 */
	private static Main mainApp;
	

	/**
	 * Initialize the RootView.
	 */
	@FXML
	public void initialize(){
		if (Main.getUser().getIsAdmin()==0){
			tabpane.getTabs().remove(admintab);
		}
		
		
	}
	/**Getter of the mainApp.
	 * @return Returns the main.
	 */
	public Main getMainApp() {
		return mainApp;
	}


	/**
	 * Setter of the mainApp.
	 * @param mainApp - Sets the mainApp.
	 */
	public static void setMainApp(Main mainApp) {
		mainApp = mainApp;
	}

}
