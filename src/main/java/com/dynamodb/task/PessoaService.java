package com.dynamodb.task;

import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.dynamodb.dao.PessoaDAO;
import com.dynamodb.model.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    final PessoaDAO pessoaDAO;

    @Autowired
    PessoaService(PessoaDAO pessoaDAO) {
        this.pessoaDAO = pessoaDAO;
    }

    public void save(Pessoa pessoa) {
        this.pessoaDAO.create(pessoa);
    }

    @Async("executor")
    public void update(String id, String newName) {
        System.out.println(Thread.currentThread().getName());
        try {
            this.pessoaDAO.updateName(id, newName);
        } catch(ConditionalCheckFailedException e) {
            System.out.println(String.format("ConditionalCheckFailed for name: %s", newName));
        }
    }
}
