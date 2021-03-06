package application.beadando3.view;


import java.awt.List;

import application.beadando3.Main;
import application.beadando3.DAO.LoginModelDAO;
import application.beadando3.DAO.RouterModelDAO;
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

/**
 * @author danida
 *
 */
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
	private LoginModelDAO lmdao= new LoginModelDAO();
	private RouterModelDAO rmdao= new RouterModelDAO();

    /**
     * all of the router platforms.
     */
    private ObservableList<String> platforms = FXCollections.observableArrayList();
	/**
	 * Static variable for the main.
	 */
	public static Main mainApp;

	
	
    /**
     * List all of the platforms.
     * @return Returns the platforms 
     */
    public ObservableList<String> getPlatforms() {
		return platforms;
	}



	/**
	 * Set the platforms.
	 * @param platforms - platform list
	 */
	public void setPlatforms(ObservableList<String> platforms) {
		this.platforms = platforms;
	}



	/**
	 * Returns the platform Axis.
	 * @return Returns the x Axis of the chart
	 */
	public CategoryAxis getxAxis() {
		return xAxis;
	}

	/**Getter of the Main.
	 * @return Returns the Main
	 */
	public static Main getMainApp() {
		return mainApp;
	}

	/**Setter of the Main.
	 * @param mainApp - Mainapp instance
	 */
	public static void setMainApp(Main mainApp) {
		DashboardView.mainApp = mainApp;
	}

	/**
	 * Default constructor.
	 */
	public DashboardView() {
		super();
		}



	/**
	 * Initialize the dashboard controller.
	 */
	@FXML
	public void initialize(){
		RouterModelServiceImplementation dm = new RouterModelServiceImplementation(rmdao);
		LoginModelServicesImplementation lm = new LoginModelServicesImplementation(lmdao);
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
	 /**
	  * Counts the number of the different platforms.
	 * @param platforms - platforms list
	 */
	public void setRouterData(ObservableList<String> platforms) {
	        String[] platformCounter = new String[platforms.size()];
			RouterModelServiceImplementation dm = new RouterModelServiceImplementation(rmdao);
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
	

