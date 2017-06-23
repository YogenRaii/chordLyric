/**
 * 
 */
package com.chordLyric.api.models.impl;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@DynamoDBDocument
public class UpdateDetail {

	@DynamoDBAttribute(attributeName = "DateUpdated")
	private Date dateUpdated;
	
	@DynamoDBAttribute(attributeName = "ContributerId")
	private String contributerId;
	
	@DynamoDBAttribute(attributeName = "ContributerUsername")
	private String contributerUsername;
}
