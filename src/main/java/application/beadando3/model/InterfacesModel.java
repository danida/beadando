package application.beadando3.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author danida
 *
 */
@javax.persistence.Entity
@Table(name = "Interfaces")

@NamedQueries({ @NamedQuery(name = "InterfacesModel.findAll", query = "Select i from InterfacesModel i"),
		@NamedQuery(name = "InterfacesModel.count", query = "Select count(i.id) From InterfacesModel i"),
		@NamedQuery(name = "InterfacesModel.getByRouterId", query = "Select i from InterfacesModel i  where i.router_id= :router_id"),
		@NamedQuery(name = "InterfacesModel.getInterfacesbyId", query = "Select i from InterfacesModel i where i.id = :interfaces_id") })

public class InterfacesModel {
	@javax.persistence.Id
	@SequenceGenerator(name = "interfaces_seq_gen", sequenceName = "interfaces_SEQ")

	@GeneratedValue(strategy = GenerationType.AUTO, generator = "interfaces_seq_gen")
	@Column
	@Basic
	private Integer id;
	@Column
	@Basic
	@OneToMany(mappedBy = "router")
	private Integer router_id;
	@Column
	@Basic
	private String interface_name;
	@Column
	@Basic
	private String MAC;
	@Column
	@Basic
	private String IP;

	/**
	 * Non-default constructor.
	 * @param id - id of the interface
	 * @param router_id - router_id of the interface
	 * @param interface_name - name of the interface
	 * @param mAC - MAC address of the interface
	 * @param iP - IP of the router
	 */
	public InterfacesModel(Integer id, Integer router_id, String interface_name, String mAC, String iP) {
		super();
		this.id = id;
		this.router_id = router_id;
		this.interface_name = interface_name;
		MAC = mAC;
		IP = iP;
	}

	/**
	 * Default constructor.
	 */
	public InterfacesModel() {
	}

	
	/**
	 * Getter of the id.
	 * @return Returns the id of the interface
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Setter of the id.
	 * @param id - id of the interface.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Getter of the router_id.
	 * @return Returns the router_id of the interface
	 */
	public Integer getRouter_id() {
		return router_id;
	}
	/**
	 * Setter of the router_id.
	 * @param router_id - router_id of the interface.
	 */
	public void setRouter_id(Integer router_id) {
		this.router_id = router_id;
	}
	/**
	 * Getter of the interface_name.
	 * @return Returns the interface_name of the interface
	 */

	public String getInterface_name() {
		return interface_name;
	}
	/**
	 * Setter of the interface_name.
	 * @param interface_name - interface_name of the interface.
	 */
	public void setInterface_name(String interface_name) {
		this.interface_name = interface_name;
	}
	/**
	 * Getter of the MAC.
	 * @return Returns the MAC of the interface
	 */

	public String getMAC() {
		return MAC;
	}
	/**
	 * Setter of the mAC.
	 * @param mAC - mAC of the interface.
	 */
	public void setMAC(String mAC) {
		MAC = mAC;
	}

	/**
	 * Getter of the IP.
	 * @return Returns the IP of the interface
	 */
	public String getIP() {
		return IP;
	}
	/**
	 * Setter of the iP.
	 * @param iP - iP of the interface.
	 */
	public void setIP(String iP) {
		IP = iP;
	}

	/**
	 * Getter of IP property.
	 * @return Returns the IP as a stringproperty
	 */
	public SimpleStringProperty getIPProperty() {
		return new SimpleStringProperty(IP);
	}
	/**
	 * Setter of the iP.
	 * @param iP - iP of the interface.
	 */
	public void setIPProperty(SimpleStringProperty iP) {
		IP = iP.get();
	}
	/**
	 * Getter of MAC property.
	 * @return Returns the MAC as a stringproperty
	 */
	public SimpleStringProperty getMACProperty() {
		return new SimpleStringProperty(MAC);
	}
	/**
	 * Setter of the MAC.
	 * @param mAC - MAC of the interface.
	 */
	public void setMACProperty(SimpleStringProperty mAC) {
		MAC = mAC.get();
	}
	/**
	 * Getter of interface_name property.
	 * @return Returns the interface_name as a stringproperty
	 */
	public SimpleStringProperty getInterface_nameProperty() {
		return new SimpleStringProperty(interface_name);
	}
	/**
	 * Setter of the interface_name.
	 * @param interface_name - interface_name of the interface.
	 */
	public void setInterface_nameProperty(SimpleStringProperty interface_name) {
		this.interface_name = interface_name.get();
	}

}
