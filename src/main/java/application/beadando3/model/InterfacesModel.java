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

@javax.persistence.Entity
@Table(name="Interfaces")

@NamedQueries({
	@NamedQuery(name = "InterfacesModel.findAll", query = "Select i from InterfacesModel i"),
	@NamedQuery(name = "InterfacesModel.count", query = "Select count(i.id) From InterfacesModel i"),
	@NamedQuery(name = "InterfacesModel.getByRouterId", query = "Select i from InterfacesModel i  where i.router_id= :router_id"),
	@NamedQuery(name = "InterfacesModel.getInterfacesbyId", query = "Select i from InterfacesModel i where i.id = :interfaces_id")
})


public class InterfacesModel {
	@javax.persistence.Id
	@GeneratedValue
	(strategy=GenerationType.AUTO, generator="interfaces_seq_gen") 
	@SequenceGenerator(name="interfaces_seq_gen", sequenceName="interfaces_SEQ")
	private Integer id; 
	private Integer router_id;
	private String interface_name;
	private String MAC;
	private String IP;
	
	
	
	
	
	public InterfacesModel(Integer id, Integer router_id, String interface_name, String mAC, String iP) {
		super();
		this.id = id;
		this.router_id = router_id;
		this.interface_name = interface_name;
		MAC = mAC;
		IP = iP;
	}

	public InterfacesModel() {
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
	@OneToMany(mappedBy = "router")
	public Integer getRouter_id() {
		return router_id;
	}

	public void setRouter_id(Integer router_id) {
		this.router_id = router_id;
	}
	@Column
	@Basic
	public String getInterface_name() {
		return interface_name;
	}

	public void setInterface_name(String interface_name) {
		this.interface_name = interface_name;
	}
	@Column
	@Basic
	public String getMAC() {
		return MAC;
	}

	public void setMAC(String mAC) {
		MAC = mAC;
	}
	@Column
	@Basic
	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public SimpleStringProperty getIPProperty() {
		return new SimpleStringProperty(IP);
	}

	public void setIPProperty(SimpleStringProperty iP) {
		IP = iP.get();
	}
	public SimpleStringProperty getMACProperty() {
		return new SimpleStringProperty(MAC);
	}

	public void setMACProperty(SimpleStringProperty mAC) {
		MAC = mAC.get();
	}
	public SimpleStringProperty getInterface_nameProperty() {
		return new SimpleStringProperty(interface_name);
	}

	public void setInterface_nameProperty(SimpleStringProperty interface_name) {
		this.interface_name = interface_name.get();
	}

}
