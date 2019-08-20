package com.dynamodb.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.dynamodb.model.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class PessoaDAO {

    @Autowired
    private DynamoDBMapperConfig configWithOptimisticLocking;

    @Qualifier(value = "clobber")
    @Autowired
    private DynamoDBMapperConfig configWithoutOptimisticLocking;

    @Autowired
    private AmazonDynamoDB client;

    public void create(Pessoa dto) {
        DynamoDBMapper mapper = new DynamoDBMapper(client, configWithoutOptimisticLocking);
        mapper.save(dto);
    }

    public Pessoa get(String id) {
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        return mapper.load(Pessoa.class,id);
    }

    public Pessoa updateName(String id, String newName) {
        Pessoa pessoa = get(id);
        pessoa.setName(newName);
        DynamoDBMapper mapper = new DynamoDBMapper(client, configWithOptimisticLocking);
        mapper.save(pessoa);
        return pessoa;
    }

}
