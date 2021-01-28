package com.ssafy.hw08;

public class Product {
	//member variable
	private int num;
	private String name;
	private int price;
	private int qty;
	//constructor
	public Product() {
		super();
	}
	public Product(int num, String name, int price, int qty) {
		super();
		this.num = num;
		this.name = name;
		this.price = price;
		this.qty = qty;
	}
	//Getters & Setters
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	@Override
	public boolean equals(Object o) {
		Product p = (Product)o;
		if( this.getNum()==p.getNum() && this.getName().equals(p.getName()) ){
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return "제품 번호:" + num + "\t"+"|"+"제품이름:" + name + "\t"+"|"+"가격:" + price +"원"+"\t"+"|"+ "수량:" + qty+"\t"+"|" ;
	}
}
