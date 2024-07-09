package com.product.example.service;

import org.springframework.stereotype.Service;

import com.product.example.model.Product;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	@Override
	public long addProduct(Product product) {
		log.info("Adding product...");

		log.info("Product with id: {} created.", product.getProductId());
		return product.getProductId();
	}

	@Override
	public Product getProductById(long productId) {
		log.info("Get the product for product id: {}", productId);
		Product product = Product.builder().productId(productId).productName("Apple iPhone 15").price(70000).quantity(1)
				.build();

		return product;
	}

}
