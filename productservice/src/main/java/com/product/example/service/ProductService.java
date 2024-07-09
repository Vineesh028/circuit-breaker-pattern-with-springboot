package com.product.example.service;

import com.product.example.model.Product;

public interface ProductService {
	
    long addProduct(Product product);

    Product getProductById(long productId);

}
