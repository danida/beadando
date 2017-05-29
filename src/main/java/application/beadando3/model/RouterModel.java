package application.beadando3.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author danida
 *
 */
@javax.persistence.Entity
@Table(name = "router")
@NamedQueries({ @NamedQuery(name = "RouterModel.findAll", query = "SELECT r FROM RouterModel r"),
		@NamedQuery(name = "RouterModel.count", query = "SELECT count(r.id) FROM RouterModel r"),
		@NamedQuery(name = "RouterModel.platforms", query = "Select distinct (r.Platform) FROM RouterModel r"),
		@NamedQuery(name = "RouterModel.routersnumberByPlatform", query = "select count(r.id) from RouterModel r where r.Platform = :platform"),
		@NamedQuery(name = "RouterModel.getRouterModelbyId", query = "SELECT r FROM RouterModel r where r.id = :id"),
		@NamedQuery(name = "RouterModel.getRouterbyName", query = "Select r from RouterModel r where r.Name = :name"),
		@NamedQuery(name = "RouterModel.getRouterbyIOS", query = "Select r from RouterModel r where r.IOS = :IOS"),
		@NamedQuery(name = "RouterModel.getRouterByPlatform", query = "Select r from RouterModel r where r.Platform = :platform") })

public class RouterModel {
	@javax.persistence.Id
	@SequenceGenerator(name = "router_seq_gen", sequenceName = "router_SEQ")

	@GeneratedValue(strategy = GenerationType.AUTO, generator = "router_seq_gen")
	@Column
	@Basic
	@OneToMany
	@JoinTable(name = "Interfaces")
	private Integer id;
	@Column
	@Basic

	private String Name;
	@Column
	@Basic

	private String Platform;
	@Column
	@Basic

	private String Serial_number;
	@Column
	@Basic

	private LocalDateTime When_configured;
	@Column
	@Basic

	private String Who_configured;
	@Column
	@Basic

	private String Config_register;
	@Column
	@Basic

	private String IOS;
	@Column
	@Basic

	private String man_IP;
	@Column(name = "Features")
	@Basic
	private String features;

	/**
	 * Default constructor.
	 */
	public RouterModel() {

	}

	/**
	 * Non-default constructor.
	 * @param id - id of the router
	 * @param name - name of the router
	 * @param platform - platform of the router
	 * @param serial_number - serial number of the router
	 * @param when - when was the router configured last time
	 * @param who_configured - who configured the router last time
	 * @param config_register - configuration register of the router
	 * @param iOS - IOS of the router
	 * @param man_IP - management ip of the router
	 * @param Features - features of the router
	 */
	public RouterModel(Integer id, String name, String platform, String serial_number, LocalDateTime when,
			String who_configured, String config_register, String iOS, String man_IP, String Features) {
		this.id = id;
		Name = name;
		Platform = platform;
		Serial_number = serial_number;

		When_configured = when;

		Who_configured = who_configured;
		Config_register = config_register;
		IOS = iOS;
		this.man_IP = man_IP;
		this.features = Features;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public String toString() {
		return "RouterModel [id=" + id + ", Name=" + Name + ", Platform=" + Platform + ", Serial_number="
				+ Serial_number + ", When_configured=" + When_configured + ", Who_configured=" + Who_configured
				+ ", Config_register=" + Config_register + ", IOS=" + IOS + ", man_IP=" + man_IP + ", Features="
				+ features + "]";
	}

	/**
	 * Getter of the id of the router.
	 * @return Returns 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Setter of the id of the router.
	 * @param id - id of the router
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Getter of the router name.
	 * @return Returns the name of the router
	 */
	public String getName() {
		return Name;
	}

	/**
	 * Setter of the router name.
	 * @param routerName - Name of the router
	 */
	public void setName(String routerName) {
		this.Name = routerName;
	}

	/**
	 * Getter of the platform.
	 * @return Returns the platform of the router
	 */
	public String getPlatform() {
		return Platform;
	}

	/**
	 * Setter of the routerplatform.
	 * @param routerPlatform - Platform of the router
	 */
	public void setPlatform(String routerPlatform) {
		this.Platform = routerPlatform;
	}

	/**
	 * Getter of the serial_number.
	 * @return Returns the serial number of the router
	 */
	public String getSerial_number() {
		return Serial_number;
	}

	/**
	 * Setter of the serial number.
	 * @param routerSerial - Serial number of the router
	 */
	public void setSerial_number(String routerSerial) {
		this.Serial_number = routerSerial;
	}

	/**
	 * Getter of the when_configured value.
	 * @return Returns the date when the router  was last configured
	 */
	public LocalDateTime getWhen_configured() {
		return When_configured;
	}

	/**
	 * Setter when_configured.
	 * @param configured - The date when the router was last configured
	 */
	public void setWhen_configured(LocalDateTime configured) {
		this.When_configured = configured;
	}

	/**
	 * Getter of who_configured.
	 * @return Returns who configure the router last time.
	 */
	public String getWho_Configured() {
		return Who_configured;
	}

	/**
	 * Setter of who_configured.
	 * @param configured - The person who configured the router last time
	 */
	public void setConfigured(String configured) {
		this.Who_configured = configured;
	}

	/**
	 * Getter of the configuration register.
	 * @return Returns the value of the config register
	 */
	public String getConfig_register() {
		return Config_register;
	}

	/**
	 * Setter of the config register.
	 * @param confReg - The value of the config register
	 */
	public void setConfReg(String confReg) {
		this.Config_register = confReg;
	}

	/**
	 * Getter of the IOS of the router.
	 * @return Returns the IOS of the router
	 */
	public String getIOS() {
		return IOS;
	}

	/**
	 * Setter of the IOS of the router.
	 * @param iOS - IOS of the router
	 */
	public void setIOS(String iOS) {
		IOS = iOS;
	}

	/**
	 * Getter of the management ip.
	 * @return Returns the management ip of the router
	 */
	public String getMan_IP() {
		return man_IP;
	}

	/**
	 * Setter of the management IP.
	 * @param man_IP - Ip of the router management
	 */
	public void setMan_IP(String man_IP) {
		this.man_IP = man_IP;
	}

	/**
	 * Getter of the features.
	 * @return Returns all of th features configured on the router
	 */
	public String getFeatures() {
		return features;
	}

	/**
	 * Setter of the features.
	 * @param Features - Features that we want to confiugre
	 */
	public void setFeatures(String Features) {
		this.features = Features;
	}

	/**
	 * Getter of the name property.
	 * @return Returns the name of the router as a simplestringproperty
	 */
	public StringProperty getRouterNameProperty() {
		return new SimpleStringProperty(this.Name);

	}

	/**
	 * Getter of the management IP.
	 * @return Returns the man_IP of the router as a simplestringproperty
	 */
	public StringProperty getMan_IPProperty() {
		return new SimpleStringProperty(man_IP);
	}

}
