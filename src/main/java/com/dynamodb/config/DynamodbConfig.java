package com.dynamodb.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DynamodbConfig {

    @Bean
    AmazonDynamoDB dynamoDB() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
        return client;
    }

    @Bean(value = "clobber")
    DynamoDBMapperConfig configWithoutOptmisticLock() {
        final DynamoDBMapperConfig.Builder builder = new DynamoDBMapperConfig.Builder();
        builder.setConsistentReads(DynamoDBMapperConfig.ConsistentReads.EVENTUAL);
        builder.setSaveBehavior(DynamoDBMapperConfig.SaveBehavior.CLOBBER);
        return builder.build();
    }

    @Primary
    @Bean(value = "update")
    DynamoDBMapperConfig configWithOptmisticLock() {
        final DynamoDBMapperConfig.Builder builder = new DynamoDBMapperConfig.Builder();
        builder.setConsistentReads(DynamoDBMapperConfig.ConsistentReads.EVENTUAL);
        builder.setSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE);
        return builder.build();
    }
}