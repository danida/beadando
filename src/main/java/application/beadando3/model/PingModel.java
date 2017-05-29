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
@Table(name = "ping")
@NamedQueries({ @NamedQuery(name = "PingModel.getbyid", query = "select p from PingModel p where id = :id"),
		@NamedQuery(name = "PingModel.findAll", query = "select p from PingModel p"),
		@NamedQuery(name = "PingModel.count", query = "select count(p.id) from PingModel p") })
public class PingModel {
	@javax.persistence.Id
	@SequenceGenerator(name = "ping_seq_gen", sequenceName = "ping_SEQ")

	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ping_seq_gen")
	@Column
	@Basic
	private Integer id;
	@Column
	@Basic
	private LocalDateTime execution_date;
	@Column(length=1000)
	@Basic
	private String output;
	@Column(length=20)
	@Basic
	private String destination;

	
	/**
	 * Getter of the id.
	 * @return Returns the id of the ping
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Setter of the id.
	 * @param id - id of the ping
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * Getter of the execution_date.
	 * @return Returns the execution_date of the ping
	 */

	public LocalDateTime getExecution_date() {
		return execution_date;
	}
	/**
	 * Setter of the execution_date.
	 * @param now - execution_date of the ping
	 */
	public void setExecution_date(LocalDateTime now) {
		this.execution_date = now;
	}


	/**
	 * Getter of the destination.
	 * @return Returns the destionation of the ping.
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * Setter of the destination.
	 * @param destination - destination of the ping
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * Getter of the output.
	 * @return Returns the output of the ping.
	 */
	public String getOutput() {
		return output;
	}
	/**
	 * Setter of the output.
	 * @param output - output of the ping
	 */
	public void setOutput(String output) {
		this.output = output;
	}

	/**
	 * Default constructor.
	 */
	public PingModel() {

	}

	/**
	 * Non-default constructor.
	 * @param id - id of the ping
	 * @param execution_date - execution_date of the ping
	 * @param output - output of the ping test
	 * @param destination - destination of the ping test
	 */
	public PingModel(Integer id, LocalDateTime execution_date, String output, String destination) {
		super();
		this.id = id;
		this.execution_date = execution_date;
		this.output = output;
		this.destination = destination;
	}

	/**
	 * Getter of the destination property.
	 * @return Returns the destination as a stringproperty
	 */
	public SimpleStringProperty getDestinationProperty() {
		return new SimpleStringProperty(this.destination);
	}

	/**
	 * Getter of the execution date.
	 * @return Returns the execution date as a string property
	 */
	public SimpleStringProperty getExecution_DateProperty() {
		return new SimpleStringProperty(execution_date.toString());
	}

}
