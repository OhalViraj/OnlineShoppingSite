package com.ecom.service.impl;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecom.model.Cart;
import com.ecom.model.OrderAddress;
import com.ecom.model.OrderRequest;
import com.ecom.model.ProductOrder;
import com.ecom.repository.CartRepository;
import com.ecom.repository.ProductOrderRepo;
import com.ecom.service.OrderService;
import com.ecom.util.CommonUtil;
import com.ecom.util.OrderStatus;

import jakarta.mail.MessagingException;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private ProductOrderRepo orderRepo;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Override
	public void saveOrder(Integer userId,OrderRequest orderRequest) throws Exception, MessagingException {
		// TODO Auto-generated method stub
		List<Cart> carts = cartRepository.findByUserId(userId);
		
		for(Cart cart: carts)
		{
			ProductOrder order=new ProductOrder();
			order.setOrderId(UUID.randomUUID().toString());
			order.setOrderDate(LocalDate.now());
			order.setProduct(cart.getProduct());
			order.setPrice(cart.getProduct().getDiscountPrice());
			order.setQuantity(cart.getQuantity());
			order.setUser(cart.getUser());
			order.setStatus(OrderStatus.IN_PROGRESS.getName());
			order.setPaymentType(orderRequest.getPaymentType());
			
			OrderAddress address=new OrderAddress();
			address.setFirstName(orderRequest.getFirstName());
			address.setLastName(orderRequest.getLastName());
			address.setEmail(orderRequest.getEmail());
			address.setMobileNo(orderRequest.getMobileNo());
			address.setAddress(orderRequest.getAddress());
			address.setCity(orderRequest.getCity());
			address.setState(orderRequest.getState());
			address.setPincode(orderRequest.getPincode());
			
			order.setOrderAddress(address);
			ProductOrder saveOrder = orderRepo.save(order);
			commonUtil.sendMailForProductOrder(saveOrder, "success");
		}
		
		
	}

	@Override
	public List<ProductOrder> getOrdersByUser(Integer userId) {
		// TODO Auto-generated method stub
		
		List<ProductOrder> orders=orderRepo.findByUserId(userId);
		return orders;
	}

	@Override
	public ProductOrder updateOrderStarus(Integer id, String status) {
		// TODO Auto-generated method stub
		Optional<ProductOrder> findById = orderRepo.findById(id);
		
		if(findById.isPresent())
		{
			ProductOrder productOrder = findById.get();
			productOrder.setStatus(status);
			ProductOrder updateOrder = orderRepo.save(productOrder);
			
			return updateOrder;
		}
		
		return null;
	}

	@Override
	public List<ProductOrder> getAllOrders() {
		// TODO Auto-generated method stub
		
		List<ProductOrder> orders = orderRepo.findAll();
		return orders;
	}

	@Override
	public ProductOrder getOrdersByOrderId(String orderId) {
		// TODO Auto-generated method stub
		
		ProductOrder findByOrderId = orderRepo.findByOrderId(orderId);
		return findByOrderId;
	}

	@Override
	public Page<ProductOrder> getAllOrdersPagination(Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable=PageRequest.of(pageNo, pageSize);
		return orderRepo.findAll(pageable);
		 
	}
	
	

}
