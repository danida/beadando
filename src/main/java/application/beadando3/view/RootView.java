package application.beadando3.view;

import application.beadando3.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

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
	
	
	private static Main mainApp;
	

	@FXML
	public void initialize(){
		if (Main.getUser().getIsAdmin()==0){
			tabpane.getTabs().remove(admintab);
		}
		
		
	}
	public Main getMainApp() {
		return mainApp;
	}


	public static void setMainApp(Main mainApp) {
		mainApp = mainApp;
	}

}
