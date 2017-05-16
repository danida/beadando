package helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import application.beadando3.view.PingView;
import application.beadando3.view.TracerouteView;

public class Traceroute implements Runnable{
	
	private String OSname = System.getProperty("os.name").toLowerCase();
	private TracerouteView tracerouteview;
	private String destinationIP;
	private String o = "";

	
	public String getO() {
		return o;
	}

	public void setO(String o) {
		this.o = o;
	}

	public TracerouteView getTracerouteview() {
		return tracerouteview;
	}

	public void setTracerouteview(TracerouteView tracerouteview) {
		this.tracerouteview = tracerouteview;
	}

	public String getDestinationIP() {
		return destinationIP;
	}

	public void setDestinationIP(String destinationIP) {
		this.destinationIP = destinationIP;
	}

	public TracerouteView getTracerouteView() {
		return tracerouteview;
	}

	public void setTracerouteView(TracerouteView tracerouterview) {
		this.tracerouteview = tracerouterview;
	}

	
	
	@Override
	public void run() {
		 String ret= "";
		    if (OSname.contains("windows")){
		        String command = "tracert -d " + destinationIP;
		        try {
		            Process p = Runtime.getRuntime().exec(command);
		            p.getOutputStream();
		            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		        String line;
		        while (true) {
		            line = r.readLine();
		            if (line != null) {  o += line + "\n";	
		            this.tracerouteview.setOutput(o);
		            }
		           
		        }
		        } catch (IOException ex) {
		        	ex.printStackTrace();
		        }
		    }		
	}

}
