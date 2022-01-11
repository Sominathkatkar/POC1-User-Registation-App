package com.neo.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Generated;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
@Table(name="User_DTLS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="Name is required")
	@Column(name="FIRST_NAME")
	private String fName;
	
	@NotNull(message="Last Name is required")
	@Column(name="LAST_NAME")
	private String lName;
	
	@NotNull(message="Gender is required")
	@Column(name="GENDER")
	private String gender;
	
	@NotNull(message="Addres is required")
	@Column(name="ADDRESS")
	private String address;
	
	@NotNull(message="Pincode is required")
	@Column(name="PINCODE")
	private Integer pincode;
	
	@NotNull(message="email is required")
	@Column(name="EMAIL")
    private String email;
	
	@NotNull(message="Date of birth is required")
	@Column(name="DOB")
    private LocalDate dob;
	
	@NotNull(message="Date of joining is required")
	@Column(name="DOJ")
    private LocalDate doj;
	
	
	@Column(name="ACTIVE_SW")
	private boolean activeSw;
	
    
}
