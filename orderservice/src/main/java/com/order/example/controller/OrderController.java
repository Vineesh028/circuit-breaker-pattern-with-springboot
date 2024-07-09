package com.order.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.example.model.OrderRequest;
import com.order.example.model.OrderResponse;
import com.order.example.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping()
	public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest) {
		OrderResponse order = orderService.placeOrder(orderRequest);
		log.info("Order has been submitted: {}", order);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<OrderResponse> getOrderDetailsById(@PathVariable long orderId) {
		log.info("getOrderDetailsById invoked");
		OrderResponse orderResponse = orderService.getOrderDetailsById(orderId);

		return new ResponseEntity<>(orderResponse, HttpStatus.OK);
	}

}
