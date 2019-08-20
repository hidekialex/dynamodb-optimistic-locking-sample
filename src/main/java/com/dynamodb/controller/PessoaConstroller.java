package com.dynamodb.controller;

import com.dynamodb.model.Pessoa;
import com.dynamodb.task.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PessoaConstroller {

    private PessoaService service;

    @Autowired
    PessoaConstroller(PessoaService service) {
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void create() {

        Pessoa pessoa = new Pessoa();
        pessoa.setId("1234");
        pessoa.setName("Alexandre Hideki");
        pessoa.setAge(29);

        service.save(pessoa);

        service.update("1234", "A");
        service.update("1234", "Al");
        service.update("1234", "Ale");
        service.update("1234", "Alex");
        service.update("1234", "Alexa");
        service.update("1234", "Alexan");
        service.update("1234", "Alexand");
        service.update("1234", "Alexandr");
    }
}
