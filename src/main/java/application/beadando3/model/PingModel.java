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

@javax.persistence.Entity
@Table(name = "ping")
@NamedQueries({ @NamedQuery(name = "PingModel.getbyid", query = "select p from PingModel p where id = :id"),
		@NamedQuery(name = "PingModel.findAll", query = "select p from PingModel p"),
		@NamedQuery(name = "PingModel.count", query = "select count(p.id) from PingModel p") })
public class PingModel {
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ping_seq_gen")
	@SequenceGenerator(name = "ping_seq_gen", sequenceName = "ping_SEQ")

	private Integer id;
	private LocalDateTime execution_date;
	private String output;
	private String destination;

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

	public void setExecution_date(LocalDateTime now) {

		this.execution_date = now;

	}

	@Column
	@Basic

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@Column
	@Basic
	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public PingModel() {

	}

	public PingModel(Integer id, LocalDateTime execution_date, String output, String destination) {
		super();
		this.id = id;
		this.execution_date = execution_date;
		this.output = output;
		this.destination = destination;
	}

	public SimpleStringProperty getDestinationProperty() {
		return new SimpleStringProperty(this.destination);
	}

	public SimpleStringProperty getExecution_DateProperty() {
		return new SimpleStringProperty(execution_date.toString());
	}

}
