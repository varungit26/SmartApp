package com.techresource.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Employee implements Serializable {

	private static final long serialVersionUID = -79731625857400812L;

	@Id
	private int id;
	
	@NotEmpty
	@Size(min = 2, max = 30)
	private String name;
	
	@NotEmpty
	@Size(min = 2, max = 30)
	private String role;

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
