/**
 * 
 */
package com.chordLyric.api.models.impl;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.chordLyric.api.models.types.ArtistType;
import com.chordLyric.api.models.types.MusicType;

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
@DynamoDBTable(tableName = "chordLyric_Song")
public class Song extends Article {

	@DynamoDBAttribute(attributeName = "Lyrics")
	private String lyrics;
	
	@DynamoDBAttribute(attributeName = "LyricsWithChords")
	private String lyricsWithChords;

	@DynamoDBTyped(DynamoDBAttributeType.S)
	@DynamoDBAttribute(attributeName = "Genre")
	private MusicType genre;
	
	@DynamoDBAttribute(attributeName = "Artists")
	private List<Artist> artists;
}
