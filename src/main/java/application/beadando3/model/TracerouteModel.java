package application.beadando3.model;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Lob;
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

	
	@Column
	@Basic
	private Integer id;
	@Column
	@Basic
	private LocalDateTime execution_date;

	@Column(length = 1000)
	@Basic
	private String output;
	@Column(length=20)
	@Basic
	private String destination;
	
	/**
	 * Default constructor.
	 */
	public TracerouteModel() {
		
	}
	
	/**
	 * Non-default constructor of traceroutemodel.
	 * @param execution_date - Date when the test was executed
	 * @param output - Output of the test
	 * @param destination - Destination of the test
	 */
	public TracerouteModel(LocalDateTime execution_date, String output, String destination) {
		super();
		this.execution_date = execution_date;
		this.output = output;
		this.destination = destination;
	}


	
	
	/**
	 * Getter of the id.
	 * @return Returns the id of the traceroute test
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * Setter of the id.
	 * @param id - id of the traceroute
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	
	/**
	 * Getter of the execution_date.
	 * @return Returns the date when the traceroute was executed
	 */
	public LocalDateTime getExecution_date() {
		return execution_date;
	}


	/**
	 * Setter of the execution_date.
	 * @param execution_date - The time when the traceroute was executed
	 */
	public void setExecution_date(LocalDateTime execution_date) {
		
			this.execution_date = execution_date;

			} 
		

	
	/**
	 * Getter of the output.
	 * @return Returns the output of the test.
	 */
	public String getOutput() {
		return output;
	}


	/**
	 * Setter of the output.
	 * @param output - output of the traceroute
	 */
	public void setOutput(String output) {
		this.output = output;
	}


	/**
	 * Getter of the destination.
	 * @return Returns the destination of the tracerotue test
	 */
	public String getDestination() {
		return destination;
	}


	/**
	 * Setter of the destination.
	 * @param destination - The destination of the traceroutetest.
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}


	
	/**
	 * @return Returns the destination as a simplestringproperty.
	 */
	public SimpleStringProperty getDestinationProperty(){
		return new SimpleStringProperty(this.destination);
}


	/**
	 * @return Returns the execution_date as a simplestringproperty.
	 */
	public SimpleStringProperty getExecution_DateProperty(){
		return new SimpleStringProperty(execution_date.toString());
}

}
