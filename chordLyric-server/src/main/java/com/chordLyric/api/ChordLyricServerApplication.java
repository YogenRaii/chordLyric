package com.chordLyric.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.chordLyric.api.repositories")
@EnableElasticsearchRepositories(basePackages = "com.chordLyric.api.search.repositories")
public class ChordLyricServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChordLyricServerApplication.class, args);
	}
}
