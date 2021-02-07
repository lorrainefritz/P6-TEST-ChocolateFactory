package com.chocolateFactory.Chocolate.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "REGISTERED_USER", 
	uniqueConstraints = @UniqueConstraint(columnNames = "EMAIL"))
//@Getter
//@Setter
//@NoArgsConstructor
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "NAME")
	@NotBlank
	private String name;

	@Column(name = "PASSWORD")
	@NotBlank
	private String password;

	@Column(name = "EMAIL")
	@NotBlank
	private String email;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "REGISTERED_USERS_ROLES",
			joinColumns = @JoinColumn(
					name = "USER_ID", referencedColumnName = "ID"),
			inverseJoinColumns = @JoinColumn
			(name = "ROLE_ID", referencedColumnName = "ID"))
	private Collection<Role> roles;

	public User(@NotBlank String name, @NotBlank String password, @NotBlank String email,
			Collection<Role> roles) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}

	public User() {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	

}
