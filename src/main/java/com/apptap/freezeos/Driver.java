package com.apptap.freezeos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Table;

@Entity
@Table(appliesTo = "driver")
public class Driver implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "did")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "experience")
	private int exp;
	
    @ManyToMany(mappedBy = "cars")
    private Set<Car> cars = new HashSet<>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

}
