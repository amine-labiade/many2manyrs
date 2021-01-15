package com.apptap.freezeos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.Table;


@Entity
@Table(appliesTo = "car")
public class Car implements Serializable {
	private static final long serialVersionUID = 1L;
	
	


	@Id
	@Column(name = "cid", unique = true)
	private int id;

	@Column(name = "model", nullable = false)
	private String model;
	
	@Column(name = "plate", nullable = false)
	private String plate;
	
	
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Cars_Drivers", 
        joinColumns = { @JoinColumn(name = "cid") }, 
        inverseJoinColumns = { @JoinColumn(name = "did") }
    )
    
    Set<Driver> drivers = new HashSet<>();

	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}
}
