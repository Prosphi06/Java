package com.eventdriven.microservice.rest.query;

import com.eventdriven.microservice.query.FindProductQuery;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class QueryController {

    @Autowired
    QueryGateway queryGateway;

    @GetMapping(value = "/products")
    public List<ProductRestModel> getProducts(){
        FindProductQuery findProductQuery = new FindProductQuery();
        List<ProductRestModel> products = queryGateway.query(findProductQuery, ResponseTypes.multipleInstancesOf(ProductRestModel.class)).join();
        return products;
    }
}
