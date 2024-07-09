package com.order.example.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

	    private long orderId;
	    private Date orderDate;
	    private String orderStatus;
	    private long amount;
	    private ProductDetails productDetails;

}
