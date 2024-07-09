package com.order.example.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.order.example.model.ProductDetails;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@CircuitBreaker(name = "external")
@FeignClient(name = "PRODUCT-SERVICE",url = "http://localhost:8081", path = "/product", fallback = Fallback.class)
public interface ProductServiceClient {
	
	@GetMapping("/{id}")
    public ResponseEntity<ProductDetails> getProductById(@PathVariable("id") long productId);

}


