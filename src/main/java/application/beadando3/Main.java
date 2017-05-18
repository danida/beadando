package application.beadando3;


import java.io.IOException;

import application.beadando3.model.DatabaseConnection;
import application.beadando3.model.RouterModel;
import application.beadando3.view.DashboardView;
import application.beadando3.view.InterfacesView;
import application.beadando3.view.RouterEditDialogView;
import application.beadando3.view.RouterView;
import application.beadando3.view.searchResultView;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


	public class Main extends Application {

	    private Stage primaryStage;
	    private BorderPane rootLayout;
	    private ObservableList<RouterModel> routerData = FXCollections.observableArrayList();

	    public Main() {
	    	
		}

		@Override
	    public void start(Stage primaryStage) {
	        this.primaryStage = primaryStage;
	        this.primaryStage.setTitle("Beadando");
	        initRootLayout();
	    }
		@Override
		public void stop(){
			DatabaseConnection dc = new DatabaseConnection();
			dc.closeConnection();
			
		}

	    /**
	     * Initializes the root layout.
	     */
	    public void initRootLayout() {
	        try {
	            // Load root layout from fxml file.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("/view/rootView.fxml"));
	            rootLayout = (BorderPane) loader.load();
	            // Show the scene containing the root layout.
	            Scene scene = new Scene(rootLayout);
	            primaryStage.setScene(scene);
	            primaryStage.show();
	        	RouterView.mainApp = this;
	        	DashboardView.mainApp = this;

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }



	    /**
	     * Returns the main stage.
	     * @return
	     */
	    public Stage getPrimaryStage() {
	        return primaryStage;
	    }
	    
	    public boolean showRouterEditDialog(RouterModel router) {
	        try {
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
	    public boolean showRouterNewDialog(RouterModel router) {
	        try {
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
	            dialogStage.showAndWait();

	            return controller.isOkClicked();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	
	    public boolean showRouterSearchDialog() {
	        try {
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

	            searchResultView controller = loader.getController();
	            controller.setDialogStage(dialogStage);

	            // Show the dialog and wait until the user closes it
	            dialogStage.showAndWait();

	            return controller.isSearchClicked();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    public boolean showInterfacesSearchDialog(RouterModel selectedRouter) {
	    	try{
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

	            return controller.isOkClicked();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
		}
	}
	public ObservableList<RouterModel> getRouterData() {
			return routerData;
		}

		public void setRouterData(ObservableList<RouterModel> routerData) {
			this.routerData = routerData;
		}

	public static void main(String[] args) {
		launch(args);
	}

	
}
