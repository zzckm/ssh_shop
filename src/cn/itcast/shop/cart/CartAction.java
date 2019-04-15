package cn.itcast.shop.cart;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.product.Product;
import cn.itcast.shop.product.ProductService;

/**
 * 购物模块的Action类
 * @author Kg.zz
 *
 */
	
public class CartAction {
	//接收pid
	private Integer pid;
	//接收数量
	private Integer count;
	//注入productService
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
			this.productService = productService;
		}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 从session范围获取购物车的代码
	 */
	public Cart getCart(HttpServletRequest request)
	{
		//从session的范围获得Cart对象
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		//判断
		if(cart==null)
		{
			//创建购物车对象
			cart=new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	/**
	 * 添加到购物车的方法
	 */
	public String addCart(){
		//查询商品信息
		Product product=productService.findByPid(pid);
		//创建一个购物项对象
		CartItem cartItem=new CartItem();
		cartItem.setCount(count);
		cartItem.setProduct(product);
		//获取购物车 需要依赖于request对象
		HttpServletRequest request=ServletActionContext.getRequest();
		Cart cart=getCart(request);
		cart.addCart(cartItem);
		
		return "addCartSuccess";
	}
	/**
	 * 清空购物车:
	 */
	public String clearCart() {
		// 获取Cart对象.
		HttpServletRequest request = ServletActionContext.getRequest();
		Cart cart = getCart(request);
		
		cart.clearCart();
		return "clearCartSuccess";
	}

	/**
	 * 移除购物项
	 */
	public String removeCart() {
		// 获取Cart对象.
		HttpServletRequest request = ServletActionContext.getRequest();
		Cart cart = getCart(request);
		
		cart.removeCart(pid);
		return "removeCartSuccess";
	}
	
	/**
	 * 我的购物车:
	 */
	public String myCart(){
		return "myCartSuccess";
	}
}
