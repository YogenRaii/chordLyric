/**
 * 
 */
package com.chordLyric.api.models.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;
import com.chordLyric.api.models.types.ArtistType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Yogen
 *
 */
@Getter
@Setter
@ToString
@DynamoDBDocument
public class Artist extends Article {

	@DynamoDBAttribute(attributeName = "Role")
	private String name;
	
	@DynamoDBAttribute(attributeName = "Bio")
	private String bio;

	@DynamoDBTyped(DynamoDBAttributeType.S)
	@DynamoDBAttribute(attributeName = "Type")
	private ArtistType type;
}
