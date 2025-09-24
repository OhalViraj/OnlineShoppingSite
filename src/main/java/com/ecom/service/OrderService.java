package com.ecom.service;

import java.util.List;

import com.ecom.model.OrderRequest;
import com.ecom.model.ProductOrder;

import jakarta.mail.MessagingException;

public interface OrderService {

	public void saveOrder(Integer userId,OrderRequest orderRequest) throws Exception, MessagingException;

	public List<ProductOrder> getOrdersByUser(Integer userId);
	
	public ProductOrder updateOrderStarus(Integer id,String status);

	public List<ProductOrder> getAllOrders();
	
	public ProductOrder getOrdersByOrderId(String orderId);
}
