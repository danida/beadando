package application.beadando3.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import application.beadando3.model.TracerouteModel;
import application.beadando3.model.TracerouteModelDAO;
import helper.Traceroute;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
	public void initialize(){

		tr = new Traceroute();
		output.setText("");

		
		
	}
	
	
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
		TracerouteModelDAO trd = new TracerouteModelDAO();
		trd.init();
		TracerouteModel trm = new TracerouteModel();
		trm.setId(null);
		trm.setExecution_date("2017-12-01");
		trm.setOutput(output.getText());
		trm.setDestination(destinationIP.getText());
		trd.create(trm);
		trd.closeEm();
		
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setOutput(String o) {
		output.setText(o);
		
	}
}
