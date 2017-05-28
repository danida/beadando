package helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import application.beadando3.view.PingView;
import application.beadando3.view.TracerouteView;

/**
 * @author danida
 *
 */
public class Traceroute implements Runnable {

	private String OSname = System.getProperty("os.name").toLowerCase();
	private TracerouteView tracerouteview;
	private String destinationIP;
	private String o = "";

	
	/**
	 * Getter of the Tracerouteview.
	 * @return Returns the current instance of the Tracerouteview
	 */
	public TracerouteView getTracerouteview() {
		return tracerouteview;
	}

	/**
	 * Setter of the TracerouteView.
	 * @param tracerouteview - Sets the TracerouteView in this instance
	 */
	public void setTracerouteview(TracerouteView tracerouteview) {
		this.tracerouteview = tracerouteview;
	}

	
	
	/**
	 * Setter of the destinationIP.
	 * @param destinationIP - Destination that the user wants to test
	 */
	public void setDestinationIP(String destinationIP) {
		this.destinationIP = destinationIP;
	}

	
	/**
	 * Getter of the TracerouteView.
	 * @return Returns the current instance of the TracerouteView
	 */
	
	public TracerouteView getTracerouteView() {
		return tracerouteview;
	}

	/**
	 * Setter of the TracerouteView.
	 * @param tracerouterview - Sets the TracerouteView in this instance
	 */
	public void setTracerouteView(TracerouteView tracerouterview) {
		this.tracerouteview = tracerouterview;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		String ret = "";
		if (OSname.contains("windows")) {
			String command = "tracert -d " + destinationIP;
			try {
				Process p = Runtime.getRuntime().exec(command);
				p.getOutputStream();
				BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				while ((line = r.readLine()) != null) {
						o += line + "\n";
						this.tracerouteview.setOutput(o);
						Thread.sleep(1000);


				}
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (OSname.indexOf("nix") >= 0 || OSname.indexOf("nux") >= 0 || OSname.indexOf("aix") > 0 ){
			String command = "traceroute " + destinationIP;

			try {
				Process p = Runtime.getRuntime().exec(command);
				p.getOutputStream();
				BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				while ((line = r.readLine()) != null) {
						o += line + "\n";
						this.tracerouteview.setOutput(o);
						Thread.sleep(1000);


				}
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
