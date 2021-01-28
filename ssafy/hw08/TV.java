package com.ssafy.hw08;

public class TV extends Product {
	private int inch;
	private String type;

	public TV() {
	};

	public TV(int num, String name, int price, int qty, int inch, String type) {
		super(num, name, price, qty);
		this.inch = inch;
		this.type = type;
	}

	public int getInch() {
		return inch;
	}

	public void setInch(int inch) {
		this.inch = inch;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return super.toString() +"인치:"+inch + "\t" + "|"+"디스플레이타입:"+type;
	}

}
