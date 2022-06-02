package com.eventdriven.microservice.rest.command;

import com.eventdriven.microservice.command.CreateProductCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api")
public class CommandController {

    private final Environment env;
    private final CommandGateway gateway;

    @Autowired
    public CommandController(Environment env, CommandGateway gateway){
        this.env = env;
        this.gateway = gateway;
    }

    @PostMapping(value = "/save")
    public String save(@RequestBody ProductModel product) {
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .title(product.getTitle())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .productId(UUID.randomUUID().toString())
                .build();
        String returnValue;
        try {
            returnValue = gateway.sendAndWait(createProductCommand);
        }catch (Exception e) {
            returnValue = e.getLocalizedMessage();
        }
        return returnValue;
    }

//    @GetMapping(value = "/get")
//    public String get(){
//        return "Product retrieved " + env.getProperty("local.server.port");
//    }

}
