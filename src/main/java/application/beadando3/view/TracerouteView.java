package application.beadando3.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import application.beadando3.Main;
import application.beadando3.DAO.TracerouteModelDAO;
import application.beadando3.model.PingModel;
import application.beadando3.model.TracerouteModel;
import application.beadando3.services.implementations.PingModelServiceImplementation;
import application.beadando3.services.implementations.TracerouteModelServiceImplementation;
import helper.Traceroute;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class TracerouteView {
	@FXML
	private TextField destinationIP;
	@FXML
	private TextArea output;

	private String OSname = System.getProperty("os.name").toLowerCase();

	private Stage dialogStage;
	private Traceroute tr;
	@FXML
    public  TableView<TracerouteModel> tracerouteTable;
    @FXML
    private TableColumn<TracerouteModel, String> destination;
    @FXML
    private TableColumn<TracerouteModel, String> executiondate;
    @FXML
	private TextArea dboutput;

	@FXML
	public void initialize(){

		tr = new Traceroute();
		TracerouteModelServiceImplementation pm = new TracerouteModelServiceImplementation();
		tracerouteTable.setItems(FXCollections.observableList(pm.getAll()));
        destination.setCellValueFactory(cellData -> cellData.getValue().getDestinationProperty());
        executiondate.setCellValueFactory(cellData -> cellData.getValue().getExecution_DateProperty());

        tracerouteTable.getSelectionModel().selectedItemProperty().addListener(
        		(observable, oldValue, newValue) -> {
                	if (newValue != null){
                	dboutput.setText(newValue.getOutput());
                	}
                	else {
                	dboutput.setText("");
                	}
        });				
		
		
	}
	
    public static Main mainApp;
    private final static Logger logger = LoggerFactory.getLogger(TracerouteView.class);


	public TextField getDestinationIP() {
		return destinationIP;
	}


	public void setDestinationIP(TextField destinationIP) {
		this.destinationIP = destinationIP;
	}


	public TextArea getOutput() {
		return output;
	}


	public void setOutput(TextArea output) {
		this.output = output;
	}


	public Traceroute getTr() {
		return tr;
	}


	public void setTr(Traceroute tr) {
		this.tr = tr;
	}


	@FXML
	public void dotheTraceroute() {
		tr.setDestinationIP(destinationIP.getText());
		tr.setTracerouteView(this);
		Thread t = new Thread(tr);
		t.start();
		
	}
	@FXML
	public void handleSaving(){
		TracerouteModelServiceImplementation trd = new TracerouteModelServiceImplementation();
		TracerouteModel trm = new TracerouteModel();
		trm.setExecution_date("2017-12-01 23:12:11.0");
		trm.setOutput(output.getText());
		trm.setDestination(destinationIP.getText());
		trd.save(trm);
    	tracerouteTable.setItems(FXCollections.observableList(trd.getAll()));

	}
	
	@FXML
	public void handleDeleting(){
		try{
		TracerouteModelServiceImplementation rm = new TracerouteModelServiceImplementation();
        int selectedIndex = tracerouteTable.getSelectionModel().getSelectedIndex();
       TracerouteModel traceroutemodel= tracerouteTable.getItems().get(selectedIndex);
    	rm.delete(traceroutemodel);
    	tracerouteTable.setItems(FXCollections.observableList(rm.getAll()));
    	}
    	 catch (ArrayIndexOutOfBoundsException e){
         	logger.error("User trying to delete traceroute without selecting it");
         	Alert alert = new Alert(AlertType.WARNING);
             alert.initOwner(mainApp.getPrimaryStage());
             alert.setTitle("No Selection");
             alert.setHeaderText("No Traceroute record Selected");
             alert.setContentText("Please select a traceroute result from the table.");

             alert.showAndWait();
         }

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setOutput(String o) {
		output.setText(o);
		
	}
}
