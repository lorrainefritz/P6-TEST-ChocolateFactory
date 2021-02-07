package com.chocolateFactory.Chocolate.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="ROLE")
public class Role implements Serializable {

private static final long serialVersionUID = 1L;

@Id	
@Column(name="ID")
@GeneratedValue(strategy=GenerationType.AUTO)
private Integer id;

@Column(name="NAME")
@NotBlank
private String name;

public Role(@NotBlank String name) {
	super();
	this.name = name;
}

public Role() {
	super();
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}



}
