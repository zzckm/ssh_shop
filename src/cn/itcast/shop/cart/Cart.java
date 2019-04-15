package cn.itcast.shop.cart;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 购物车对象：
 * @author Kg.zz
 *
 */
public class Cart {
	//购物车可以存放多个购物项：
	//Map集合用商品的ID作为Map的key，购物项作为Map的value
	private Map<Integer, CartItem> map=new HashMap<Integer, CartItem>();
	
	//提供获得map的value作为一个集合:用于在Cart页面中显示+++<CartItem>目的是为了在订单里遍历
	//相当于类中有一个属性：cartItems
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	//总计
	private Double total=0d;
	
	public Double getTotal() {
		return total;
	}
	//提供三个方法：
	//将购物项添加到购物车：
	public void addCart(CartItem cartItem){
		//获取购物项的标识id
		Integer pid=cartItem.getProduct().getPid();
		if(map.containsKey(pid))
		{
			//购物车已有该购物项
			CartItem _cartitem=map.get(pid);
			_cartitem.setCount(_cartitem.getCount()+cartItem.getCount());
		}
		else {
			//购物车没有该购物项
			map.put(pid, cartItem);
		}
		//总计
		total+=cartItem.getSubtotal();
		
	}
	//将购物项从购物车中移除：
	public void removeCart(Integer pid){
		CartItem cartItem=map.remove(pid);
		total-=cartItem.getSubtotal();
	}
	//清空购物车：
	public void clearCart(){
		//清空map总计设为0
		map.clear();
		total=0d;
	}
}
