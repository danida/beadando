package application.beadando3.view;


import application.beadando3.services.implementations.PingModelServiceImplementation;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import application.beadando3.Main;
import application.beadando3.DAO.PingModelDAO;
import application.beadando3.model.PingModel;
import helper.Ping;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * @author danida
 *
 */
public class PingView  {
	@FXML
	private TextField destinationIP;
	@FXML
	private  TextArea output;
	@FXML
	private TextArea dboutput;
	/**
	 * Pingtable contains all if the former ping results.
	 */
	@FXML
    public  TableView<PingModel> pingTable;
    @FXML
    private TableColumn<PingModel, String> destination;
    @FXML
    private TableColumn<PingModel, String> executiondate;
    private PingModelDAO pmdao = new PingModelDAO();

	private Ping ping;
    private final static Logger logger = LoggerFactory.getLogger(PingView.class);
    /**
	 * Reference to the main application.
	 */
    public static Main mainApp;


	/**
	 * Initializing the view of the Ping.
	 */
	@FXML
	void initialize() {
		ping = new Ping();
		PingModelServiceImplementation pm = new PingModelServiceImplementation(pmdao);
		pingTable.setItems(FXCollections.observableList(pm.getAll()));
        destination.setCellValueFactory(cellData -> cellData.getValue().getDestinationProperty());
        executiondate.setCellValueFactory(cellData -> cellData.getValue().getExecution_DateProperty());
        pingTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                	if (newValue != null){
                	dboutput.setText(newValue.getOutput());
                	}
                	else {
                	dboutput.setText("");
                	}
        });			
	
	}

	/**
	 * Getter of the destination.
	 * @return Returns the value of the DestionationIP textfield
	 */
	public TextField getDestinationIP() {
		return destinationIP;
	}

	/**
	 * Setter of the destiantionIP.
	 * @param destinationIP - Value of the destinationIP
	 */
	public void setDestinationIP(TextField destinationIP) {
		this.destinationIP = destinationIP;
	}

	/**
	 * Getter of the ping output.
	 * @return  Returns the output of the ping that had been run.
	 */
	public  TextArea getOutput() {
		return output;
	}

	/**
	 * Setter of the output.
	 * @param output - The output of the ping command
	 */
	public  void setOutput(String output) {
		this.output.setText(output);
	}

	/**
	 * Calls the ping helper and create new thread which runs and updates the output.
	 */
	public void dothePing() {
		ping.setDestinationIP(destinationIP.getText());
		ping.setPingView(this);
		Thread t = new Thread(ping);
		t.start();
		

	}

	

	/**
	 * Creates and saves the pingmodel based on the outputs.
	 */
	@FXML
	public void handleSaving(){
		PingModelServiceImplementation pd = new PingModelServiceImplementation(pmdao);
		PingModel pm = new PingModel();
		pm.setDestination(destinationIP.getText());
		pm.setOutput(output.getText());
		LocalDateTime now = LocalDateTime.now();
		pm.setExecution_date(now);
		pm.setId(null);
		pd.save(pm);
    	pingTable.setItems(FXCollections.observableList(pd.getAll()));
		
	}
	/**
	 * Deletes the pingmodel based on the selection.
	 */
	@FXML
	public void handleDeleting(){
		PingModelServiceImplementation rm = new PingModelServiceImplementation(pmdao);
        int selectedIndex = pingTable.getSelectionModel().getSelectedIndex();
        try{
        PingModel pingmodel= pingTable.getItems().get(selectedIndex);
    	rm.delete(pingmodel);
    	pingTable.setItems(FXCollections.observableList(rm.getAll()));
        }
        catch (ArrayIndexOutOfBoundsException e){
        	logger.error("User trying to delete ping without selecting it");
        	Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Ping record Selected");
            alert.setContentText("Please select a ping result from the table.");

            alert.showAndWait();
        }
	}
	
	
}
