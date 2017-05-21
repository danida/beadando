package application.beadando3.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@javax.persistence.Entity
@Table(name="router")
@NamedQueries({
	@NamedQuery(name = "RouterModel.findAll", query = "SELECT r FROM RouterModel r"),
	@NamedQuery(name = "RouterModel.count", query = "SELECT count(r.id) FROM RouterModel r"),
	@NamedQuery(name = "RouterModel.platforms", query = "Select distinct (r.Platform) FROM RouterModel r"),
	@NamedQuery(name = "RouterModel.routersnumberByPlatform", query = "select count(r.id) from RouterModel r where r.Platform = :platform"),
	@NamedQuery(name = "RouterModel.getRouterModelbyId", query = "SELECT r FROM RouterModel r where r.id = :id"),
	@NamedQuery(name = "RouterModel.getRouterbyName",query = "Select r from RouterModel r where r.Name = :name"),
	@NamedQuery(name = "RouterModel.getRouterbyIOS",query = "Select r from RouterModel r where r.IOS = :IOS"),
	@NamedQuery(name = "RouterModel.getRouterByPlatform", query = "Select r from RouterModel r where r.Platform = :platform")
})
	
	

public class RouterModel {
	@javax.persistence.Id
	@GeneratedValue
	(strategy=GenerationType.AUTO, generator="router_seq_gen") 
	@SequenceGenerator(name="router_seq_gen", sequenceName="router_SEQ")
	
	private Integer id;
	private String  Name;
	private String  Platform;
	private String  Serial_number;
	private LocalDateTime  When_configured;
	private String  Who_configured;
	private String  Config_register;
	private String  IOS;
	private String  man_IP;
	private String  Bootstrap_version;
	
	public RouterModel() {

	}
	
	



	public RouterModel(Integer id, String name, String platform, String serial_number, LocalDateTime when,
			String who_configured, String config_register, String iOS, String man_IP, String bootstrap_version) {
		this.id = id;
		Name = name;
		Platform = platform;
		Serial_number = serial_number;
	   
		
			When_configured = when;

			
		
		Who_configured = who_configured;
		Config_register = config_register;
		IOS = iOS;
		this.man_IP = man_IP;
		Bootstrap_version = bootstrap_version;
	}


@Override
	public String toString() {
		return "RouterModel [id=" + id + ", Name=" + Name + ", Platform=" + Platform + ", Serial_number="
				+ Serial_number + ", When_configured=" + When_configured + ", Who_configured=" + Who_configured
				+ ", Config_register=" + Config_register + ", IOS=" + IOS + ", man_IP=" + man_IP
				+ ", Bootstrap_version=" + Bootstrap_version + "]";
	}



@Column
@Basic
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	@Column
	@Basic


	public String getName() {
		return Name;
	}


	public void setName(String routerName) {
		this.Name = routerName;
	}


	@Column
	@Basic


	public String getPlatform() {
		return Platform;
	}




	public void setPlatform(String routerPlatform) {
		this.Platform = routerPlatform;
	}


	@Column
	@Basic

	public String getSerial_number() {
		return Serial_number;
	}




	public void setSerial_number(String routerSerial) {
		this.Serial_number = routerSerial;
	}
	@Column
	@Basic



	public LocalDateTime getWhen_configured() {
		return When_configured;
	}





	public void setWhen_configured(LocalDateTime configuredBy) {
		this.When_configured = configuredBy;
	}




	@Column
	@Basic


	public String getWho_Configured() {
		return Who_configured;
	}


	public void setConfigured(String configured) {
		this.Who_configured = configured;
	}


	@Column
	@Basic

	public String getConfig_register() {
		return Config_register;
	}

	public void setConfReg(String confReg) {
		this.Config_register = confReg;
	}

	@Column
	@Basic



	public String getIOS() {
		return IOS;
	}



	public void setIOS(String iOS) {
		IOS = iOS;
	}

	@Column
	@Basic

	public String getMan_IP() {
		return man_IP;
	}


	public void setMan_IP(String man_IP) {
		this.man_IP = man_IP;
	}


	@Column(name="BOOTSTRAP_VERSION")
	@Basic

	public String getBootstrap_version() {
		return Bootstrap_version;
	}

	public void setBootstrap(String bootstrap) {
		this.Bootstrap_version = bootstrap;
	}
	
	public StringProperty getRouterNameProperty(){
		return new SimpleStringProperty(this.Name);
		
		
		
	}

	public StringProperty getMan_IPProperty() {
		return new SimpleStringProperty(man_IP);
	}

	
}
