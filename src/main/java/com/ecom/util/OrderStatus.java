package com.ecom.util;

public enum OrderStatus {

	In_PROGRESS(1,"IN PROGRESS"),
	ORDER_RECEIVED(2,"ORDER RECEIVED"),
	PRODUCT_PACKED(3,"PRODUCT PACKED"),
	OUT_FOR_DELIVERY(4,"OUT FOR DELIVERY"),
	DELIVERED(5,"DELIVERD");
	
	private Integer id;
	
	private String name;

	private OrderStatus(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
