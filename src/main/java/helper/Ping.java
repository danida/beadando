package helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import application.beadando3.view.PingView;
import javafx.scene.control.TextField;

/**
 * @author danida
 *
 */
public class Ping implements Runnable {

	private String OSname = System.getProperty("os.name").toLowerCase();

	private String o = "";
	private String destinationIP;
	private PingView pingView;

	/**
	 * Getter of the PingView.
	 * @return Returns the current instance of the PingView
	 */
	public PingView getPingView() {
		return pingView;
	}

	/**
	 * Setter of the PingView.
	 * @param pingView - Sets the pingview in this instance
	 */
	public void setPingView(PingView pingView) {
		this.pingView = pingView;
	}


	/**
	 * Setter of the destinationIP.
	 * @param destinationIP - Destination that the user wants to test
	 */
	public void setDestinationIP(String destinationIP) {
		this.destinationIP = destinationIP;
	}


	/**
	 * Non-default constructor.
	 * @param destinationIP - destination that the user want to check
	 * @param pingview - Reference to the pingview where the request come from
	 */
	public Ping(String destinationIP, PingView pingview) {
		super();
		this.destinationIP = destinationIP;
		this.pingView = pingview;
	}

	/**
	 * Default constructor.
	 */
	public Ping() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {

		if (OSname.contains("windows")) {
			String command = "ping -n 10 " + destinationIP;
			try {
				Runtime rt = Runtime.getRuntime();

				Process p = rt.exec(command);

				String line;

				BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
				while ((line = r.readLine()) != null) {
					o += line + "\n";
					this.pingView.setOutput(o);
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
			String command = "ping " +  "destinationIP " +"-c 10 " ;
			try {
				Runtime rt = Runtime.getRuntime();

				Process p = rt.exec(command);

				String line;

				BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
				while ((line = r.readLine()) != null) {
					o += line + "\n";
					this.pingView.setOutput(o);
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
