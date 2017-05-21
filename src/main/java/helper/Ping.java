package helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import application.beadando3.view.PingView;
import javafx.scene.control.TextField;

public class Ping implements Runnable {

	private String OSname = System.getProperty("os.name").toLowerCase();

	private String o = "";
	private String destinationIP;
	private PingView pingView;

	public PingView getPingView() {
		return pingView;
	}

	public void setPingView(PingView pingView) {
		this.pingView = pingView;
	}

	public String getDestinationIP() {
		return destinationIP;
	}

	public void setDestinationIP(String destinationIP) {
		this.destinationIP = destinationIP;
	}

	public String getO() {
		return o;
	}

	public void setO(String o) {
		this.o = o;
	}

	public Ping(String destinationIP, PingView pingview) {
		super();
		this.destinationIP = destinationIP;
		this.pingView = pingview;
	}

	public Ping() {
		// TODO Auto-generated constructor stub
	}

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
	}

}
