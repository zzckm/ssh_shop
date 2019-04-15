package cn.itcast.shop.cart;

import cn.itcast.shop.product.Product;

/**
 * 购物项
 * @author Kg.zz
 *
 */
public class CartItem {
	//商品对象
	private Product product;
	//数量
	private Integer count;
	//小计：不允许从外部更改 只可以自动生成，因此不需要setg方法
		private Double subtotal;
		
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getSubtotal() {
		return count*product.getShop_price();
	}
	
	
}
