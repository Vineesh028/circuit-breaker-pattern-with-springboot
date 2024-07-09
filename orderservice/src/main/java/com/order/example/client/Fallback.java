package com.order.example.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.order.example.model.ProductDetails;

@Component
public class Fallback implements ProductServiceClient{

	@Override
	public ResponseEntity<ProductDetails> getProductById(long productId) {
		return null;
	}

 

}
