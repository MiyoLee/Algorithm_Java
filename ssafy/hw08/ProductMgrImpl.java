package com.ssafy.hw08;

import java.util.ArrayList;
import java.util.List;

public class ProductMgrImpl implements IProductMgr{
	//private Product[] product_list = new Product[MAX_SIZE];
	List <Product> product_list = new ArrayList<Product>();
	
	@Override
	public void add(Product p) throws DuplicateException {	//이미 존재하는 상품을 추가하려할 경우 예외 처리
		for(int i=0; i<product_list.size(); i++) {
			if(product_list.get(i).equals(p)) {
				throw new DuplicateException();
			}
		}
		product_list.add(p);
	}
	@Override
	public List<Product> list() { // 상품을 볼 수 있는 기능
		return product_list;
	}
	@Override
	public Product searchByNum(int num) throws CodeNotFoundException { // 상품 번호로 검색하는 기능
		Product p = null;
		for (int i = 0; i < product_list.size(); i++) {
			if (product_list.get(i).getNum() == num) {
				p = product_list.get(i);
			}
		}
		if(p==null){
			throw new CodeNotFoundException(num);
		}
		return p;
	}
	@Override
	public List<Product> searchByName(String Name) { // 상품명으로 검색하는 기능
		List<Product>result = new ArrayList<Product>();
		for (int i = 0; i < product_list.size(); i++) {
			if (product_list.get(i).getName().contains(Name)) {
				result.add(product_list.get(i));
			}
		}
		return result;
	}
	@Override
	public List<TV> getTVs() { // TV만 검색
		List <TV> result = new ArrayList<TV>();
		for (int i = 0; i < product_list.size(); i++) {
			if (product_list.get(i) instanceof TV) {
				result.add((TV)product_list.get(i));
			}
		}
		return result;
	}
	@Override
	public List<Refrigerator> getRefrigerators() { // Refrigerator만 검색
		List <Refrigerator> result = new ArrayList<Refrigerator>();
		for (int i = 0; i < product_list.size(); i++) {
			if (product_list.get(i) instanceof Refrigerator) {
				result.add((Refrigerator)product_list.get(i));
			}
		}
		return result;
	}
	@Override
	public void delete(int num) {	//상품 번호로 상품 정보 삭제
		for (int i = 0; i < product_list.size(); i++) {
			if (product_list.get(i).getNum() == num) {
				product_list.remove(i);
				return;
			}
		}
		System.out.println("해당 상품은 없습니다.");
	}
	@Override
	public int getTotalPrice() { // 전체 재고 상품 금액을 구하는 기능
		int sum=0;
		for(Product p : product_list) {
			sum+=p.getPrice()*p.getQty();
		}
		return sum;
	}

	@Override
	public List<Refrigerator> getOver400L() throws ProductNotFoundException {
		List <Refrigerator> ref_list = new ArrayList<Refrigerator>();
		for(Refrigerator r : getRefrigerators()) {
			if(r.getVol() >= 400) {
				ref_list.add(r);
			}
		}
		if(ref_list.isEmpty()) {
			throw new ProductNotFoundException();
		}
		return ref_list;
	}
	@Override
	public List<TV> getOver50inch() throws ProductNotFoundException {
		List<TV> tv_list = new ArrayList<TV>();
		for(TV t : getTVs()) {
			if(t.getInch() >= 50) {
				tv_list.add(t);
			}
		}
		if(tv_list.isEmpty()) {
			throw new ProductNotFoundException();
		}
		return tv_list;
	}
	@Override
	public void update(int num, int price) {
		for (int i = 0; i < product_list.size(); i++) {
			if (product_list.get(i).getNum() == num) {
				product_list.get(i).setPrice(price);
			}
		}
	}

	
}
