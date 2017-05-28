package application.beadando3.model;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author danida
 *
 */
@javax.persistence.Entity
@Table(name="traceroute")
@NamedQueries({
	@NamedQuery (name  = "TracerouteModel.getbyId", query="select t from TracerouteModel t where id = :id" ),
	@NamedQuery (name  = "TracerouteModel.findAll", query="select t from TracerouteModel t" ),
	@NamedQuery (name  ="TracerouteModel.count", query="select count(t.id) from TracerouteModel t" )
})
public class TracerouteModel {
	@javax.persistence.Id
	@SequenceGenerator(name="traceroute_seq_gen", sequenceName="traceroute_SEQ")

	@GeneratedValue
	(strategy=GenerationType.AUTO, generator="traceroute_seq_gen") 

	
	
	private Integer id;
	private LocalDateTime execution_date;
	private String output;
	private String destination;
	
	
	
	public TracerouteModel(LocalDateTime execution_date, String output, String destination) {
		super();
		this.execution_date = execution_date;
		this.output = output;
		this.destination = destination;
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
	public LocalDateTime getExecution_date() {
		return execution_date;
	}


	public void setExecution_date(LocalDateTime execution_date) {
		
			this.execution_date = execution_date;

			} 
		

	@Column
	@Basic
	public String getOutput() {
		return output;
	}


	public void setOutput(String output) {
		this.output = output;
	}
	@Column
	@Basic

	public String getDestination() {
		return destination;
	}


	public void setDestination(String destination) {
		this.destination = destination;
	}


	public TracerouteModel() {
	
	}
	public SimpleStringProperty getDestinationProperty(){
		return new SimpleStringProperty(this.destination);
}

	
	public SimpleStringProperty getExecution_DateProperty(){
		return new SimpleStringProperty(execution_date.toString());
}

}
