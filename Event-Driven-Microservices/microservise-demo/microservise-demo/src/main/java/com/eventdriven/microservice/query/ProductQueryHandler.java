package com.eventdriven.microservice.query;

import com.eventdriven.microservice.persistance.Entity.ProductEntity;
import com.eventdriven.microservice.persistance.ProductRepo;
import com.eventdriven.microservice.rest.query.ProductRestModel;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductQueryHandler {

    private final ProductRepo productRepo;

//    public ProductQueryHandler(ProductRepo productRepo){
//        this.productRepo = productRepo
//    }
    @QueryHandler
    public List<ProductRestModel> findProduct(FindProductQuery query){
        List<ProductRestModel> productRest = new ArrayList<>();
        List<ProductEntity> storedProduct = productRepo.findAll();

        for (ProductEntity productEntity: storedProduct) {
            ProductRestModel productRestModel = new ProductRestModel();
            BeanUtils.copyProperties(productEntity, productRestModel);
            productRest.add(productRestModel);
        }
        return productRest;
    }
}
