package com.order.example.service;

import com.order.example.model.OrderRequest;
import com.order.example.model.OrderResponse;

public interface OrderService {
	
    OrderResponse placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetailsById(long orderId);
}
