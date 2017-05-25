package application.beadando3;



import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import application.beadando3.model.DatabaseConnection;
import application.beadando3.model.LoginModel;
import application.beadando3.model.RouterModel;
import application.beadando3.view.DashboardView;
import application.beadando3.view.InterfacesView;
import application.beadando3.view.LoginView;
import application.beadando3.view.PingView;
import application.beadando3.view.RootView;
import application.beadando3.view.RouterEditDialogView;
import application.beadando3.view.RouterView;
import application.beadando3.view.SearchResultView;
import application.beadando3.view.TracerouteView;
import helper.DatabaseDummyData;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;



	/**
	 * @author danida
	 *
	 */
	public class Main extends Application {
		

	    /**
	     * Set the primary stage
	     */
	    private Stage primaryStage;
	    
	    /**
	     * Set rootPane
	     */
	    private BorderPane rootLayout;
	    /**
	     * Set the loginPane
	     */
	    private AnchorPane loginLayout;
	    /**
	     * One instance of the logger
	     */
	    private final static Logger logger = LoggerFactory.getLogger(Main.class);
	    /**
	     * Login user
	     */
	    private static LoginModel user; 
	    
	    /**
	     * Constructor of the Main class
	     */
	    public Main() {
	    	
		}

		
		
		@Override
	    public void start(Stage primaryStage) {
			logger.info("Starting application");
	        this.primaryStage = primaryStage;
	        this.primaryStage.setTitle("Beadando");
	        DatabaseDummyData.init();
	        if (initLoginLayout()){
	        initRootLayout();}
	    }
		@Override
		public void stop(){
			logger.info("Closing database session");
			DatabaseConnection dc = new DatabaseConnection();
			dc.closeConnection();
		}

	   
	   
	    /**
	     * Initialize rootLayout
	     */
	    public void initRootLayout() {
	        try {
				logger.info("Loading rootview");
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("/view/rootView.fxml"));
	            rootLayout = (BorderPane) loader.load();
	            
	            // Show the scene containing the root layout.
	            Scene scene = new Scene(rootLayout);
	        	RootView controller = loader.getController();
	        	
	            primaryStage.setScene(scene);
	            primaryStage.show();
	        	RouterView.mainApp = this;
	        	DashboardView.mainApp = this;
	        	PingView.mainApp = this;
	        	TracerouteView.mainApp = this;
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * Initialize loginLayout
	     * 
	     * @return Returns if the authentication was successful or not
	     */
	    public Boolean initLoginLayout() {
	    	
	        try {
				logger.info("Loading loginview");
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("/view/login.fxml"));
	            loginLayout = (AnchorPane) loader.load();
	            // Show the scene containing the root layout.
	            Stage dialogStage = new Stage();

	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(primaryStage);
	            Scene scene = new Scene(loginLayout);
	            dialogStage.setScene(scene);
	        	LoginView.setMainApp(this);
	        	LoginView controller = loader.getController();
	            controller.setDialogStage(dialogStage);

	            dialogStage.showAndWait();

	        	
	            return controller.isOkClicked();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

	    /*
	     *Returns the primaryStage 
	     * @return Returns the main stage.
	     */
	    
	    public Stage getPrimaryStage() {
	        return primaryStage;
	    }
	    
	    /**
	     * Pops up when the customer wants to edit a router
	     * @param One instance of RouterModel
	     * @return Returns if the user clicked OK or not
	     */
	    public boolean showRouterEditDialog(RouterModel router) {
	        try {
				logger.info("Showing router edit dialog");

	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("/view/editrouterdialog.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("Add/Edit Router");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(primaryStage);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            RouterEditDialogView controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            controller.setRouter(router);

	            dialogStage.showAndWait();

	            return controller.isOkClicked();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	   
	    /**
	     * Pops up when the customer wants to add a new router
	     * @param One instance of RouterModel
	     * @return Returns if the user clicked OK or not
	     */
	    public boolean showRouterNewDialog(RouterModel router) {
	        try {
				logger.info("Showing new router dialog");

	        	FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("/view/newrouter.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("Add router");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(primaryStage);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            RouterEditDialogView controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            controller.setRouter(router);
	            RouterEditDialogView.setMainApp(this);
	            dialogStage.showAndWait();

	            return controller.isOkClicked();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	
	    /**Pops up when the customer wants to search for a router
	     * @return Returns if the Search button clicked or not
	     */
	    public boolean showRouterSearchDialog() {
	        try {
				logger.info("Showing search router dialog");
	        	
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("/view/searchresult.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("Search Router");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(primaryStage);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            SearchResultView controller = loader.getController();
	            controller.setDialogStage(dialogStage);

	            // Show the dialog and wait until the user closes it
	            dialogStage.showAndWait();

	            return controller.isSearchClicked();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	   
	    /**
	     * Pops up a dialog where the user can change the interfaces for a router
	     * @param One router which is selected from the tableview
	     */
	    public void showInterfacesDialog(RouterModel selectedRouter) {
	    	try{
				logger.info("Showing the interfaces of router dialog");

	    	 FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("/view/interfaces.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("Interfaces of Router");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(primaryStage);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);
	            InterfacesView controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            controller.setRouter(selectedRouter);

	            controller.setData();

	            dialogStage.showAndWait();

	        } catch (IOException e) {
	            e.printStackTrace();
		}
	}


	/**Application start point
	 * @param Application parameters
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**Returns an instance of LoginModel
	 * @return Returns a LoginModel of a user
	 */
	public static LoginModel getUser() {
		return user;
	}

	/**Set the LoginModel of the Main
	 * @param One instance of the LoginModel
	 */
	public static void setUser(LoginModel user) {
		Main.user = user;
	}

	
	

	
}
