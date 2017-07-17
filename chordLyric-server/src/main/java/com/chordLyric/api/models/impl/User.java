package com.chordLyric.api.models.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import com.chordLyric.api.models.Model;
import com.chordLyric.api.models.types.RoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User extends Model<String> {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@NotEmpty 
	@NotNull
	private String username;
	
	@NotEmpty 
	@NotNull
	private String email;
	
	@NotEmpty 
	@NotNull
	private String firstName;
	
	@NotEmpty 
	@NotNull
	private String lastName;
	
	private Date dateCreated;
	
	@Enumerated(EnumType.STRING)
	private RoleType role;
	
	private String token;
}
