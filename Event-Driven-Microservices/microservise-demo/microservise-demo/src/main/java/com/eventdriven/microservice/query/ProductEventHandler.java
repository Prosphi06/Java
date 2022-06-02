package com.eventdriven.microservice.query;

import com.eventdriven.microservice.events.ProductCreatedEvent;
import com.eventdriven.microservice.persistance.Entity.ProductEntity;
import com.eventdriven.microservice.persistance.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductEventHandler {

    private final ProductRepo repo;

    @EventHandler
    public void on(ProductCreatedEvent event){
        ProductEntity entity = new ProductEntity();
        BeanUtils.copyProperties(event, entity);

        repo.save(entity);
    }
}
