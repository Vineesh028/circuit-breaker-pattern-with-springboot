package com.order.example.service;


import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.order.example.client.ProductServiceClient;
import com.order.example.model.OrderRequest;
import com.order.example.model.OrderResponse;
import com.order.example.model.ProductDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
    ProductServiceClient productServiceClient;

    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) {

        log.info("Placing order request: {}", orderRequest);
        
        ResponseEntity<ProductDetails> product = productServiceClient.getProductById(orderRequest.getProductId());

		OrderResponse order = OrderResponse
        		.builder()
        		.orderId(new Random().nextInt(900000))
        		.orderStatus("CREATED")
        		.orderDate(new Date())
        		.amount(new Random().nextInt(90000))
        		//.productDetails(product)
        		.build();
		
		if(null != product) {
			order.setProductDetails(product.getBody());
		}

        return order;
    }

    
    @Override
    public OrderResponse getOrderDetailsById(long orderId){

        log.info("Get order details for order id {}", orderId);

         ResponseEntity<ProductDetails> product = productServiceClient.getProductById(100);
         OrderResponse order =  OrderResponse.builder()
 		.orderId(orderId)
 		.orderStatus("CREATED")
 		.orderDate(new Date())
 		.amount(new Random().nextInt(90000))
 		.build();
         
         if(null != product) {
 			order.setProductDetails(product.getBody());
 		}

        return order;
    }
    
    
    public ResponseEntity<OrderResponse> getOrderDetailsByIdFallback(@PathVariable long orderId, Throwable throwable) {
    	OrderResponse order = OrderResponse.builder()
		.orderId(orderId)
		.orderStatus("CREATED")
		.orderDate(new Date())
		.amount(new Random().nextInt(90000))
		.productDetails(new ProductDetails())
		.build();

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
