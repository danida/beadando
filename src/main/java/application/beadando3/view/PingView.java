package application.beadando3.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import application.beadando3.services.implementations.PingModelServiceImplementation;
import application.beadando3.DAO.RouterModelDAO;
import application.beadando3.DAO.TracerouteModelDAO;
import application.beadando3.model.PingModel;
import application.beadando3.model.TracerouteModel;
import helper.Ping;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PingView  {
	@FXML
	private TextField destinationIP;
	@FXML
	private  TextArea output;

	private Stage dialogStage;
	private Ping ping;

	@FXML
	void initialize() {
		ping = new Ping();
		PingModelServiceImplementation pm = new PingModelServiceImplementation();
		output.setText("");
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
		pm.setExecution_date("2017-07-12 23:12:11.1");
		pd.save(pm);
		
	}
	
	
}