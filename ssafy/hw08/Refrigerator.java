package com.ssafy.hw08;

public class Refrigerator extends Product{
	private int vol;
	
	public Refrigerator() {}
	public Refrigerator(int num, String name, int price, int qty, int vol) {
		super(num, name, price, qty);
		this.vol = vol;
	}
	public int getVol() {
		return vol;
	}
	public void setVol(int vol) {
		this.vol = vol;
	}
	@Override
	public String toString() {
		return super.toString()+"용량:"+vol+"L";
	}
	
}
