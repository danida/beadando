package application.beadando3.view;


import java.awt.List;

import application.beadando3.Main;
import application.beadando3.services.implementations.LoginModelServicesImplementation;
import application.beadando3.services.implementations.RouterModelServiceImplementation;
import application.beadando3.model.RouterModel;
import application.beadando3.services.implementations.RouterModelServiceImplementation;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.fxml.FXML;

public class DashboardView {
	@FXML
	private Label sumOfRouters;
	@FXML
	private Label sumOfUsers;
	@FXML
	private IntegerProperty reachable= new SimpleIntegerProperty(0);
	
	@FXML
	private CategoryAxis xAxis = new CategoryAxis();
	@FXML
    private BarChart<String,Integer> bc;
	
    private ObservableList<String> platforms = FXCollections.observableArrayList();

	
	
    public ObservableList<String> getPlatforms() {
		return platforms;
	}



	public void setPlatforms(ObservableList<String> monthNames) {
		this.platforms = monthNames;
	}



	public CategoryAxis getxAxis() {
		return xAxis;
	}
	public static Main mainApp;



	
	
	public static Main getMainApp() {
		return mainApp;
	}



	public static void setMainApp(Main mainApp) {
		DashboardView.mainApp = mainApp;
	}



	public DashboardView() {
		super();
		}



	@FXML
	public void initialize(){
		RouterModelServiceImplementation dm = new RouterModelServiceImplementation();
		LoginModelServicesImplementation lm = new LoginModelServicesImplementation();
		sumOfRouters.setText(dm.count());
		sumOfUsers.setText(lm.count());
		platforms.addAll(dm.getPlatforms());
		xAxis.setCategories(platforms);
		setRouterData(platforms);
		
	}
	@FXML
	private void handleSearchRouter() {
	            boolean okClicked = mainApp.showRouterSearchDialog();
	            

	      
	        
	}
	 public void setRouterData(ObservableList<String> platforms) {
	        String[] platformCounter = new String[platforms.size()];
			RouterModelServiceImplementation dm = new RouterModelServiceImplementation();
			int i = 0;
	        for (String s:platforms){
	        	platformCounter[i++] =dm.getNumberbyPlatform(s);
	        	
	        }

	        XYChart.Series<String, Integer> series = new XYChart.Series<>();

	        for (i = 0; i < platformCounter.length; i++) {
	            series.getData().add(new XYChart.Data<>(platforms.get(i), new Integer(platformCounter[i])));
	        }

	        bc.getData().add(series);
	    }
    }
	

