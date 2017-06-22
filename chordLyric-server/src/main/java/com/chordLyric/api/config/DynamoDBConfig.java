package com.chordLyric.api.config;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.util.StringUtils;
import com.chordLyric.api.models.impl.User;
import com.chordLyric.api.utils.SystemParams;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Yogen
 * 
 * Configuration for connecting to Amazon DynamoDB and creating a table if doesn't exist.
 *
 */

@Configuration
@EnableDynamoDBRepositories(basePackages="com.chordLyric.api.repositories")
@Slf4j
public class DynamoDBConfig {
	@Value("${amazon.dynamodb.endpoint}")
	private String amazonDynamoDBEndpoint;
	
	@Autowired
	private DynamoDBMapper dynamoDBMapper;

	@Bean 
	public AmazonDynamoDB amazonDynamoDB() {
		AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient();
		
		if (!StringUtils.isNullOrEmpty(amazonDynamoDBEndpoint)) {
			amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);
		}

		return amazonDynamoDB;
	}
	
	@Bean
	public DynamoDBMapper dynamoDBMapper() {
		return new DynamoDBMapper(amazonDynamoDB());
	}
	

	@PostConstruct
	public void setupDB() {
		AmazonDynamoDB amazonDynamoDB = amazonDynamoDB();
		
		ListTablesResult listTablesResult = amazonDynamoDB.listTables();
		
		List<CreateTableRequest> tableRequests = Arrays.asList(
				dynamoDBMapper.generateCreateTableRequest(User.class)
				);
		
		tableRequests.stream().forEach(tableRequest -> {
			if(!listTablesResult.getTableNames().contains(tableRequest.getTableName())) {
				log.info("Creating a table with name: {}", tableRequest.getTableName());
				tableRequest.setProvisionedThroughput(new ProvisionedThroughput(SystemParams.READ_CAPACITY_UNITS, 
						SystemParams.WRITE_CAPACITY_UNITS));
				amazonDynamoDB.createTable(tableRequest);
			}
		});
	}
}
