package com.ssafy.hw08;

import java.util.List;

public interface IProductMgr {
	public void add(Product p) throws DuplicateException;
	public List<Product> list();
	public Product searchByNum(int num) throws CodeNotFoundException;
	public List<Product> searchByName(String Name);
	public List<TV> getTVs();
	public List<Refrigerator> getRefrigerators();
	public List<Refrigerator> getOver400L() throws ProductNotFoundException;		//400L 이상의 Refrigerator 검색
	public List<TV> getOver50inch() throws ProductNotFoundException;	//50inch 이상의 TV 검색
	//상품번호와 가격을 입력 받아 상품 가격을 변경할 수 있는 기능
	public void update(int num, int price);
	public void delete(int num) ;
	public int getTotalPrice();
}
