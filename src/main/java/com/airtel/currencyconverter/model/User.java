package com.airtel.currencyconverter.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "user")
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	@NotEmpty(message = "Please should not be empty")
	@Email(message = "Please enter a valid email")
	private String email;
	@Size(min = 8, message = "Password should be at least 8 characters")
	private String password;
	@Size(min = 8, message = "Both passwords do not match")
	private String passwordConfirmation; //FIXEME: Create separate register model
	@NotEmpty
	@Size(min = 3, message = "Firstname should be at least 5 characters")
	private String firstName;
	@NotEmpty
	@Size(min = 3, message = "Lastname should be at least 5 characters")
	private String lastName;

	@NotEmpty
	@Size(min = 3, message = "Please select a Date of Birth")
	private String dateOfBirth;
	@NotEmpty
	@Size(min = 3, message = "Enter your street Address")
	private String addressLine1;
	private String addressLine2;
	@NotEmpty
	@Size(min = 3, message = "City should be at least 5 characters")
	private String city;
	@NotEmpty
	@Size(min = 3, message = "Please select country")
	private String country;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "queries")
	private Set<String> queries;

	@OneToMany
	@JoinTable(name = "user_conversion")
	private Set<Conversion> conversions;

	@Column(name = "email", nullable = false, unique = true)
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

	@Column(name = "date_of_birth", nullable = false)
	public String getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "address_line_1", nullable = false)
	public String getAddressLine1() {
		return this.addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}


	@Column(name = "address_line_2", nullable = false)
	public String getAddressLine2() {
		return this.addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}


	@Column(name = "city", nullable = false)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "country", nullable = false)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Set<String> getQueries() {
		return queries;
	}

	public void setQueries(Set<String> queries) {
		this.queries = queries;
	}

	public Set<Conversion> getConversions() {
		return conversions;
	}

	public void setConversion(Set<Conversion> conversions) {
		this.conversions = conversions;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> auths = new HashSet<>();
		GrantedAuthority auth = new SimpleGrantedAuthority("ROLE_ADMIN");
		auths.add(auth);
		return auths;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
