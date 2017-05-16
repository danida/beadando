package application.beadando3.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name="ping")

public class TracerouteModel {
	@javax.persistence.Id
	@GeneratedValue
	(strategy=GenerationType.AUTO, generator="traceroute_seq_gen") 
	@SequenceGenerator(name="traceroute_seq_gen", sequenceName="traceroute_SEQ")

	
	
	private Integer id;
	private Date execution_date;
	private String output;
	private String destination;
	
	
	
	public TracerouteModel(Date execution_date, String output, String destination) {
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
	public Date getExecution_date() {
		return execution_date;
	}


	public void setExecution_date(String execution_date) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
	    Date date;
		try {
			date = format.parse(execution_date);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	    try {
			date = format.parse(execution_date);
			this.execution_date = new java.sql.Date(date.getTime());

			} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	}

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


}
