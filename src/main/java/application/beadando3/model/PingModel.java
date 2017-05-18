package application.beadando3.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@javax.persistence.Entity
@Table(name="ping")
@NamedQueries({
		@NamedQuery (name  = "PingModel.getbyId", query="select p from PingModel p where id = :id" ),
		@NamedQuery (name  = "PingModel.findAll", query="select p from PingModel p" ),
		@NamedQuery (name  ="PingModel.count", query="select count(p.id) from PingModel p" )
})
public class PingModel {
	@javax.persistence.Id
	@GeneratedValue
	(strategy=GenerationType.AUTO, generator="ping_seq_gen") 
	@SequenceGenerator(name="ping_seq_gen", sequenceName="ping_SEQ")

	private Integer id;
	private Date execution_date;
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


	public PingModel(Integer id, String execution_date, String output, String destination) {
		super();
		this.id = id;
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
		}
		this.output = output;
		this.destination = destination;
	}

	
}
