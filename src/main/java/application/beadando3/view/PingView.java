package application.beadando3.view;


import application.beadando3.services.implementations.PingModelServiceImplementation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import application.beadando3.Main;
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

public class PingView  {
	@FXML
	private TextField destinationIP;
	@FXML
	private  TextArea output;
	@FXML
	private TextArea dboutput;
	@FXML
    public  TableView<PingModel> pingTable;
    @FXML
    private TableColumn<PingModel, String> destination;
    @FXML
    private TableColumn<PingModel, String> executiondate;


	private Stage dialogStage;
	private Ping ping;
    private final static Logger logger = LoggerFactory.getLogger(PingView.class);
    public static Main mainApp;


	@FXML
	void initialize() {
		ping = new Ping();
		PingModelServiceImplementation pm = new PingModelServiceImplementation();
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

	public TextField getDestinationIP() {
		return destinationIP;
	}

	public void setDestinationIP(TextField destinationIP) {
		this.destinationIP = destinationIP;
	}

	public  TextArea getOutput() {
		return output;
	}

	public  void setOutput(String output) {
		this.output.setText(output);
	}

	public void dothePing() {
		ping.setDestinationIP(destinationIP.getText());
		ping.setPingView(this);
		Thread t = new Thread(ping);
		t.start();
		

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	@FXML
	public void handleSaving(){
		PingModelServiceImplementation pd = new PingModelServiceImplementation();
		PingModel pm = new PingModel();
		pm.setDestination(destinationIP.getText());
		pm.setOutput(output.getText());
		pm.setId(null);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.S");
		LocalDateTime now = LocalDateTime.now();
		pm.setExecution_date(dtf.format(now));
		pd.save(pm);
    	pingTable.setItems(FXCollections.observableList(pd.getAll()));
		
	}
	@FXML
	public void handleDeleting(){
		PingModelServiceImplementation rm = new PingModelServiceImplementation();
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
