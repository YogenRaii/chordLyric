/**
 * 
 */
package com.chordLyric.api.models.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.elasticsearch.annotations.Document;

import com.chordLyric.api.models.types.ArtistType;
import com.chordLyric.api.models.types.MusicType;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Document(indexName = "song", type = "song", shards = 1, replicas = 0, refreshInterval = "-1")
@Entity
public class Song extends Article {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@NotNull
	@NotEmpty
	private String title;

	@NotNull
	@NotEmpty
	private String lyrics;
	
	private String lyricsWithChords;

	@NotNull
	@Enumerated(EnumType.STRING)
	private MusicType genre;
	
	@Valid
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "song_artist", joinColumns = {@JoinColumn(name = "song_id", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "artist_id", nullable = false, updatable = false)})
	private List<Artist> artists;
	
	private boolean isApproved;
	
	private String contributerId;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "UPDATE_ID")
	private List<UpdateDetail> updateDetails = new ArrayList<>();
}
