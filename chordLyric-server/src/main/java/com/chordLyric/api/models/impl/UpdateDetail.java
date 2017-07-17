/**
 * 
 */
package com.chordLyric.api.models.impl;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.chordLyric.api.models.types.ArtistType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Yogen
 *
 */
@Getter
@Setter
@ToString
@Entity
public class UpdateDetail {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private Date dateUpdated = new Date();
	
	private String contributerId;
	
	private String contributerUsername;
}
