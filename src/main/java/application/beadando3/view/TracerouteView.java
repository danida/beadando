package application.beadando3.view;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import application.beadando3.Main;
import application.beadando3.model.TracerouteModel;
import application.beadando3.services.implementations.TracerouteModelServiceImplementation;
import helper.Traceroute;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author danida
 *
 */
public class TracerouteView {
	@FXML
	private TextField destinationIP;
	@FXML
	private TextArea output;


	private Stage dialogStage;
	private Traceroute tr;
	/**
	 * List of the executed traceroutes.
	 */
	@FXML
	public TableView<TracerouteModel> tracerouteTable;
	@FXML
	private TableColumn<TracerouteModel, String> destination;
	@FXML
	private TableColumn<TracerouteModel, String> executiondate;
	@FXML
	private TextArea dboutput;
	/**
	 * Reference to the main application.
	 */
	public static Main mainApp;
	private final static Logger logger = LoggerFactory.getLogger(TracerouteView.class);

	/**
	 * Initalize the tracerouteview.
	 */
	@FXML
	public void initialize() {

		tr = new Traceroute();
		TracerouteModelServiceImplementation pm = new TracerouteModelServiceImplementation();
		tracerouteTable.setItems(FXCollections.observableList(pm.getAll()));
		destination.setCellValueFactory(cellData -> cellData.getValue().getDestinationProperty());
		executiondate.setCellValueFactory(cellData -> cellData.getValue().getExecution_DateProperty());

		tracerouteTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				dboutput.setText(newValue.getOutput());
			} else {
				dboutput.setText("");
			}
		});

	}

	
	/**
	 * Getter of the destinationIP.
	 * @return Returns the value of the destinationIP textfield
	 */
	public TextField getDestinationIP() {
		return destinationIP;
	}

	/**
	 * Setter of the destinationIP.
	 * @param destinationIP - Sets the destinationip to its parameter.
	 */
	public void setDestinationIP(TextField destinationIP) {
		this.destinationIP = destinationIP;
	}

	/**
	 * Getter of the output textfield.
	 * @return Returns the value of the outputfield.
	 */
	public TextArea getOutput() {
		return output;
	}

	/**
	 * Setter of the output textfield.
	 * @param output - The value that has to be set
	 */
	public void setOutput(TextArea output) {
		this.output = output;
	}


	/**
	 * Calls when the user wants to run a traceroute test.
	 */
	@FXML
	public void dotheTraceroute() {
		tr.setDestinationIP(destinationIP.getText());
		tr.setTracerouteView(this);
		Thread t = new Thread(tr);
		t.start();

	}

	/**
	 * Calls when the user wants to save a result to the database.
	 */
	@FXML
	public void handleSaving() {
		TracerouteModelServiceImplementation trd = new TracerouteModelServiceImplementation();
		TracerouteModel trm = new TracerouteModel();
		LocalDateTime now = LocalDateTime.now();
		trm.setExecution_date(now);
		trm.setOutput(output.getText());
		trm.setDestination(destinationIP.getText());
		trd.save(trm);
		tracerouteTable.setItems(FXCollections.observableList(trd.getAll()));

	}

	/**
	 * Calls when the user wants to delete a test from the database.
	 */
	@FXML
	public void handleDeleting() {
		try {
			TracerouteModelServiceImplementation rm = new TracerouteModelServiceImplementation();
			int selectedIndex = tracerouteTable.getSelectionModel().getSelectedIndex();
			TracerouteModel traceroutemodel = tracerouteTable.getItems().get(selectedIndex);
			rm.delete(traceroutemodel);
			tracerouteTable.setItems(FXCollections.observableList(rm.getAll()));
		} catch (ArrayIndexOutOfBoundsException e) {
			logger.error("User trying to delete traceroute without selecting it");
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Traceroute record Selected");
			alert.setContentText("Please select a traceroute result from the table.");

			alert.showAndWait();
		}

	}

	/**
	 * Setter of the dialogstage.
	 * @param dialogStage - dialogstage from the Main.class
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Setter of the Output.
	 * @param o - The value that has to be set
	 */
	public void setOutput(String o) {
		output.setText(o);

	}
}
