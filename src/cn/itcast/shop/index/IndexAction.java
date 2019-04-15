package cn.itcast.shop.index;

import java.util.List;

import cn.itcast.shop.category.Category;
import cn.itcast.shop.category.CategoryService;
import cn.itcast.shop.product.Product;
import cn.itcast.shop.product.ProductService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 首页访问的Action
 * @author Kg.zz
 *
 */
public class IndexAction extends ActionSupport { 

	//注入以一级分类的Serivce
	private  CategoryService categoryService;
	//注入商品的Service
	private ProductService productService;
	//热门商品集合
	private List<Product> hotList;
	//最新商品集合 :get一下方法 在页面中就可以获取在action方法中以获取的值
	private List<Product> newList;
	public List<Product> getNewList() {
		return newList;
	}
	public List<Product> getHotList() {
		return hotList;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	/**
	 * 执行首页访问方法
	 */
	@Override
	public String execute() throws Exception {
		//查询所有一级分类
		List<Category>  categoryList = categoryService.findAll();
		// 存入到Session
		ActionContext.getContext().getSession().put("categoryList", categoryList);
		//查询热门商品
		hotList=productService.findHot();
		
		//查询最新商品
		newList=productService.findNew();
		
		
		return "indexSuccess";
	}
}
