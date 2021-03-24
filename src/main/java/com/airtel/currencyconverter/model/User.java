package com.airtel.currencyconverter.model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	@NotEmpty(message = "Please should not be empty")
	@Email(message = "Please enter a valid email")
	private String email;
	private String password;
	private String passwordConfirmation; //FIXEME: Create separate register model
	@NotEmpty
	@Size(min = 5, message = "Firstname should be at least 5 characters")
	private String firstName;
	@NotEmpty
	@Size(min = 5, message = "Lastname should be at least 5 characters")
	private String lastName;

	@ElementCollection
	@CollectionTable(name = "tags")
	private Set<String> queries;

	@Column(name = "email", nullable = false)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return this.password;
	}

	@Column(name = "passwordConfirmation", nullable = false)
	public String getPasswordConfirmation() {
		return this.passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return this.firstName;
	}

	@Column(name = "first_name", nullable = false)
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", nullable = false)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<String> getQueries() {
		return queries;
	}

	public void setQueries(Set<String> queries) {
		this.queries = queries;
	}

}
