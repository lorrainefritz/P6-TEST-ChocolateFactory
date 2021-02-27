package com.chocolateFactory.Chocolate.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="CHOCOLATE")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Chocolate implements Serializable {
	
	
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer id;
	
	@Size(min=2,max=10,message="merci de rentrer de 2 à 10 charactères signé le Back")
//	@NotBlank
	@Column(name="NAME")
	private String name;

}

